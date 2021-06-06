package com.example.dicodingjetpackpro.repository.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingjetpackpro.model.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM tbl_movie WHERE is_bookmark = 1 ORDER BY created_at DESC")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * from tbl_movie WHere id = :id AND is_bookmark = 1")
    fun getMovie(id:Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MovieEntity>)
}