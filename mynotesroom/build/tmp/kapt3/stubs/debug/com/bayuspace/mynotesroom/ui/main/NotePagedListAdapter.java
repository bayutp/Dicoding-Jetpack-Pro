package com.bayuspace.mynotesroom.ui.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u001f\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00072\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/main/NotePagedListAdapter;", "Landroidx/paging/PagedListAdapter;", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "Lcom/bayuspace/mynotesroom/ui/main/NotePagedListAdapter$ViewHolder;", "listener", "Lkotlin/Function2;", "", "", "(Lkotlin/jvm/functions/Function2;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "mynotesroom_debug"})
public final class NotePagedListAdapter extends androidx.paging.PagedListAdapter<com.bayuspace.mynotesroom.database.NoteEntity, com.bayuspace.mynotesroom.ui.main.NotePagedListAdapter.ViewHolder> {
    private final kotlin.jvm.functions.Function2<com.bayuspace.mynotesroom.database.NoteEntity, java.lang.Integer, kotlin.Unit> listener = null;
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.bayuspace.mynotesroom.database.NoteEntity> DIFF_CALLBACK = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.bayuspace.mynotesroom.ui.main.NotePagedListAdapter.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.bayuspace.mynotesroom.ui.main.NotePagedListAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.ui.main.NotePagedListAdapter.ViewHolder holder, int position) {
    }
    
    public NotePagedListAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.bayuspace.mynotesroom.database.NoteEntity, ? super java.lang.Integer, kotlin.Unit> listener) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/main/NotePagedListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/bayuspace/mynotesroom/databinding/ItemNoteBinding;", "(Lcom/bayuspace/mynotesroom/ui/main/NotePagedListAdapter;Lcom/bayuspace/mynotesroom/databinding/ItemNoteBinding;)V", "bind", "", "data", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "mynotesroom_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.bayuspace.mynotesroom.databinding.ItemNoteBinding binding = null;
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.bayuspace.mynotesroom.database.NoteEntity data) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.bayuspace.mynotesroom.databinding.ItemNoteBinding binding) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/main/NotePagedListAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "mynotesroom_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}