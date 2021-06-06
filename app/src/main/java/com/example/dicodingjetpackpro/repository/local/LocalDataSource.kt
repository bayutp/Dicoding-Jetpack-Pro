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

    fun getMovies() = localDatabase.movieDao().getMovies()

    suspend fun insertMovies(data: List<MovieEntity>) =
        suspendDataResult { getResult { localDatabase.movieDao().insertMovies(data) } }

    suspend fun getMovie(id: Int) =
        suspendDataResult { getResult { localDatabase.movieDao().getMovie(id) } }

    fun getTvs() = localDatabase.tvDao().getTvs()

    suspend fun insertTvs(data: List<TvEntity>) =
        suspendDataResult { getResult { localDatabase.tvDao().insertTvs(data) } }

    suspend fun getTv(id: Int) = suspendDataResult { getResult { localDatabase.tvDao().getTv(id) } }
}