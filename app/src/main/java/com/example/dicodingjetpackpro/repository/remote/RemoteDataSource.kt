package com.example.dicodingjetpackpro.repository.remote

import com.example.dicodingjetpackpro.api.ApiService
import com.example.dicodingjetpackpro.base.BaseDataSource
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.base.ResponseWrapper
import com.example.dicodingjetpackpro.model.response.BaseResponse
import retrofit2.Response
import java.net.UnknownHostException

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    private suspend fun <T> getResult(request: suspend () -> Response<BaseResponse<T>>): ResourceState<ResponseWrapper<T>> {
        return try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful.not() || body == null) {
                return errorState(response.code(), response.message())
            }

            if (body.meta.code !in (200..201)) {
                return errorState(body.meta.code, body.meta.message.orEmpty())
            }

            return ResourceState.Success(
                ResponseWrapper(
                    null,
                    null,
                    body.data,
                    null
                )
            )
        } catch (e: Exception) {
            errorState(msg = if (e is UnknownHostException) NO_INTERNET else e.localizedMessage.orEmpty())
        }
    }

    companion object{
        const val NO_INTERNET = "No internet connection!"
    }
}