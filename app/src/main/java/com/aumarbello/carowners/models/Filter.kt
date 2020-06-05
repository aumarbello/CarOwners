package com.aumarbello.carowners.models

data class Filter (
    val gender: String,
    val colors: List<String>,
    val countries: List<String>
)