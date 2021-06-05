package com.example.dicodingjetpackpro.repository.local

import com.example.dicodingjetpackpro.base.BaseDataSource
import com.example.dicodingjetpackpro.base.ResourceState
import com.example.dicodingjetpackpro.base.ResponseWrapper
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity

class LocalDataSource(private val localDatabase: LocalDatabase) : BaseDataSource() {
    private suspend fun <T> getResult(request: suspend () -> T): ResourceState<ResponseWrapper<T>> {
        return try {
            val res = request.invoke()
            return ResourceState.Success(ResponseWrapper("OK", data = res, errorData = null))
        } catch (e: Exception) {
            errorState(msg = e.toString())
        }
    }

    suspend fun getMovies() =
        suspendDataResult { getResult { localDatabase.movieDao().getMovies() } }

    suspend fun insertMovies(data: List<MovieEntity>) =
        suspendDataResult { getResult { localDatabase.movieDao().insertMovies(data) } }

    suspend fun getTvs() =
        suspendDataResult { getResult { localDatabase.tvDao().getTvs() } }

    suspend fun insertTvs(data: List<TvEntity>) =
        suspendDataResult { getResult { localDatabase.tvDao().insertTvs(data) } }


}