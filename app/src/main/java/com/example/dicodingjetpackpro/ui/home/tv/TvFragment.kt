package com.example.dicodingjetpackpro.ui.home.tv

import android.content.Intent
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
import com.example.dicodingjetpackpro.databinding.FragmentTvBinding
import com.example.dicodingjetpackpro.model.response.tv.TvResult
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.ui.home.MovieAdapter
import com.example.dicodingjetpackpro.ui.nointernet.NoInternetActivity
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
            findNavController().navigate(
                R.id.action_menu_tv_to_detailFragment,
                args = bundleOf("tv_id" to it.id),
                navOptions {
                    anim {
                        enter = android.R.anim.fade_in
                        exit = android.R.anim.fade_out
                    }
                })
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

            svTv.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null && p0.isNotEmpty()) homeViewModel.searchTvs(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false

            })
        }
        homeViewModel.getDiscoverTvs()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    override fun observeData() {
        with(homeViewModel) {
            observeDiscoverTvs().onResult {
                movieAdapter.setData(it.results, false)
                _binding.apply {
                    svTv.setQuery("", false)
                    svTv.clearFocus()
                    rvTv.visible()
                    emptyAnimation.gone()
                }
            }

            observeError().onResult {
                requireContext().showMsg(it.msg ?: "")
            }

            observeLoading().onResult { isLoading ->
                if (isLoading) _binding.tvProgress.visible() else _binding.tvProgress.gone()
            }
            observeEmptyData().onResult {
                if (it) {
                    _binding.apply {
                        emptyAnimation.visible()
                        rvTv.gone()
                    }
                } else {
                    _binding.apply {
                        emptyAnimation.gone()
                        rvTv.visible()
                    }
                }
                _binding.svTv.clearFocus()
            }
            observeNoConnection().onResult {
                startActivity(Intent(requireActivity(), NoInternetActivity::class.java))
            }
        }

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getDiscoverTvs()
    }
}