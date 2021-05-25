package com.example.dicodingjetpackpro.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dicodingjetpackpro.model.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
}