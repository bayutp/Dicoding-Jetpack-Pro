package com.bayuspace.mynotesroom.helper

import androidx.recyclerview.widget.DiffUtil
import com.bayuspace.mynotesroom.database.NoteEntity

class NoteDiffCallback(
    private val mOldNotes: List<NoteEntity>,
    private val mNewNotes: List<NoteEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNotes.size
    }

    override fun getNewListSize(): Int {
        return mNewNotes.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNotes[oldItemPosition].id == mNewNotes[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldNotes[oldItemPosition]
        val newItem = mNewNotes[newItemPosition]
        return oldItem.title == newItem.title && oldItem.description == newItem.description
    }

}