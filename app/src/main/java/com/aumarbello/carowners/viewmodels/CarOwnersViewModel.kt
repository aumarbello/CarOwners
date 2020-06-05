package com.aumarbello.carowners.viewmodels

import com.aumarbello.carowners.models.Filter
import com.aumarbello.carowners.models.CarOwner
import com.aumarbello.carowners.repositories.CarOwnersRepository
import java.io.File
import javax.inject.Inject

class CarOwnersViewModel @Inject constructor(private val repository: CarOwnersRepository):
    BaseViewModel<CarOwner>() {
    fun filterOwners(filter: Filter, csvFile: File) {
        loadData { repository.filterOwners(filter, csvFile) }
    }
}