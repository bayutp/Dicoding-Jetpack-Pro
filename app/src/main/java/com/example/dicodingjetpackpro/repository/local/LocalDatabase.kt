package com.example.dicodingjetpackpro.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.repository.local.dao.MovieDao
import com.example.dicodingjetpackpro.repository.local.dao.TvDao

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}