package com.bayuspace.mynotesroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instanceDatabase: AppDatabase? = null

        @JvmStatic
        fun instanceDatabase(context: Context): AppDatabase {
            if (instanceDatabase == null) synchronized(AppDatabase::class.java) {
                instanceDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_note"
                ).build()
            }
            return instanceDatabase as AppDatabase
        }
    }
}