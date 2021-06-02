package com.example.dicodingjetpackpro.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentDetailBinding
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.utils.gone
import com.example.dicodingjetpackpro.utils.showMsg
import com.example.dicodingjetpackpro.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private lateinit var _binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var movieId = ""

    override fun onViewReady(savedInstanceState: Bundle?) {
        movieId = requireArguments().getInt("movie_id").toString()
        if (movieId.isNotEmpty()) {
            detailViewModel.getMovieDetail(movieId)
        }
        (activity as MainActivity).hideBottomNavigation()
        hideToolbar(true)
    }

    override fun observeData() {
        with(detailViewModel) {
            observeGetDMovieDetailSuccess().onResult {

            }
            observeError().onResult {
                requireContext().showMsg(it.msg ?: "Error, failed load content")
            }

            observeLoading().onResult { state ->
                if (state) _binding.progressDetail.visible() else _binding.progressDetail.gone()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

}