package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

class FakeAcademyRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocaleDataSource,
    private val appExecutor: AppExecutor
) : AcademyDataSource {

    override fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CourseEntity>, List<CourseResponse>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<CourseEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllCourses(), config).build()
            }

            override fun shouldFetch(data: PagedList<CourseEntity>?): Boolean {
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

    override fun getBookmarkCourses(): LiveData<PagedList<CourseEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedCourses(), config).build()
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