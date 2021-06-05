package com.example.dicodingjetpackpro.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.model.response.movie.MovieDetailResponse
import com.example.dicodingjetpackpro.model.response.movie.MovieResponse
import com.example.dicodingjetpackpro.model.response.tv.TvDetailResponse
import com.example.dicodingjetpackpro.model.response.tv.TvResponse
import com.example.dicodingjetpackpro.repository.DataRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DataRepository) : BaseViewModel() {
    private val onGetMovieDetailSuccess = MutableLiveData<MovieDetailResponse>()
    private val onGetSimilarMovieSuccess = MutableLiveData<MovieResponse>()
    private val onGetTvDetailSuccess = MutableLiveData<TvDetailResponse>()
    private val onGetSimilarTvSuccess = MutableLiveData<TvResponse>()
    private val onAddBookmarkSuccess = MutableLiveData<Boolean>()
    private val onCheckBookmarkSuccess = MutableLiveData<Boolean>()

    fun observeGetDMovieDetailSuccess(): LiveData<MovieDetailResponse> = onGetMovieDetailSuccess
    fun observeGetSimilarMovieSuccess(): LiveData<MovieResponse> = onGetSimilarMovieSuccess
    fun observeGetTvDetailSuccess(): LiveData<TvDetailResponse> = onGetTvDetailSuccess
    fun observeGetSimilarTvSuccess(): LiveData<TvResponse> = onGetSimilarTvSuccess
    fun observeAddBookmarkSuccess(): LiveData<Boolean> = onAddBookmarkSuccess
    fun observeCheckBookmarkSuccess(): LiveData<Boolean> = onCheckBookmarkSuccess

    fun getMovieDetail(movieId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getMovieDetail(movieId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetMovieDetailSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                is ResourceState.Loading -> isLoading.postValue(true)

            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getSimilarMovies(movieId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetSimilarMovieSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun getTvDetail(tvId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getTvDetail(tvId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetTvDetailSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun getSimilarTvs(tvId: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getSimilarTvs(tvId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetSimilarTvSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun addMovieToBookmark(data: MovieEntity, isBookmark: Boolean) {
        viewModelScope.launch {
            when (val state = repository.saveBookmark(listOf(data))) {
                is ResourceState.Success -> onAddBookmarkSuccess.postValue(isBookmark)
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
    }

    fun checkMovieBookmark(id: Int) {
        viewModelScope.launch {
            when (val state = repository.getMovieBookmarked()) {
                is ResourceState.Success -> {
                    val result = state.result.data?.find { it.id == id }
                    onCheckBookmarkSuccess.postValue(result != null)
                }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)

            }
        }
    }

    fun addTvToBookmark(data: TvEntity, isBookmark: Boolean) {
        viewModelScope.launch {
            when (val state = repository.saveTvBookmark(listOf(data))) {
                is ResourceState.Success -> onAddBookmarkSuccess.postValue(isBookmark)
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
    }

    fun checkTvBookmark(id: Int) {
        viewModelScope.launch {
            when (val state = repository.getTvBookmarked()) {
                is ResourceState.Success -> {
                    val result = state.result.data?.find { it.id == id }
                    onCheckBookmarkSuccess.postValue(result != null)
                }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)

            }
        }
    }

}