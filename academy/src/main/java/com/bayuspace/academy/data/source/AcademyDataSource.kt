package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity

interface AcademyDataSource {
    fun getAllCourses(): LiveData<List<CourseEntity>>
    fun getBookmarkCourses(): LiveData<List<CourseEntity>>
    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>
    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>
    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}