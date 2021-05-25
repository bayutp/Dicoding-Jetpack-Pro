package com.example.dicodingjetpackpro.di.modules.room

import androidx.room.Room
import com.example.dicodingjetpackpro.di.modules.BaseModule
import com.example.dicodingjetpackpro.repository.local.LocalDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

object RoomModules : BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(roomModule)

    private val roomModule = module {
        single {
            Room.databaseBuilder(get(), LocalDatabase::class.java, "local-db")
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}