package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bayuspace.academy.data.source.remote.RemoteDataSource
import com.bayuspace.academy.data.source.remote.response.ContentResponse
import com.bayuspace.academy.data.source.remote.response.CourseResponse
import com.bayuspace.academy.data.source.remote.response.ModuleResponse

class FakeAcademyRepository(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {
    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            override fun onAllCoursesReceived(data: List<CourseResponse>) {
                courseResult.postValue(data.map { CourseEntity.mapToCourseEntity(it) })
            }
        })
        return courseResult
    }

    override fun getBookmarkCourses(): LiveData<List<CourseEntity>> {
        val bookmarkResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            override fun onAllCoursesReceived(data: List<CourseResponse>) {
                bookmarkResult.postValue(data.map { CourseEntity.mapToCourseEntity(it) })
            }
        })
        return bookmarkResult
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult = MutableLiveData<CourseEntity>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
            override fun onAllCoursesReceived(data: List<CourseResponse>) {
                val temp = data.map { CourseEntity.mapToCourseEntity(it) }
                lateinit var result: CourseEntity
                for (course in temp) {
                    if (course.courseId == courseId) {
                        result = course
                    }
                }
                courseResult.postValue(result)
            }

        })
        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()
        remoteDataSource.getModule(courseId, object : RemoteDataSource.LoadModuleCallback {
            override fun onAllModulesReceived(data: List<ModuleResponse>) {
                moduleResults.postValue(data.map { ModuleEntity.mapToModuleEntity(it) })
            }
        })
        return moduleResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()
        remoteDataSource.getModule(courseId, object : RemoteDataSource.LoadModuleCallback {
            override fun onAllModulesReceived(data: List<ModuleResponse>) {
                val temp = data.map { ModuleEntity.mapToModuleEntity(it) }
                lateinit var result: ModuleEntity
                for (module in temp) {
                    if (module.moduleId == moduleId) {
                        result = module
                        remoteDataSource.getContent(
                            moduleId,
                            object : RemoteDataSource.LoadContentCallback {
                                override fun onContentReceived(data: ContentResponse?) {
                                    result.contentEntity = ContentEntity(data?.content ?: "")
                                    moduleResult.postValue(result)
                                }

                            })
                    }
                }
            }

        })

        return moduleResult
    }
}