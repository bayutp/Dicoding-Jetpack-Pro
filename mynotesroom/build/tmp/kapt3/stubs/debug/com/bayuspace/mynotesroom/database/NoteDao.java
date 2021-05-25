package com.bayuspace.mynotesroom.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\t\u001a\u00020\nH\'J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\'J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0010"}, d2 = {"Lcom/bayuspace/mynotesroom/database/NoteDao;", "", "deleteNote", "", "note", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "getAllNotes", "Landroidx/paging/DataSource$Factory;", "", "query", "Landroidx/sqlite/db/SupportSQLiteQuery;", "insertAll", "list", "", "insertNote", "updateNote", "mynotesroom_debug"})
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
    @androidx.room.RawQuery(observedEntities = {com.bayuspace.mynotesroom.database.NoteEntity.class})
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.bayuspace.mynotesroom.database.NoteEntity> getAllNotes(@org.jetbrains.annotations.NotNull()
    androidx.sqlite.db.SupportSQLiteQuery query);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.bayuspace.mynotesroom.database.NoteEntity> list);
}