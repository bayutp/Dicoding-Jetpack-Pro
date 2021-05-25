package com.bayuspace.mynotesroom.database;

import java.lang.System;

@androidx.room.Database(entities = {com.bayuspace.mynotesroom.database.NoteEntity.class}, version = 1)
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/bayuspace/mynotesroom/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "noteDao", "Lcom/bayuspace/mynotesroom/database/NoteDao;", "Companion", "mynotesroom_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    private static volatile com.bayuspace.mynotesroom.database.AppDatabase instanceDatabase;
    @org.jetbrains.annotations.NotNull()
    public static final com.bayuspace.mynotesroom.database.AppDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.bayuspace.mynotesroom.database.NoteDao noteDao();
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.bayuspace.mynotesroom.database.AppDatabase instanceDatabase(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/bayuspace/mynotesroom/database/AppDatabase$Companion;", "", "()V", "instanceDatabase", "Lcom/bayuspace/mynotesroom/database/AppDatabase;", "add", "", "context", "Landroid/content/Context;", "mynotesroom_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.bayuspace.mynotesroom.database.AppDatabase instanceDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        public final void add() {
        }
        
        private Companion() {
            super();
        }
    }
}