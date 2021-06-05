package com.example.dicodingjetpackpro.repository

import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.repository.local.LocalDataSource
import com.example.dicodingjetpackpro.repository.remote.RemoteDataSource

class DataRepository(private val local: LocalDataSource, private val remote: RemoteDataSource) {
    suspend fun getDiscoverMovies() = remote.getDiscoverMovies()
    suspend fun getDiscoverTvs() = remote.getDiscoverTvs()
    suspend fun getMovieDetail(movieId: Int) = remote.getMovieDetail(movieId)
    suspend fun getSimilarMovies(movieId: Int) = remote.getSimilarMovies(movieId)
    suspend fun getTvDetail(tvId: Int) = remote.getTvDetail(tvId)
    suspend fun getSimilarTvs(tvId: Int) = remote.getSimilarTvs(tvId)

    suspend fun getMovieBookmarked() = local.getMovies()
    suspend fun saveBookmark(data: List<MovieEntity>) = local.insertMovies(data)
    suspend fun setMovieBookmark(isBookmark: Boolean, movieId: Int) =
        local.updateMovie(isBookmark, movieId)
}