package com.bayuspace.mynotesroom.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u000b"}, d2 = {"Lcom/bayuspace/mynotesroom/database/NoteDao;", "", "deleteNote", "", "note", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "getAllNotes", "Landroidx/lifecycle/LiveData;", "", "insertNote", "updateNote", "mynotesroom_debug"})
public abstract interface NoteDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note);
    
    @androidx.room.Update()
    public abstract void updateNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note);
    
    @androidx.room.Delete()
    public abstract void deleteNote(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.database.NoteEntity note);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM tbl_note")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.bayuspace.mynotesroom.database.NoteEntity>> getAllNotes();
}