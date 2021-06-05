package com.example.dicodingjetpackpro.ui.home.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.repository.DataRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: DataRepository) : BaseViewModel() {
    private var onGetMoviesSuccess = MutableLiveData<List<MovieEntity>>()
    private var onGetTvSuccess = MutableLiveData<List<TvEntity>>()

    fun observeGetMoviesSuccess(): LiveData<List<MovieEntity>> = onGetMoviesSuccess
    fun observeGetTvSuccess(): LiveData<List<TvEntity>> = onGetTvSuccess

    fun getMovies() {
        viewModelScope.launch {
            when (val state = repository.getMovieBookmarked()) {
                is ResourceState.Success -> state.result.data?.let { onGetMoviesSuccess.postValue(it) }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
    }

    fun getTvs() {
        viewModelScope.launch {
            when (val state = repository.getTvBookmarked()) {
                is ResourceState.Success -> state.result.data?.let { onGetTvSuccess.postValue(it) }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
    }

}