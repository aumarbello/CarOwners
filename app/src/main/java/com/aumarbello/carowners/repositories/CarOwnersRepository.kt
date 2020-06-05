package com.aumarbello.carowners.repositories

import com.aumarbello.carowners.models.Filter
import com.aumarbello.carowners.service.CarOwnersService
import com.aumarbello.carowners.utils.CSVParser
import com.aumarbello.carowners.utils.Constants
import com.aumarbello.carowners.utils.toInitialCap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class CarOwnersRepository @Inject constructor(
    private val service: CarOwnersService,
    private val parser: CSVParser
) {
    suspend fun filterOwners(filter: Filter, csvFile: File) = withContext(Dispatchers.IO) {
        if (!csvFile.exists()) {
            downloadCSV(csvFile)
        }

        parser.parseFile(csvFile)

        var owners = parser.owners.asSequence()
            .filter { it.gender == filter.gender.toInitialCap() }

        if (filter.countries.isNotEmpty()) {
            owners = owners.filter { filter.countries.contains(it.country) }
        }

        if (filter.colors.isNotEmpty()) {
            owners = owners.filter { filter.colors.contains(it.carColor) }
        }

        owners.map { it.toOwner() }
            .toList()
    }

    private suspend fun downloadCSV(csvFile: File) = withContext(Dispatchers.IO) {
        val responseStream = service.downloadFile(Constants.FILE_PATH).byteStream()

        responseStream.use { input ->
            val outputStream = FileOutputStream(csvFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024)
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }
}