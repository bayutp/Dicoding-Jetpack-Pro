package com.bayuspace.academy.data.source.remote.response

import android.os.Parcelable
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseResponse(
    var id: String,
    var title: String,
    var description: String,
    var date: String,
    var imagePath: String
) : Parcelable {
    companion object {
        fun mapToCourseResponse(data: CourseEntity) = CourseResponse(
            data.courseId,
            data.title,
            data.description,
            data.deadline,
            data.imagePath
        )
    }
}