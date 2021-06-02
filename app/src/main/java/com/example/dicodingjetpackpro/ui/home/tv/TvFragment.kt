package com.example.dicodingjetpackpro.ui.home.tv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentTvBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.model.response.tv.TvResult
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.ui.home.MovieAdapter
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.showMsg
import com.example.dicodingjetpackpro.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : BaseFragment() {

    private lateinit var _binding: FragmentTvBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter<TvResult>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter {
            requireContext().showMsg(it.name)
        }
        with(_binding) {
            rvTv.apply {
                setHasFixedSize(true)
                layoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                adapter = movieAdapter
            }
        }
        homeViewModel.getDiscoverTvs()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverTvs().onResult {
                movieAdapter.setData(it.results, false)
            }

            observeError().onResult {
                requireContext().showMsg(it.msg ?: "")
            }

            observeLoading().onResult { isLoading ->
                if (isLoading) _binding.tvProgress.visible() else _binding.tvProgress.gone()
            }
        }
    }
}