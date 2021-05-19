package com.bayuspace.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.vo.Resource

class AcademyViewModel(private val repo: AcademyRepository) : ViewModel(){
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = repo.getAllCourses()
}