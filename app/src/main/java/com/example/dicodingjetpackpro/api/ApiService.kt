package com.example.dicodingjetpackpro.api

import com.example.dicodingjetpackpro.model.response.BaseResponse
import retrofit2.Response

interface ApiService {

}

typealias SuccessCallback<T> = Response<BaseResponse<T>>