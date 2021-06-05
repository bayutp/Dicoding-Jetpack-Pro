package com.example.dicodingjetpackpro.ui.home.detail

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseFragment
import com.example.dicodingjetpackpro.databinding.FragmentDetailBinding
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.model.response.tv.TvResult
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.utils.*
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private lateinit var _binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var movieId: Int? = null
    private var tvId: Int? = null
    private var bookmarked = false
    private var isBookmark = MutableLiveData<Boolean>()
    private fun observeBookmark(): LiveData<Boolean> = isBookmark

    override fun onViewReady(savedInstanceState: Bundle?) {
        movieId = requireArguments().getInt("movie_id")
        tvId = requireArguments().getInt("tv_id")

        movieId?.let { id ->
            if (id != 0) {
                detailViewModel.apply {
                    getMovieDetail(id)
                    getSimilarMovies(id)
                    checkMovieBookmark(id)
                    _binding.tvRelatedMovie.text = getString(R.string.related_movies)
                }
            }
        }

        tvId?.let { id ->
            if (id != 0) {
                detailViewModel.apply {
                    getTvDetail(id)
                    getSimilarTvs(id)
                    checkTvBookmark(id)
                    _binding.tvRelatedMovie.text = getString(R.string.related_tv)
                }

            }
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
                        "%s (IMDb)",
                        data.voteAverage.toString()
                    )
                    tvPopularityDetail.text = data.popularity.toString()
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

                    ivBookmark.setOnClickListener {
                        detailViewModel.addMovieToBookmark(
                            MovieEntity.mapToMovieEntity(
                                data,
                                !bookmarked
                            ), !bookmarked
                        )
                    }
                }
            }

            observeBookmark().onResult {
                bookmarked = it
            }

            observeGetSimilarMovieSuccess().onResult {
                val similarAdapter = SimilarMovieAdapter<Result> { result ->
                    detailViewModel.getMovieDetail(result.id)
                    _binding.nsParentDetail.smoothScrollTo(0, 0)
                    _binding.rvRelatedMovie.smoothScrollToPosition(0)
                }
                with(_binding.rvRelatedMovie) {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = similarAdapter
                }
                similarAdapter.setData(it.results)
            }

            observeGetSimilarTvSuccess().onResult {
                val similarAdapter = SimilarMovieAdapter<TvResult> { result ->
                    detailViewModel.getTvDetail(result.id)
                    _binding.nsParentDetail.smoothScrollTo(0, 0)
                    _binding.rvRelatedMovie.smoothScrollToPosition(0)
                }
                with(_binding.rvRelatedMovie) {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = similarAdapter
                }
                similarAdapter.setData(it.results, false)
            }

            observeGetTvDetailSuccess().onResult { data ->
                with(_binding) {
                    nsParentDetail.visible()
                    ivDetail.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.backdropPath}")
                    ivPoster.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.posterPath}")
                    tvTitleDetail.text = data.name
                    tvRatingDetail.text = String.format(
                        "%s (IMDb)",
                        data.voteAverage.toString()
                    )
                    tvPopularityDetail.text = data.popularity.toString()
                    tvRelease.text = data.firstAirDate.formatDate("yyyy-MM-dd", "MMMM dd, yyyy")
                    tvTimeDetail.text = String.format(
                        "%s minutes",
                        if (data.episodeRunTime.isNotEmpty()) data.episodeRunTime[0].toString() else "0"
                    )
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

                    ivBookmark.setOnClickListener {
                        detailViewModel.addTvToBookmark(
                            TvEntity.mapToTvEntity(
                                data,
                                !bookmarked
                            ), !bookmarked
                        )
                    }
                }
            }

            observeAddBookmarkSuccess().onResult {
                changeBookmarkIcon(it)
                requireContext().showMsg(if (it) "Add to bookmark" else "Remove from bookmark")
            }

            observeCheckBookmarkSuccess().onResult {
                changeBookmarkIcon(it)
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

    private fun changeBookmarkIcon(it: Boolean) {
        isBookmark.postValue(it)
        val drawable = if (it) ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_bookmarked
        ) else ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_bookmark
        )
        _binding.ivBookmark.setImageDrawable(drawable)
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