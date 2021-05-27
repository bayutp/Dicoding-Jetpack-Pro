package com.example.dicodingjetpackpro.api

import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.model.response.BaseResponse
import com.example.dicodingjetpackpro.model.response.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") sort: String = "popularity.desc"
    ): SuccessCallback<MovieResponse>
    @GET("discover/tv")

    suspend fun getDiscoverTvs(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") sort: String = "popularity.desc"
    ): SuccessCallback<MovieResponse>

}

typealias SuccessCallback<T> = Response<BaseResponse<T>>