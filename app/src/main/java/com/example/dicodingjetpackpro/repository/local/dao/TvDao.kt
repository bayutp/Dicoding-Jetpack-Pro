package com.example.dicodingjetpackpro.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingjetpackpro.model.entity.TvEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM tbl_tv WHERE is_bookmark = 1 ORDER BY created_at DESC")
    fun getTvs(): List<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(data: List<TvEntity>)
}