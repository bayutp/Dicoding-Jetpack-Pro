package com.bayuspace.academy.ui.academy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.R
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.databinding.ItemsAcademyBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AcademyAdapter(private val listener: (CourseEntity) -> Unit) :
   PagedListAdapter<CourseEntity, AcademyAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemsAcademyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    listener(course)
                }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemAcademyBinding =
            ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemAcademyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as CourseEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseEntity>() {
            override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem.courseId == newItem.courseId
            }

            override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}