package com.bayuspace.academy.vo

data class Resource<T>(val status: Status, val data: T?, val msg: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }
}