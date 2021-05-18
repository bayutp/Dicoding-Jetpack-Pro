package com.bayuspace.mynotesroom.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\u0006H\u0016J\u001c\u0010\f\u001a\u00020\u00072\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0014\u0010\u0013\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/NoteAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/bayuspace/mynotesroom/ui/NoteAdapter$ViewHolder;", "listener", "Lkotlin/Function2;", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "", "", "(Lkotlin/jvm/functions/Function2;)V", "listNotes", "Ljava/util/ArrayList;", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "data", "", "ViewHolder", "mynotesroom_debug"})
public final class NoteAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.bayuspace.mynotesroom.ui.NoteAdapter.ViewHolder> {
    private final java.util.ArrayList<com.bayuspace.mynotesroom.database.NoteEntity> listNotes = null;
    private final kotlin.jvm.functions.Function2<com.bayuspace.mynotesroom.database.NoteEntity, java.lang.Integer, kotlin.Unit> listener = null;
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.bayuspace.mynotesroom.database.NoteEntity> data) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.bayuspace.mynotesroom.ui.NoteAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.bayuspace.mynotesroom.ui.NoteAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public NoteAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.bayuspace.mynotesroom.database.NoteEntity, ? super java.lang.Integer, kotlin.Unit> listener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/bayuspace/mynotesroom/ui/NoteAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/bayuspace/mynotesroom/databinding/ItemNoteBinding;", "(Lcom/bayuspace/mynotesroom/ui/NoteAdapter;Lcom/bayuspace/mynotesroom/databinding/ItemNoteBinding;)V", "bind", "", "data", "Lcom/bayuspace/mynotesroom/database/NoteEntity;", "mynotesroom_debug"})
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
}