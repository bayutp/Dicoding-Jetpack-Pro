package com.bayuspace.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.ActivityCourseReaderBinding
import com.bayuspace.academy.ui.reader.content.ModuleContentFragment
import com.bayuspace.academy.ui.reader.list.ModuleListFragment

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    private lateinit var _binding: ActivityCourseReaderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            Log.d("TAG", "courseId: $courseId")
            if (courseId != null)
                populateFragment()
        }
        Log.d("TAG", "bundle: $bundle courseId: ${bundle?.getString(EXTRA_COURSE_ID)}")
    }


    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(_binding.frameContainer.id, fragment, ModuleContentFragment.TAG)
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
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if (fragment != null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(_binding.frameContainer.id, fragment, ModuleListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}