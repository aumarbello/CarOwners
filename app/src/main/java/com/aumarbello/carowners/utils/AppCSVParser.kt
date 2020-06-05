package com.aumarbello.carowners.utils

import com.aumarbello.carowners.models.CarOwnerBean
import com.univocity.parsers.csv.CsvRoutines
import java.io.File

class AppCSVParser : CSVParser {
    private val _owners = mutableListOf<CarOwnerBean>()

    override val owners: List<CarOwnerBean>
        get() = _owners

    override fun parseFile(file: File) {
        if (_owners.isNotEmpty()) {
            //File has been parsed already
            return
        }

        _owners.addAll(CsvRoutines().iterate(CarOwnerBean::class.java, file).toList())
    }
}