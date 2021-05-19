package com.bayuspace.mynotesroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

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
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        add()
                    }
                }).build()
            }
            return instanceDatabase as AppDatabase
        }

        fun add() {
            Executors.newSingleThreadExecutor().execute {
                val list = mutableListOf<NoteEntity>()
                for (i in 0..20) {
                    val dummyNote = NoteEntity()
                    dummyNote.title = "Tugas $i"
                    dummyNote.description = "Belajar Modul $i"
                    dummyNote.date = "2019/09/09 09:09:0$i"
                    list.add(dummyNote)
                }
                instanceDatabase?.noteDao()?.insertAll(list)
            }
        }
    }
}