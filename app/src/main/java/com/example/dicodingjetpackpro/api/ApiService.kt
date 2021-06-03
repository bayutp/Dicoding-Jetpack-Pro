package com.example.dicodingjetpackpro.api

import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.model.response.movie.MovieDetailResponse
import com.example.dicodingjetpackpro.model.response.movie.MovieResponse
import com.example.dicodingjetpackpro.model.response.tv.TvDetailResponse
import com.example.dicodingjetpackpro.model.response.tv.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") sort: String = "popularity.desc"
    ): Response<MovieResponse>

    @GET("discover/tv")
    suspend fun getDiscoverTvs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") sort: String = "popularity.desc"
    ): Response<TvResponse>

    @GET("movie/{id_movie}")
    suspend fun getMovieDetail(
        @Path("id_movie") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetailResponse>

    @GET("movie/{id_movie}/similar")
    suspend fun getSimilarMovies(
        @Path("id_movie") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieResponse>

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<TvDetailResponse>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvs(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<TvResponse>

}