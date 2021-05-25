package com.bayuspace.mynotesroom.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/bayuspace/mynotesroom/repository/NoteRepository;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "executorService", "Ljava/util/concurrent/ExecutorService;", "mNoteDao", "Lcom/bayuspace/mynotesroom/database/NoteDao;", "deleteNote", "", "note", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "getAllNotes", "Landroidx/paging/DataSource$Factory;", "", "sort", "", "insertNote", "updateNote", "mynotesroom_debug"})
public final class NoteRepository {
    private final com.bayuspace.mynotesroom.database.NoteDao mNoteDao = null;
    private final java.util.concurrent.ExecutorService executorService = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.bayuspace.mynotesroom.database.NoteEntity> getAllNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String sort) {
        return null;
    }
    
    public final void insertNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note) {
    }
    
    public final void deleteNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note) {
    }
    
    public final void updateNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note) {
    }
    
    public NoteRepository(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
}