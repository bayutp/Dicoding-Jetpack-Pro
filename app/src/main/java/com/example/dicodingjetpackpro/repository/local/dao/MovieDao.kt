package com.example.dicodingjetpackpro.repository.local.dao

import androidx.room.*
import com.example.dicodingjetpackpro.model.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM tbl_movie WHERE is_bookmark = 1 ORDER BY created_at DESC")
    fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MovieEntity>)
}