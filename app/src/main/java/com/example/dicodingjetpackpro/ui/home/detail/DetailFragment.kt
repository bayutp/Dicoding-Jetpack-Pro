package com.example.dicodingjetpackpro.ui.home.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentDetailBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.utils.*
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private lateinit var _binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var movieId = ""

    override fun onViewReady(savedInstanceState: Bundle?) {
        movieId = requireArguments().getInt("movie_id").toString()
        if (movieId.isNotEmpty()) {
            detailViewModel.getMovieDetail(movieId)
            detailViewModel.getSimilarMovies(movieId)
        }
        (activity as MainActivity).hideBottomNavigation()
        hideToolbar(true)
        _binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeData() {
        with(detailViewModel) {
            observeGetDMovieDetailSuccess().onResult { data ->
                with(_binding) {
                    nsParentDetail.visible()
                    ivDetail.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.backdropPath}")
                    ivPoster.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.posterPath}")
                    tvTitleDetail.text = data.title
                    tvRatingDetail.text = String.format(
                        "%s (%s)",
                        data.voteAverage.toString(),
                        data.voteCount.toString()
                    )
                    tvRelease.text = data.releaseDate.formatDate("yyyy-MM-dd", "MMMM dd, yyyy")
                    tvTimeDetail.text = String.format("%s minutes", data.runtime.toString())
                    tvSynopsis.text = data.overview

                    data.genres.forEach {
                        val chips: Chip =
                            layoutInflater.inflate(R.layout.item_genre, null, false) as Chip
                        chips.text = it.name
                        val padding = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 8F,
                            resources.displayMetrics
                        ).toInt()
                        chips.setPadding(padding, 0, padding, 0)
                        _binding.chipsGenre.addView(chips)
                    }
                }
            }

            observeGetSimilarMovieSuccess().onResult {
                val similarAdapter = SimilarMovieAdapter<Result> {
                    detailViewModel.getMovieDetail(it.id.toString())
                    _binding.nsParentDetail.smoothScrollTo(0, 0)
                }
                with(_binding.rvRelatedMovie) {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = similarAdapter
                }
                similarAdapter.setData(it.results)
            }

            observeError().onResult {
                _binding.nsParentDetail.gone()
                requireContext().showMsg(it.msg ?: "Error, failed load content")
            }

            observeLoading().onResult { state ->
                if (state) {
                    _binding.nsParentDetail.gone()
                    _binding.progressDetail.visible()
                } else {
                    _binding.nsParentDetail.visible()
                    _binding.progressDetail.gone()
                }
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