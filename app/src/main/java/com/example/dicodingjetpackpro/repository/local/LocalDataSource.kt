package com.example.dicodingjetpackpro.repository.local

import com.example.dicodingjetpackpro.base.BaseDataSource
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.base.ResponseWrapper

class LocalDataSource(private val localDatabase: LocalDatabase) : BaseDataSource() {
    private suspend fun <T> getResult(request: suspend () -> T): ResourceState<ResponseWrapper<T>> {
        return try {
            val res = request.invoke()
            return ResourceState.Success(ResponseWrapper("OK", data = res, errorData = null))
        } catch (e: Exception) {
            errorState(msg = e.toString())
        }
    }
}