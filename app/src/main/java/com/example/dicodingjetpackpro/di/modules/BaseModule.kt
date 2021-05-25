package com.example.dicodingjetpackpro.di.modules

import org.koin.core.module.Module

interface BaseModule {
    val modules: MutableList<Module>
}