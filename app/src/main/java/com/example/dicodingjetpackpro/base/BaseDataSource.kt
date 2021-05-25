package com.example.dicodingjetpackpro.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.net.ssl.HttpsURLConnection

abstract class BaseDataSource {
    fun <T> errorState(
        errorCode: Int? = HttpsURLConnection.HTTP_INTERNAL_ERROR,
        msg: String,
        error: String?=""
    ): ResourceState<ResponseWrapper<T>> {
        return ResourceState.Error(
            ResponseWrapper(
                null,
                null,
                null,
                ErrorResponse(errorCode, msg, error)
            )
        )
    }

    suspend fun <T> suspendDataResult(request: suspend () -> ResourceState<T>): ResourceState<T> {
        return withContext(Dispatchers.IO) {
            request.invoke()
        }
    }
}