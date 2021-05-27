package com.example.dicodingjetpackpro

import android.app.Application
import com.example.dicodingjetpackpro.di.modules.DepsModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApp)
            modules(DepsModuleProvider.modules)
        }
    }
}