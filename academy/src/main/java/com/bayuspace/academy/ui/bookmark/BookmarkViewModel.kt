package com.bayuspace.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val repo: AcademyRepository) : ViewModel(){
    fun getBookmarks() : LiveData<List<CourseEntity>> = repo.getBookmarkCourses()
}