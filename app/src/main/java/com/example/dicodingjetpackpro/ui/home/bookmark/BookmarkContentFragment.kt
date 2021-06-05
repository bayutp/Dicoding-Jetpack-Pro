package com.example.dicodingjetpackpro.ui.home.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentMovieBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.model.response.tv.TvResult
import com.example.dicodingjetpackpro.ui.home.MovieAdapter
import com.example.dicodingjetpackpro.utils.showMsg
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"

class BookmarkContentFragment : BaseFragment() {
    private var param1: String? = null
    private lateinit var _binding: FragmentMovieBinding
    private val viewModel: BookmarkViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter<Result>
    private lateinit var tvAdapter: MovieAdapter<TvResult>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        with(_binding.rvMovie) {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        }

        when (param1) {
            "movie" -> {
                movieAdapter = MovieAdapter {
                    findNavController().navigate(
                        R.id.action_menu_bookmark_to_detailFragment,
                        args = bundleOf("movie_id" to it.id),
                        navOptions {
                            anim {
                                enter = R.anim.nav_default_pop_enter_anim
                                exit = R.anim.nav_default_exit_anim
                            }
                        })
                }
                _binding.rvMovie.adapter = movieAdapter
                viewModel.getMovies()
            }
            "tv" -> {
                tvAdapter = MovieAdapter {
                    findNavController().navigate(
                        R.id.action_menu_bookmark_to_detailFragment,
                        args = bundleOf("tv_id" to it.id),
                        navOptions {
                            anim {
                                enter = android.R.anim.fade_in
                                exit = android.R.anim.fade_out
                            }
                        })
                }
                _binding.rvMovie.adapter = tvAdapter
                viewModel.getTvs()
            }
        }

    }

    override fun observeData() {
        with(viewModel) {
            observeGetMoviesSuccess().onResult { data ->
                movieAdapter.setData(
                    data.map {
                        Result(
                            id = it.id,
                            posterPath = it.posterPath,
                            releaseDate = it.releaseDate,
                            title = it.title
                        )
                    }
                )
            }
            observeGetTvSuccess().onResult { data ->
                tvAdapter.setData(
                    data.map {
                        TvResult(
                            firstAirDate = it.firstAirDate,
                            id = it.id,
                            name = it.name,
                            posterPath = it.posterPath
                        )
                    }, false
                )
            }
            observeError().onResult {
                requireContext().showMsg(it.msg ?: "Error!, something wrong")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            BookmarkContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}