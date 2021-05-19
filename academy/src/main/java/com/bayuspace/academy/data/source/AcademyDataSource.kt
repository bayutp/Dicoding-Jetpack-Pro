package com.bayuspace.academy.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.vo.Resource

interface AcademyDataSource {
    fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>>
    fun getBookmarkCourses(): LiveData<PagedList<CourseEntity>>
    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>
    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>
    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>
    fun setCourseBookmark(courseEntity: CourseEntity, state: Boolean)
    fun setReadModule(module: ModuleEntity)
}