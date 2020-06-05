package com.aumarbello.carowners.models

import com.univocity.parsers.annotations.Parsed

class CarOwnerBean {
    @Parsed
    var id: Int = 0

    @Parsed
    lateinit var email: String

    @Parsed
    lateinit var country: String

    @Parsed
    lateinit var gender: String

    @Parsed(field = "first_name")
    lateinit var firstName: String

    @Parsed(field = "last_name")
    lateinit var lastName: String

    @Parsed(field = "car_model")
    lateinit var carModel: String

    @Parsed(field = "car_model_year")
    var carModelYear: Int = 0

    @Parsed(field = "car_color")
    lateinit var carColor: String

    @Parsed(field = "job_title")
    lateinit var jobTitle: String

    @Parsed(field = "bio")
    lateinit var biography: String

    fun toOwner(): CarOwner {
        return CarOwner(
            id,
            email,
            gender,
            country,
            firstName,
            lastName,
            carModel,
            carModelYear,
            carColor,
            jobTitle,
            biography
        )
    }
}