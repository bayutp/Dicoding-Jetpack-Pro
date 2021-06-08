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
import com.example.dicodingjetpackpro.repository.remote.RemoteDataSource
import com.example.dicodingjetpackpro.utils.IdlingResourceUtils
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
        IdlingResourceUtils.increment()
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getMovieDetail(movieId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetMovieDetailSuccess.postValue(result)
                    }
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    if (state.error.errorData?.msg?.contains(RemoteDataSource.NO_INTERNET) == true) noInternet.postValue(
                        true
                    )
                    else errorResponse.postValue(state.error.errorData)
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Loading -> isLoading.postValue(true)

            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        IdlingResourceUtils.increment()
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getSimilarMovies(movieId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetSimilarMovieSuccess.postValue(result)
                    }
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun getTvDetail(tvId: Int) {
        IdlingResourceUtils.increment()
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getTvDetail(tvId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetTvDetailSuccess.postValue(result)
                    }
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    if (state.error.errorData?.msg?.contains(RemoteDataSource.NO_INTERNET) == true) noInternet.postValue(
                        true
                    )
                    else errorResponse.postValue(state.error.errorData)
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun getSimilarTvs(tvId: Int) {
        IdlingResourceUtils.increment()
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getSimilarTvs(tvId)) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetSimilarTvSuccess.postValue(result)
                    }
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                    IdlingResourceUtils.decrement()
                }
                is ResourceState.Loading -> isLoading.postValue(true)
            }
        }
    }

    fun addMovieToBookmark(data: MovieEntity, isBookmark: Boolean) {
        IdlingResourceUtils.increment()
        viewModelScope.launch {
            when (val state = repository.saveBookmark(listOf(data))) {
                is ResourceState.Success -> onAddBookmarkSuccess.postValue(isBookmark)
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
            IdlingResourceUtils.decrement()
        }
    }

    fun checkMovieBookmark(id: Int) {
        IdlingResourceUtils.increment()
        viewModelScope.launch {
            when (val state = repository.checkMovieBookmarked(id)) {
                is ResourceState.Success -> {
                    val result = state.result.data
                    onCheckBookmarkSuccess.postValue(result != null)
                }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)

            }
        }
        IdlingResourceUtils.decrement()
    }

    fun addTvToBookmark(data: TvEntity, isBookmark: Boolean) {
        IdlingResourceUtils.increment()
        viewModelScope.launch {
            when (val state = repository.saveTvBookmark(listOf(data))) {
                is ResourceState.Success -> onAddBookmarkSuccess.postValue(isBookmark)
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
        IdlingResourceUtils.decrement()
    }

    fun checkTvBookmark(id: Int) {
        IdlingResourceUtils.increment()
        viewModelScope.launch {
            when (val state = repository.checkTvBookmarked(id)) {
                is ResourceState.Success -> {
                    val result = state.result.data
                    onCheckBookmarkSuccess.postValue(result != null)
                }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)

            }
        }
        IdlingResourceUtils.decrement()
    }

}