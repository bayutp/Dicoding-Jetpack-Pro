package com.bayuspace.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.academy.R
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.databinding.ActivityDetailBinding
import com.bayuspace.academy.databinding.ContainDetailCourseBinding
import com.bayuspace.academy.ui.reader.CourseReaderActivity
import com.bayuspace.academy.utils.DataDummy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBinding
    private lateinit var _containBinding: ContainDetailCourseBinding
    private lateinit var detailAdapter: DetailCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        _containBinding = _binding.detailContain
        setContentView(_binding.root)
        setSupportActionBar(_binding.toolbar)


        supportActionBar?.setDisplayShowHomeEnabled(true)
        detailAdapter = DetailCourseAdapter()

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                val module = DataDummy.generateDummyModules(courseId)
                detailAdapter.setModule(module)
                for (course in DataDummy.generateDummyCourse()) {
                    populateCourse(course)
                }
            }
        }

        with(_containBinding.rvModule) {
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = detailAdapter
            val itemDecoration =
                DividerItemDecoration(this@DetailActivity, DividerItemDecoration.HORIZONTAL)
            addItemDecoration(itemDecoration)
        }

    }

    private fun populateCourse(courseEntity: CourseEntity) {
        _containBinding.textTitle.text = courseEntity.title
        _containBinding.textDescription.text = courseEntity.description
        _containBinding.textDate.text =
            resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(_containBinding.imagePoster)

        _containBinding.btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }
}