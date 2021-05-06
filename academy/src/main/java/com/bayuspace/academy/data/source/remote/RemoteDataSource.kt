package com.bayuspace.academy.data.source.remote

import com.bayuspace.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(jsonHelper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(jsonHelper).apply { instance = this }
            }
    }

    fun getAllCourses() = jsonHelper.loadCourses()

    fun getModule(courseId: String) = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String) = jsonHelper.loadContent(moduleId)
}