package com.bayuspace.academy.data

data class CourseEntity(
    var courseId: String,
    var title: String,
    var description: String,
    var deadline: String,
    var bookmark: Boolean = false,
    var imagePath: String
)