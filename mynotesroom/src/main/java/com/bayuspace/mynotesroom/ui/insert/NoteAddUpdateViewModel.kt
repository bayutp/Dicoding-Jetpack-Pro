package com.bayuspace.mynotesroom.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insertNote(note: NoteEntity) {
        mNoteRepository.insertNote(note)
    }

    fun deleteNote(note: NoteEntity) {
        mNoteRepository.deleteNote(note)
    }

    fun updateNote(note: NoteEntity) {
        mNoteRepository.updateNote(note)
    }
}