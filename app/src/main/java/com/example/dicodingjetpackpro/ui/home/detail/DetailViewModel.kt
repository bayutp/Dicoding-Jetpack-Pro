package com.example.dicodingjetpackpro.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.model.response.movie.MovieDetailResponse
import com.example.dicodingjetpackpro.model.response.movie.MovieResponse
import com.example.dicodingjetpackpro.repository.DataRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DataRepository) : BaseViewModel() {
    private val onGetMovieDetailSuccess = MutableLiveData<MovieDetailResponse>()
    private val onGetSimilarMovieSuccess = MutableLiveData<MovieResponse>()
    fun observeGetDMovieDetailSuccess(): LiveData<MovieDetailResponse> = onGetMovieDetailSuccess
    fun observeGetSimilarMovieSuccess(): LiveData<MovieResponse> = onGetSimilarMovieSuccess

    fun getMovieDetail(movieId: String) {
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

    fun getSimilarMovies(movieId: String) {
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
}