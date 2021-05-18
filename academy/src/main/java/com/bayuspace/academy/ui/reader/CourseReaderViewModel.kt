package com.bayuspace.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.vo.Resource

class CourseReaderViewModel(private val repo: AcademyRepository) : ViewModel() {
    var courseId = MutableLiveData<String>()
    var moduleId = MutableLiveData<String>()

    fun selectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    fun selectedModule(moduleId: String) {
        this.moduleId.value = moduleId
    }

    var modules: LiveData<Resource<List<ModuleEntity>>> =
        Transformations.switchMap(courseId) { mCourseId ->
            repo.getAllModulesByCourse(mCourseId)
        }

    var selectedModule: LiveData<Resource<ModuleEntity>> =
        Transformations.switchMap(moduleId) { mModuleId ->
            repo.getContent(mModuleId)
        }

    fun readContent(moduleEntity: ModuleEntity) {
        repo.setReadModule(moduleEntity)
    }

    fun getModuleSize(): Int {
        return if (modules.value != null) {
            val moduleEntities = modules.value?.data
            moduleEntities?.size ?: 0
        } else 0
    }

    fun nextPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data

            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    moduleId.value = moduleEntities[position + 1].moduleId
                }
            }
        }
    }

    fun prevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data

            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    moduleId.value = moduleEntities[position - 1].moduleId
                }
            }
        }
    }
}