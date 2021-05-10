package com.bayuspace.mynotesroom.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return mNoteRepository.getAllNotes()
    }
}