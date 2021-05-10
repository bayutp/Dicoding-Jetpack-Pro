package com.bayuspace.mynotesroom.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.mynotesroom.database.NoteEntity
import com.bayuspace.mynotesroom.databinding.ItemNoteBinding
import com.bayuspace.mynotesroom.helper.NoteDiffCallback

class NoteAdapter constructor(private val listener: (NoteEntity, Int) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val listNotes = arrayListOf<NoteEntity>()

    fun setData(data: List<NoteEntity>) {
        val diffCallback = NoteDiffCallback(listNotes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listNotes.clear()
        listNotes.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
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
}