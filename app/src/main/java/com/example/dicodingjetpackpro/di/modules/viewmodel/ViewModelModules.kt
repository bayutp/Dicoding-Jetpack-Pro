package com.example.dicodingjetpackpro.di.modules.viewmodel

import com.example.dicodingjetpackpro.di.modules.BaseModule
import com.example.dicodingjetpackpro.ui.home.HomeViewModel
import com.example.dicodingjetpackpro.ui.home.bookmark.BookmarkViewModel
import com.example.dicodingjetpackpro.ui.home.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModules : BaseModule {
    override val modules: MutableList<Module>
        get() = mutableListOf(viewModelModule)
    private val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { BookmarkViewModel(get()) }
    }
}