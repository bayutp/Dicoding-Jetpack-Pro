package com.example.dicodingjetpackpro.di.modules.data

import com.example.dicodingjetpackpro.di.modules.BaseModule
import com.example.dicodingjetpackpro.repository.DataRepository
import com.example.dicodingjetpackpro.repository.local.LocalDataSource
import com.example.dicodingjetpackpro.repository.remote.RemoteDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModules : BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(dataModules)

    private val dataModules = module {
        single { RemoteDataSource(get()) }
        single { LocalDataSource(get()) }
        single { DataRepository(get(), get()) }
    }
}