package com.example.dicodingjetpackpro.di.modules

import com.example.dicodingjetpackpro.di.modules.data.DataModules
import com.example.dicodingjetpackpro.di.modules.network.NetworkModules
import com.example.dicodingjetpackpro.di.modules.room.RoomModules
import com.example.dicodingjetpackpro.di.modules.viewmodel.ViewModelModules
import org.koin.core.module.Module

object DepsModuleProvider {
    val modules: MutableList<Module>
        get() {
            return mutableListOf<Module>().apply {
                addAll(RoomModules.modules)
                addAll(NetworkModules.modules)
                addAll(ViewModelModules.modules)
                addAll(DataModules.modules)
            }
        }
}