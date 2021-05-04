package com.bayuspace.academy.ui.academy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.academy.R
import com.bayuspace.academy.databinding.FragmentAcademyBinding
import com.bayuspace.academy.databinding.ItemsAcademyBinding
import com.bayuspace.academy.ui.detail.DetailActivity
import com.bayuspace.academy.utils.DataDummy

private lateinit var academyAdapter: AcademyAdapter

class AcademyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        academyAdapter = AcademyAdapter { course ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_COURSE, course.courseId)
            requireContext().startActivity(intent)
        }
        val course = DataDummy.generateDummyCourse()
        academyAdapter.setCourse(course)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentAcademyBinding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        if (activity != null) {
            with(fragmentAcademyBinding.rvAcademy) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = academyAdapter
            }
        }
        return fragmentAcademyBinding.root
    }
}