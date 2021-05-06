package com.bayuspace.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.source.AcademyRepository
import com.bayuspace.academy.utils.DataDummy

class BookmarkViewModel(private val repo: AcademyRepository) : ViewModel(){
    fun getBookmarks() = repo.getAllCourses()
}