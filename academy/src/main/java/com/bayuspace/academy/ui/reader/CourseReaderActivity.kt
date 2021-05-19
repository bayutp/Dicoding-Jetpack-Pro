package com.bayuspace.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.academy.R
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.databinding.ActivityCourseReaderBinding
import com.bayuspace.academy.ui.reader.content.ModuleContentFragment
import com.bayuspace.academy.ui.reader.list.ModuleListFragment
import com.bayuspace.academy.viewmodel.ViewModelFactory
import com.bayuspace.academy.vo.Resource
import com.bayuspace.academy.vo.Status

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    private lateinit var _binding: ActivityCourseReaderBinding
    private lateinit var viewModel: CourseReaderViewModel
    private var isLarge = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCourseReaderBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_course_reader)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[CourseReaderViewModel::class.java]

        if (findViewById<View>(R.id.frame_list) != null) isLarge = true

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
        if (!isLarge) {
            val fragment = ModuleContentFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_list, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!isLarge) {
            var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.tag)
            if (fragment == null) {
                fragment = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragment, ModuleListFragment.tag)
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commit()
        } else {
            var fragmentList = supportFragmentManager.findFragmentByTag(ModuleListFragment.tag)
            if (fragmentList == null) {
                fragmentList = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragmentList, ModuleListFragment.tag)
            }
            var fragmentContent =
                supportFragmentManager.findFragmentByTag(ModuleContentFragment.TAG)
            if (fragmentContent == null) {
                fragmentContent = ModuleContentFragment.newInstance()
                fragmentTransaction.add(
                    R.id.frame_content,
                    fragmentContent,
                    ModuleContentFragment.TAG
                )
            }
            fragmentTransaction.commit()
        }
    }

    private fun removeObserver() {
        viewModel.modules.removeObserver(initObserver)
    }

    private fun getLastReadFromModules(moduleEntities: List<ModuleEntity>): String? {
        var lastReadModule: String? = null
        for (module in moduleEntities) {
            if (module.read) {
                lastReadModule = module.moduleId
                continue
            }
            break
        }
        return lastReadModule
    }

    private val initObserver:Observer<Resource<List<ModuleEntity>>> = Observer{ modules ->
        if (modules != null) {
            when (modules.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val dataModules: List<ModuleEntity>? = modules.data
                    if (dataModules != null && dataModules.isNotEmpty()) {
                        val firstModule = dataModules[0]
                        val isFirstModuleRead = firstModule.read
                        if (!isFirstModuleRead) {
                            val firstModuleId = firstModule.moduleId
                            viewModel.selectedModule(firstModuleId)
                        } else {
                            val lastReadModuleId = getLastReadFromModules(dataModules)
                            if (lastReadModuleId != null) {
                                viewModel.selectedModule(lastReadModuleId)
                            }
                        }
                    }
                    removeObserver()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Gagal menampilkan data.", Toast.LENGTH_SHORT).show()
                    removeObserver()
                }
            }
        }
    }
}