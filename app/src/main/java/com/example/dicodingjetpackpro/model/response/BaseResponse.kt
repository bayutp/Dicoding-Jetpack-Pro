package com.example.dicodingjetpackpro.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: T?
)