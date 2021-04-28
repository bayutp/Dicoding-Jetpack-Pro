package com.bayuspace.academy.ui.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    companion object{
        const val EXTRA_COURSE = "extra_course"
    }
}