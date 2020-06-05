package com.aumarbello.carowners.service

import com.aumarbello.carowners.models.FilterResponse
import retrofit2.http.GET

interface FilterService {
    @GET("accounts")
    suspend fun fetchFilters(): List<FilterResponse>
}