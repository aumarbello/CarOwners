package com.aumarbello.carowners.viewmodels

import com.aumarbello.carowners.models.Filter
import com.aumarbello.carowners.repositories.FilterRepository
import javax.inject.Inject

class FilterViewModel @Inject constructor(private val repository: FilterRepository)
    : BaseViewModel<Filter>() {
    fun loadFilters() {
        loadData { repository.fetchFilters() }
    }
}