package com.example.dicodingjetpackpro.ui.home.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.repository.DataRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: DataRepository) : BaseViewModel() {
    private var onGetMoviesSuccess = MutableLiveData<PagedList<MovieEntity>>()
    private var onGetTvSuccess = MutableLiveData<PagedList<TvEntity>>()

    fun observeGetMoviesSuccess(): LiveData<PagedList<MovieEntity>> = onGetMoviesSuccess
    fun observeGetTvSuccess(): LiveData<PagedList<TvEntity>> = onGetTvSuccess

    fun getMovies() {
        viewModelScope.launch {
            when (val state = repository.getMovieBookmarked()) {
                is ResourceState.Success -> state.result.data?.let {
                    onGetMoviesSuccess.postValue(
                        LivePagedListBuilder(it, 10).build().value
                    )
                    Log.d("TAG", "getMovies success: ${LivePagedListBuilder(it,10).build().value}")
                }
                is ResourceState.Error -> {
                    errorResponse.postValue(state.error.errorData)
                    Log.d("TAG", "getMovies error: ${state.error}")
                }
            }
        }
    }

    fun getTvs() {
        viewModelScope.launch {
            when (val state = repository.getTvBookmarked()) {
                is ResourceState.Success -> state.result.data?.let {
                    onGetTvSuccess.postValue(
                        LivePagedListBuilder(it, 10).build().value
                    )
                }
                is ResourceState.Error -> errorResponse.postValue(state.error.errorData)
            }
        }
    }

}