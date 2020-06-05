package com.aumarbello.carowners.models

import com.aumarbello.carowners.utils.toInitialCap

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
            gender.toInitialCap(), colors, countries
        )
    }
}