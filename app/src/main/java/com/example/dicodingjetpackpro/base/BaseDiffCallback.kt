package com.example.dicodingjetpackpro.base

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffCallback<T>(
    private val mOldList: List<T>,
    private val mNewList: List<T>,
    private val itemShame: (Int, Int) -> Boolean,
    private val itemContent: (Int, Int) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return itemShame(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return itemContent(oldItemPosition, newItemPosition)
    }

}