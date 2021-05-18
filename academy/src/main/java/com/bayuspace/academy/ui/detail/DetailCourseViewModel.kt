package com.bayuspace.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.data.source.local.entity.CourseWithModule
import com.bayuspace.academy.vo.Resource

class DetailCourseViewModel(private val repo: AcademyRepository) : ViewModel() {
    val courseId = MutableLiveData<String>()

    fun selectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    var courseModule: LiveData<Resource<CourseWithModule>> =
        Transformations.switchMap(courseId) { mCourseId ->
            repo.getCourseWithModules(mCourseId)
        }


    fun setBookmark() {
        val moduleResource = courseModule.value
        if (moduleResource != null) {
            val courseWithModule = moduleResource.data

            if (courseWithModule != null) {
                val courseEntity = courseWithModule.mCourse
                val state = !courseEntity.bookmark
                repo.setCourseBookmark(courseEntity, state)
            }
        }
    }
}