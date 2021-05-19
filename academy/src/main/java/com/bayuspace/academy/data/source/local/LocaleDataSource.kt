package com.bayuspace.academy.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bayuspace.academy.data.source.local.dao.AcademyDao
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.data.source.local.entity.ModuleEntity

class LocaleDataSource private constructor(private val academyDao: AcademyDao) {
    companion object {
        private var INSTANCE: LocaleDataSource? = null

        fun getInstance(academyDao: AcademyDao): LocaleDataSource =
            INSTANCE ?: LocaleDataSource(academyDao)
    }

    fun getAllCourses(): DataSource.Factory<Int, CourseEntity> = academyDao.getCourses()

    fun getBookmarkedCourses(): DataSource.Factory<Int, CourseEntity> = academyDao.getBookmarkedCourse()

    fun getCourseWithModules(courseId: String): LiveData<CourseWithModule> =
        academyDao.getCourseWithModuleById(courseId)

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> =
        academyDao.getModulesByCourseId(courseId)

    fun insertCourses(courses: List<CourseEntity>) = academyDao.insertCourse(courses)

    fun insertModules(modules: List<ModuleEntity>) = academyDao.insertModules(modules)

    fun setCourseBookmark(course: CourseEntity, newState: Boolean) {
        course.bookmark = newState
        academyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> =
        academyDao.getModuleById(moduleId)

    fun updateContent(content: String, moduleId: String) {
        academyDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.read = true
        academyDao.updateModule(module)
    }
}