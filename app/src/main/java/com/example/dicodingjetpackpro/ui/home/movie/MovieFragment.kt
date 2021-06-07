package com.example.dicodingjetpackpro.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentMovieBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.ui.home.MainActivity
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
            findNavController().navigate(
                R.id.action_menu_home_to_detailFragment,
                args = bundleOf("movie_id" to it.id),
                navOptions {
                    anim {
                        enter = R.anim.nav_default_pop_enter_anim
                        exit = R.anim.nav_default_exit_anim
                    }
                }
            )
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

            svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null && p0.isNotEmpty()) homeViewModel.searchMovies(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false

            })
        }
        homeViewModel.getDiscoverMovies()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverMovies().onResult {
                movieAdapter.setData(it.results)
                _binding.apply {
                    svMovie.setQuery("", false)
                    svMovie.clearFocus()
                    svMovie.visible()
                    emptyAnimation.gone()
                }
            }
            observeError().onResult {
                requireContext().showMsg(it.msg ?: "Error, failed load content")
            }
            observeLoading().onResult { isLoading ->
                if (isLoading) _binding.homeProgress.visible()
                else _binding.homeProgress.gone()
            }
            observeEmptyData().onResult {
                if (it) {
                    _binding.apply {
                        emptyAnimation.visible()
                        rvMovie.gone()
                    }
                } else {
                    _binding.apply {
                        emptyAnimation.gone()
                        rvMovie.visible()
                    }
                }
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