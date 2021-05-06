package com.bayuspace.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.ActivityCourseReaderBinding
import com.bayuspace.academy.ui.reader.content.ModuleContentFragment
import com.bayuspace.academy.ui.reader.list.ModuleListFragment
import com.bayuspace.academy.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    private lateinit var _binding: ActivityCourseReaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_course_reader)

        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[CourseReaderViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null)
                viewModel.selectedCourse(courseId)
                populateFragment()
        }
    }


    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else super.onBackPressed()
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.tag)
        if (fragment == null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.tag)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}