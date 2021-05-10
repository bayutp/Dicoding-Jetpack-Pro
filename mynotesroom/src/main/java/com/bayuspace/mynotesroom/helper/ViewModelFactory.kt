package com.bayuspace.mynotesroom.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bayuspace.mynotesroom.ui.insert.NoteAddUpdateViewModel
import com.bayuspace.mynotesroom.ui.main.MainViewModel

class ViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun newInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(application) as T
            modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java) ->
                NoteAddUpdateViewModel(application) as T
            else -> throw IllegalStateException("Unknown view model class: ${modelClass.name}")
        }
    }
}