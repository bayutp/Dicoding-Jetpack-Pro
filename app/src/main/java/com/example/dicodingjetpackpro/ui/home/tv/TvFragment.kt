package com.example.dicodingjetpackpro.ui.home.tv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentTvBinding
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : BaseFragment() {

    private lateinit var _binding: FragmentTvBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        homeViewModel.getDiscoverTvs()
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverTvs().onResult {
                _binding.tvResult.text = it.results[0].name
            }

            observeError().onResult {
                _binding.tvResult.text = it.error
            }

            observeLoading().onResult { isLoading ->
                if (isLoading) _binding.tvProgress.visible() else _binding.tvProgress.gone()
            }
        }
    }
}