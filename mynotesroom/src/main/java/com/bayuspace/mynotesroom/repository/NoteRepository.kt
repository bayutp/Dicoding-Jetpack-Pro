package com.bayuspace.mynotesroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bayuspace.mynotesroom.database.AppDatabase
import com.bayuspace.mynotesroom.database.NoteDao
import com.bayuspace.mynotesroom.database.NoteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.instanceDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes(): DataSource.Factory<Int, NoteEntity> = mNoteDao.getAllNotes()

    fun insertNote(note: NoteEntity) {
        executorService.execute { mNoteDao.insertNote(note) }
    }

    fun deleteNote(note: NoteEntity) {
        executorService.execute { mNoteDao.deleteNote(note) }
    }

    fun updateNote(note: NoteEntity) {
        executorService.execute { mNoteDao.updateNote(note) }
    }
}