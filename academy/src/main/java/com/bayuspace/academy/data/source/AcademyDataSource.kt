package com.bayuspace.academy.data.source

import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity

interface AcademyDataSource {
    fun getAllCourses() : List<CourseEntity>
    fun getBookmarkCourses() : List<CourseEntity>
    fun getCourseWithModules(courseId: String) : CourseEntity
    fun getAllModulesByCourse(courseId: String) : List<ModuleEntity>
    fun getContent(courseId: String, moduleId: String) : ModuleEntity
}