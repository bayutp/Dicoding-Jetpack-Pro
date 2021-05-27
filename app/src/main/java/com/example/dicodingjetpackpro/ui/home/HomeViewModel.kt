package com.example.dicodingjetpackpro.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.model.response.movie.MovieResponse
import com.example.dicodingjetpackpro.repository.DataRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DataRepository) : BaseViewModel() {
    private val onGetDiscoverMoviesSuccess = MutableLiveData<MovieResponse>()
    private val onGetDiscoverTvsSuccess = MutableLiveData<MovieResponse>()

    fun observeDiscoverMovies(): LiveData<MovieResponse> = onGetDiscoverMoviesSuccess
    fun observeDiscoverTvs(): LiveData<MovieResponse> = onGetDiscoverTvsSuccess

    fun getDiscoverMovies() {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getDiscoverMovies()) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetDiscoverMoviesSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                else -> isLoading.postValue(true)
            }
        }
    }

    fun getDiscoverTvs() {
        isLoading.postValue(true)
        viewModelScope.launch {
            when (val state = repository.getDiscoverTvs()) {
                is ResourceState.Success -> {
                    isLoading.postValue(false)
                    state.result.data?.let { result ->
                        onGetDiscoverTvsSuccess.postValue(result)
                    }
                }
                is ResourceState.Error -> {
                    isLoading.postValue(false)
                    errorResponse.postValue(state.error.errorData)
                }
                else -> isLoading.postValue(true)
            }
        }
    }
}