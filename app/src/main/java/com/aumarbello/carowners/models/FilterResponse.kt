package com.aumarbello.carowners.models

data class FilterResponse (
    val id: String,
    val avatar: String,
    val fullName: String,
    val gender: String,
    val createdAt: String,
    val colors: List<String>,
    val countries: List<String>
) {
    fun toFilter(): Filter {
        return Filter(
            gender, colors, countries
        )
    }
}