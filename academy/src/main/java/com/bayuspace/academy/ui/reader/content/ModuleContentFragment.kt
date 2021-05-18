package com.bayuspace.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.academy.data.source.local.entity.ModuleEntity
import com.bayuspace.academy.databinding.FragmentModuleContentBinding
import com.bayuspace.academy.ui.reader.CourseReaderViewModel
import com.bayuspace.academy.viewmodel.ViewModelFactory
import com.bayuspace.academy.vo.Status

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
            viewModel.selectedModule.observe(requireActivity(), {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> fragmentModuleContentBinding.progressBar.visibility =
                            View.GONE
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                fragmentModuleContentBinding.progressBar.visibility = View.GONE
                                if (it.data.contentEntity != null) {
                                    populateWebView(it.data)
                                }
                                setButtonNextPrevState(it.data)
                                if (!it.data.read) viewModel.readContent(it.data)
                            }
                        }
                        Status.ERROR -> {
                            fragmentModuleContentBinding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
            fragmentModuleContentBinding.btnNext.setOnClickListener { viewModel.nextPage() }
            fragmentModuleContentBinding.btnPrev.setOnClickListener { viewModel.prevPage() }
        }
    }

    private fun populateWebView(data: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(
            data.contentEntity?.content ?: "",
            "text/html",
            "UTF-8"
        )
    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            when (module.position) {
                0 -> {
                    fragmentModuleContentBinding.btnPrev.isEnabled = false
                    fragmentModuleContentBinding.btnNext.isEnabled = true
                }
                viewModel.getModuleSize() - 1 -> {
                    fragmentModuleContentBinding.btnPrev.isEnabled = true
                    fragmentModuleContentBinding.btnNext.isEnabled = false
                }
                else -> {
                    fragmentModuleContentBinding.btnPrev.isEnabled = true
                    fragmentModuleContentBinding.btnNext.isEnabled = true
                }
            }
        }
    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName.toString()

        @JvmStatic
        fun newInstance() = ModuleContentFragment()
    }
}