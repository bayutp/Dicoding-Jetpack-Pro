package com.example.dicodingjetpackpro.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentMovieBinding
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {

    private lateinit var _binding: FragmentMovieBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewReady(savedInstanceState: Bundle?) {
        homeViewModel.getDiscoverMovies()
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverMovies().onResult {
                _binding.tvResult.text = it.results[0].title
            }
            observeError().onResult {
                _binding.tvResult.text = it.error
            }
            observeLoading().onResult { isLoading ->
                if (isLoading) _binding.homeProgress.visible()
                else _binding.homeProgress.gone()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding.root
    }

}