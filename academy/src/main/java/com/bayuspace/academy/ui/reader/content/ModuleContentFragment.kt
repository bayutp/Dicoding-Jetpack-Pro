package com.bayuspace.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.FragmentModuleContentBinding
import com.bayuspace.academy.ui.reader.CourseReaderViewModel
import com.bayuspace.academy.viewmodel.ViewModelFactory

class ModuleContentFragment : Fragment() {

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentModuleContentBinding =
            FragmentModuleContentBinding.inflate(layoutInflater, container, false)
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(
                requireActivity(),
                ViewModelFactory.getInstance(requireActivity())
            )[CourseReaderViewModel::class.java]
            fragmentModuleContentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(requireActivity(), {
                fragmentModuleContentBinding.progressBar.visibility = View.GONE
                populateWebView(it)
            })
        }
    }

    private fun populateWebView(data: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(
            data.contentEntity?.content ?: "",
            "text/html",
            "UTF-8"
        )
    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName.toString()

        @JvmStatic
        fun newInstance() = ModuleContentFragment()
    }
}