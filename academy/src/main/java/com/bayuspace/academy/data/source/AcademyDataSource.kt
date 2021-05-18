package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.vo.Resource

interface AcademyDataSource {
    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>
    fun getBookmarkCourses(): LiveData<List<CourseEntity>>
    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>
    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>
    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>
    fun setCourseBookmark(courseEntity: CourseEntity, state: Boolean)
    fun setReadModule(module: ModuleEntity)
}