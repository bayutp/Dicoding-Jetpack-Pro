package com.bayuspace.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.FragmentModuleListBinding
import com.bayuspace.academy.ui.reader.CourseReaderActivity
import com.bayuspace.academy.ui.reader.CourseReaderCallback
import com.bayuspace.academy.ui.reader.CourseReaderViewModel
import com.bayuspace.academy.utils.DataDummy

class ModuleListFragment : Fragment() {

    private lateinit var binding: FragmentModuleListBinding
    private lateinit var moduleListAdapter: ModuleListAdapter
    private lateinit var courseListCallback: CourseReaderCallback
    private lateinit var viewModel : CourseReaderViewModel

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
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
        moduleListAdapter = ModuleListAdapter { position, data ->
            courseListCallback.moveTo(position, data.moduleId)
            viewModel.selectedModule(data.moduleId)
        }
        populateRecyclerView(viewModel.getModules())
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
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = moduleListAdapter
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = ModuleListFragment()
    }
}