package com.example.dicodingjetpackpro.ui.home.bookmark

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicodingjetpackpro.base.BaseViewModel
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.repository.DataRepository

class BookmarkViewModel(private val repository: DataRepository) : BaseViewModel() {
    fun getMovies(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(repository.getMovieBookmarked(), 10).build()
    }

    fun getTvs(): LiveData<PagedList<TvEntity>> {
        return LivePagedListBuilder(repository.getTvBookmarked(), 10).build()
    }

}