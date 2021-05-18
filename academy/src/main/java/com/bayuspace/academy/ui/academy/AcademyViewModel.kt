package com.bayuspace.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.vo.Resource

class AcademyViewModel(private val repo: AcademyRepository) : ViewModel(){
    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = repo.getAllCourses()
}