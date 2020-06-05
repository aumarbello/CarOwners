package com.aumarbello.carowners.utils

import com.aumarbello.carowners.models.CarOwnerBean
import java.io.File

interface CSVParser {
    val owners: List<CarOwnerBean>

    fun parseFile(file: File)
}