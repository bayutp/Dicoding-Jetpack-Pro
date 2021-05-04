package com.bayuspace.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    private lateinit var courseId: String

    fun selectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity {
        lateinit var courseEntity: CourseEntity
        val courseEntities = DataDummy.generateDummyCourse()
        for (course in courseEntities) {
            if (course.courseId == courseId) {
                courseEntity = course
            }
        }
        return courseEntity
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}