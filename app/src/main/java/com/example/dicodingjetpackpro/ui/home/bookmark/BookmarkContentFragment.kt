package com.example.dicodingjetpackpro.ui.home.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.paging.PagedList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentMovieBinding
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.utils.gone
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"

class BookmarkContentFragment : BaseFragment() {
    private var param1: String? = null
    private lateinit var _binding: FragmentMovieBinding
    private val viewModel: BookmarkViewModel by viewModel()
    private var movieAdapter: BookmarkAdapter? = null
    private var tvAdapter: BookmarkTvAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        with(_binding){
            tvTitle.gone()
            svMovie.gone()
        }
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
                movieAdapter = BookmarkAdapter {
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
            }
            "tv" -> {
                tvAdapter = BookmarkTvAdapter {
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
            }
        }

    }

    override fun observeData() {
        with(viewModel) {
            getMovies().observe(this@BookmarkContentFragment, movieObserver)
            getTvs().observe(this@BookmarkContentFragment, tvObserver)
        }
    }

    private val movieObserver = Observer<PagedList<MovieEntity>> {
        if (it != null) {
            movieAdapter?.submitList(it)
        }
    }

    private val tvObserver = Observer<PagedList<TvEntity>> {
        if (it != null) {
            tvAdapter?.submitList(it)
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