package com.bayuspace.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.utils.DataDummy

class AcademyViewModel : ViewModel(){
    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourse()
}