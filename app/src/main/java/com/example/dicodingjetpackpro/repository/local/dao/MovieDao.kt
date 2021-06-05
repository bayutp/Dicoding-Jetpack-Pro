package com.example.dicodingjetpackpro.repository.local.dao

import androidx.room.*
import com.example.dicodingjetpackpro.model.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM tbl_movie WHERE is_bookmark = :isBookmark ORDER BY created_at DESC")
    fun getMovies(isBookmark: Boolean = true): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MovieEntity>)

    @Query("UPDATE tbl_movie SET is_bookmark= :isBookmark WHERE id = :movieId")
    fun updateMovie(isBookmark: Boolean, movieId: Int)
}