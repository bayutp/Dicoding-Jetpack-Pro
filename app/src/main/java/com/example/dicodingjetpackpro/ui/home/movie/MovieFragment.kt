package com.example.dicodingjetpackpro.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentMovieBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.ui.home.MovieAdapter
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.showMsg
import com.example.dicodingjetpackpro.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {

    private lateinit var _binding: FragmentMovieBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter<Result>

    override fun onViewReady(savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter {
            requireContext().showMsg(it.title)
        }
        with(_binding) {
            rvMovie.apply {
                setHasFixedSize(true)
                layoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                adapter = movieAdapter
            }
        }
        homeViewModel.getDiscoverMovies()
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverMovies().onResult {
                movieAdapter.setData(it.results)
            }
            observeError().onResult {
                requireContext().showMsg(it.msg ?: "Error, failed load content")
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