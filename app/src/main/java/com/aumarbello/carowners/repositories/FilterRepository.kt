package com.aumarbello.carowners.repositories

import com.aumarbello.carowners.service.FilterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilterRepository @Inject constructor(private val service: FilterService) {
    suspend fun fetchFilters() = withContext(Dispatchers.IO) {
        service.fetchFilters().map { it.toFilter() }.toSet().toList()
    }
}