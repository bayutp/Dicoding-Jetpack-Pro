package com.bayuspace.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.FragmentModuleListBinding
import com.bayuspace.academy.ui.reader.CourseReaderActivity
import com.bayuspace.academy.ui.reader.CourseReaderCallback
import com.bayuspace.academy.utils.DataDummy

class ModuleListFragment : Fragment() {

    private lateinit var binding: FragmentModuleListBinding
    private lateinit var moduleListAdapter: ModuleListAdapter
    private lateinit var courseListCallback: CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moduleListAdapter = ModuleListAdapter { position, data ->
            courseListCallback.moveTo(position, data.moduleId)
        }
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseListCallback = context as CourseReaderActivity
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        Log.d(TAG, "populateRecyclerView: ${modules.size}")
        with(binding) {
            progressBar.visibility = View.GONE
            moduleListAdapter.setModules(modules)
            also {
                with(rvModule) {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@ModuleListFragment.requireContext())
                    adapter = moduleListAdapter
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
        }
    }

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = ModuleListFragment()
    }
}