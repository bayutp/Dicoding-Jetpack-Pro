package com.bayuspace.academy.data

import com.bayuspace.academy.data.source.remote.response.CourseResponse

data class CourseEntity(
    var courseId: String,
    var title: String,
    var description: String,
    var deadline: String,
    var bookmark: Boolean = false,
    var imagePath: String
) {
    companion object {
        fun mapToCourseEntity(courseResponse: CourseResponse) =
            CourseEntity(
                courseResponse.id,
                courseResponse.title,
                courseResponse.description,
                courseResponse.date,
                false,
                courseResponse.imagePath
            )
    }
}