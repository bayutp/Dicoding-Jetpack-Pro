package com.bayuspace.academy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.ActivityCourseReaderBinding

class CourseReaderActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityCourseReaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}