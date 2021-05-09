package com.bayuspace.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy

class AcademyViewModel(private val repo: AcademyRepository) : ViewModel(){
    fun getCourses(): LiveData<List<CourseEntity>> = repo.getAllCourses()
}