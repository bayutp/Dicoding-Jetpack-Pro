package com.bayuspace.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.bayuspace.academy.data.source.remote.response.ContentResponse
import com.bayuspace.academy.data.source.remote.response.CourseResponse
import com.bayuspace.academy.data.source.remote.response.ModuleResponse
import com.bayuspace.academy.utils.JsonHelper

open class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS = 2_000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(jsonHelper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(jsonHelper).apply { instance = this }
            }
    }

    fun getAllCourses(callback: LoadCourseCallback) {
        handler.postDelayed(
            { callback.onAllCoursesReceived(jsonHelper.loadCourses()) },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
    }

    fun getModule(courseId: String, callback: LoadModuleCallback) {
        handler.postDelayed(
            { callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        handler.postDelayed(
            { callback.onContentReceived(jsonHelper.loadContent(moduleId)) },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
    }

    interface LoadCourseCallback {
        fun onAllCoursesReceived(data: List<CourseResponse>)
    }

    interface LoadModuleCallback {
        fun onAllModulesReceived(data: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(data: ContentResponse?)
    }

}