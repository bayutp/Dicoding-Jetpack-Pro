package com.bayuspace.mynotesroom.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.databinding.ItemNoteBinding

class NotePagedListAdapter(private val listener: (NoteEntity, Int) -> Unit) :
    PagedListAdapter<NoteEntity, NotePagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as NoteEntity)
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NoteEntity) {
            with(binding) {
                tvItemTitle.text = data.title
                tvItemDescription.text = data.description
                tvItemDate.text = data.date
                cvItemNote.setOnClickListener {
                    listener(data, adapterPosition)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<NoteEntity> =
            object : DiffUtil.ItemCallback<NoteEntity>() {
                override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                    return oldItem.title == newItem.title && oldItem.description == newItem.description
                }

                override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                    return oldItem == newItem
                }

            }
    }
}