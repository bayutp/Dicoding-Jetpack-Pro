package com.example.dicodingjetpackpro.di.modules.room

import android.app.Application
import androidx.room.Room
import com.example.dicodingjetpackpro.di.modules.BaseModule
import com.example.dicodingjetpackpro.repository.local.LocalDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

object RoomModules : BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(roomModule)

    private val roomModule = module {

        fun provideDatabase(application: Application) =
            Room.databaseBuilder(application, LocalDatabase::class.java, "local-db")
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()

        fun provideMovieDao(db: LocalDatabase) = db.movieDao()
        fun provideTvDao(db:LocalDatabase) = db.tvDao()

        single { provideDatabase(androidApplication()) }
        single { provideMovieDao(get()) }
        single { provideTvDao(get()) }
    }


}