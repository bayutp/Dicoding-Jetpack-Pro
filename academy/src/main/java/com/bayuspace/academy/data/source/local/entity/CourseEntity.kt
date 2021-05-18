package com.bayuspace.academy.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayuspace.academy.data.source.remote.response.CourseResponse

@Entity(tableName = "courseentities")
data class CourseEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "courseId")
    var courseId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "deadline")
    var deadline: String,

    @ColumnInfo(name = "bookmark")
    var bookmark: Boolean = false,

    @ColumnInfo(name = "imagePath")
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