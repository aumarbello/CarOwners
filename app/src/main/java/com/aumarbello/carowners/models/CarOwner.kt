package com.aumarbello.carowners.models

data class CarOwner(
    val id: Int,
    val email: String,
    val gender: String,
    val country: String,
    val firstName: String,
    val lastName: String,
    val carModel: String,
    val carYear: Int,
    val carColor: String,
    val jobTitle: String,
    val biography: String
)