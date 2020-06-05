package com.aumarbello.carowners.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aumarbello.carowners.utils.Event
import kotlinx.coroutines.launch

abstract class BaseViewModel <T> : ViewModel() {
    private val _loader = MutableLiveData<Boolean>()
    val loader: LiveData<Boolean> = _loader

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _response = MutableLiveData<List<T>>()
    val response: LiveData<List<T>> = _response

    protected fun loadData(block: suspend () -> List<T>) {
        viewModelScope.launch {
            try {
                _loader.value = true
                _response.value = block()
            } catch (ex: Throwable) {
                ex.printStackTrace()

                _error.value = Event(resolveErrorMessage(ex) ?: "An error occurred")
            } finally {
                _loader.value = false
            }
        }
    }

    private fun resolveErrorMessage(throwable: Throwable): String? {
        return throwable.message
    }
}