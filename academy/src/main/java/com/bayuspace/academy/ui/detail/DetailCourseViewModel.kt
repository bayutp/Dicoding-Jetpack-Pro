package com.bayuspace.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy

class DetailCourseViewModel(private val repo: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun selectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> {
        return repo.getCourseWithModules(courseId)
    }

    fun getModules(): LiveData<List<ModuleEntity>> = repo.getAllModulesByCourse(courseId)
}