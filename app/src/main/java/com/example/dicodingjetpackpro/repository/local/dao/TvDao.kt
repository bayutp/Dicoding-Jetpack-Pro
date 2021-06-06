package com.example.dicodingjetpackpro.repository.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingjetpackpro.model.entity.TvEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM tbl_tv WHERE is_bookmark = 1 ORDER BY created_at DESC")
    fun getTvs(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * from tbl_tv WHERE id = :id AND is_bookmark = 1")
    fun getTv(id:Int): TvEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(data: List<TvEntity>)
}