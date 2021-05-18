package com.bayuspace.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bayuspace.academy.data.source.remote.response.ContentResponse
import com.bayuspace.academy.data.source.remote.response.CourseResponse
import com.bayuspace.academy.data.source.remote.response.ModuleResponse
import com.bayuspace.academy.utils.EspressoIdlingResources
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

    fun getAllCourses(): LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResources.increment()
        val courseResult = MutableLiveData<ApiResponse<List<CourseResponse>>>()
        handler.postDelayed(
            {
                courseResult.value = ApiResponse.success(jsonHelper.loadCourses())
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
        return courseResult
    }

    fun getModule(courseId: String): LiveData<ApiResponse<List<ModuleResponse>>> {
        EspressoIdlingResources.increment()
        val moduleResult = MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        handler.postDelayed(
            {
                moduleResult.value = ApiResponse.success(jsonHelper.loadModule(courseId))
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
        return moduleResult
    }

    fun getContent(moduleId: String): LiveData<ApiResponse<ContentResponse>> {
        EspressoIdlingResources.increment()
        val contentResult = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed(
            {
                contentResult.value = ApiResponse.success(jsonHelper.loadContent(moduleId)!!)
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS.toLong()
        )
        return contentResult
    }

}