package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import com.bayuspace.academy.data.NetworkBoundResource
import com.bayuspace.academy.data.source.local.LocaleDataSource
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.data.source.remote.ApiResponse
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.data.source.remote.response.ContentResponse
import com.bayuspace.academy.data.source.remote.response.CourseResponse
import com.bayuspace.academy.data.source.remote.response.ModuleResponse
import com.bayuspace.academy.utils.AppExecutor
import com.bayuspace.academy.vo.Resource

open class AcademyRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocaleDataSource,
    private val appExecutor: AppExecutor
) :
    AcademyDataSource {
    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocaleDataSource,
            appExecutor: AppExecutor
        ): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutor
                ).apply { instance = this }
            }
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object :
            NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutor) {
            override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localDataSource.getAllCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteDataSource.getAllCourses()
            }

            override fun saveCallResult(data: List<CourseResponse>) {
                localDataSource.insertCourses(data.map {
                    CourseEntity.mapToCourseEntity(it)
                })
            }

        }.asLiveData()
    }

    override fun getBookmarkCourses(): LiveData<List<CourseEntity>> {
        return localDataSource.getBookmarkedCourses()
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutor) {
            override fun loadFromDB(): LiveData<CourseWithModule> {
                return localDataSource.getCourseWithModules(courseId)
            }

            override fun shouldFetch(data: CourseWithModule?): Boolean {
                return data?.mModules == null || data.mModules.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModule(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                localDataSource.insertModules(data.map { ModuleEntity.mapToModuleEntity(it) })
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object :
            NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutor) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> {
                return localDataSource.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModule(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {
                localDataSource.insertModules(data.map { ModuleEntity.mapToModuleEntity(it) })
            }

        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutor) {
            override fun loadFromDB(): LiveData<ModuleEntity> {
                return localDataSource.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(data: ModuleEntity?): Boolean {
                return data?.contentEntity == null
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                return remoteDataSource.getContent(moduleId)
            }

            override fun saveCallResult(data: ContentResponse) {
                localDataSource.updateContent(data.content, moduleId)
            }

        }.asLiveData()
    }

    override fun setCourseBookmark(courseEntity: CourseEntity, state: Boolean) {
        appExecutor.diskIO().execute {
            localDataSource.setCourseBookmark(courseEntity, state)
        }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutor.diskIO().execute {
            localDataSource.setReadModule(module)
        }
    }
}