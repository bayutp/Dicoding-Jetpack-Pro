package com.example.dicodingjetpackpro.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val errorResponse = MutableLiveData<ErrorResponse>()
    protected val isLoading = MutableLiveData<Boolean>()
    protected val isFetching = MutableLiveData<Boolean>()
    protected val isEmptyData = MutableLiveData<Boolean>()
    protected val noInternet = MutableLiveData<Boolean>()

    fun observeError(): LiveData<ErrorResponse> = errorResponse
    fun observeLoading(): LiveData<Boolean> = isLoading
    fun observeFetching(): LiveData<Boolean> = isFetching
    fun observeEmptyData(): LiveData<Boolean> = isEmptyData
    fun observeNoConnection() : LiveData<Boolean> = noInternet
}