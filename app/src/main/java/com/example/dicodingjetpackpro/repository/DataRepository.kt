package com.example.dicodingjetpackpro.repository

import com.example.dicodingjetpackpro.repository.local.LocalDataSource
import com.example.dicodingjetpackpro.repository.remote.RemoteDataSource

class DataRepository(private val local: LocalDataSource, private val remote: RemoteDataSource) {
    suspend fun getDiscoverMovies() = remote.getDiscoverMovies()
    suspend fun getDiscoverTvs() = remote.getDiscoverTvs()
}