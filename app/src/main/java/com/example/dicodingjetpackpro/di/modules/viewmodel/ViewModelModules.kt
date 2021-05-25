package com.example.dicodingjetpackpro.di.modules.viewmodel

import com.example.dicodingjetpackpro.di.modules.BaseModule
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModules : BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(viewModelModule)
    private val viewModelModule = module {

    }
}