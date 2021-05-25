package com.example.dicodingjetpackpro.base

import com.example.dicodingjetpackpro.model.response.Meta

sealed class ResourceState<out T> {
    data class Success<out T>(val result: T) : ResourceState<T>()
    data class Error<T>(val error: T) : ResourceState<T>()
    object Loading : ResourceState<Nothing>()
}

data class ErrorResponse(val code: Int? = 0, val msg: String? = "", val error:String?="")
data class ResponseWrapper<out T>(
    val status: String?,
    val meta: Meta? = Meta(),
    val data: T?,
    val errorData: ErrorResponse?
)