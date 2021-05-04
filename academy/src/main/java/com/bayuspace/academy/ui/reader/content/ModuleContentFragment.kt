package com.bayuspace.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.academy.R
import com.bayuspace.academy.data.ContentEntity
import com.bayuspace.academy.data.ModuleEntity
import com.bayuspace.academy.databinding.FragmentModuleContentBinding
import com.bayuspace.academy.ui.reader.CourseReaderViewModel

class ModuleContentFragment : Fragment() {

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                ViewModelProvider.NewInstanceFactory()
            )[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
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