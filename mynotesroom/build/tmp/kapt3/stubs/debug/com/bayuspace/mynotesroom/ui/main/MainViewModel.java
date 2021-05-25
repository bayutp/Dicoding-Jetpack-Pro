package com.bayuspace.mynotesroom.ui.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "mNoteRepository", "Lcom/bayuspace/mynotesroom/repository/NoteRepository;", "getAllNotes", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "sort", "", "mynotesroom_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    private final com.bayuspace.mynotesroom.repository.NoteRepository mNoteRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<androidx.paging.PagedList<com.bayuspace.mynotesroom.database.NoteEntity>> getAllNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String sort) {
        return null;
    }
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
}