package com.bayuspace.academy.data.source

import com.bayuspace.academy.data.ContentEntity
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.data.source.remote.RemoteDataSource

open class AcademyRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    AcademyDataSource {
    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getAllCourses(): List<CourseEntity> {
        return remoteDataSource.getAllCourses().map { CourseEntity.mapToCourseEntity(it) }
    }

    override fun getBookmarkCourses(): List<CourseEntity> {
        return remoteDataSource.getAllCourses().map { CourseEntity.mapToCourseEntity(it) }
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val data = remoteDataSource.getAllCourses().map { CourseEntity.mapToCourseEntity(it) }
        lateinit var result: CourseEntity
        for (course in data) {
            if (course.courseId == courseId) {
                result = course
            }
        }
        return result
    }

    override fun getAllModulesByCourse(courseId: String): List<ModuleEntity> {
        return remoteDataSource.getModule(courseId).map { ModuleEntity.mapToModuleEntity(it) }
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val data = remoteDataSource.getModule(courseId).map { ModuleEntity.mapToModuleEntity(it) }
        lateinit var result: ModuleEntity
        for (module in data) {
            if (module.moduleId == moduleId) {
                result = module
                result.contentEntity =
                    ContentEntity(remoteDataSource.getContent(moduleId)?.content ?: "")
            }
        }
        return result
    }
}