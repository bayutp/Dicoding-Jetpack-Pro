package com.bayuspace.academy.ui.reader

import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.ContentEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy

class CourseReaderViewModel(private val repo: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun selectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun selectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules() = repo.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity {
        return repo.getContent(courseId, moduleId)
    }
}