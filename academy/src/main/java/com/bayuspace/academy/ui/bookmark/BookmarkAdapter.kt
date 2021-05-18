package com.bayuspace.academy.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.R
import com.bayuspace.academy.data.source.local.entity.CourseEntity
import com.bayuspace.academy.databinding.ItemsBookmarkBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BookmarkAdapter(
    private val listener: (CourseEntity) -> Unit,
    private val shareListener: (CourseEntity) -> Unit
) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private val listCourse = ArrayList<CourseEntity>()

    fun setCourse(data: List<CourseEntity>) {
        listCourse.clear()
        listCourse.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemsBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseEntity) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    listener(course)
                }
                imgShare.setOnClickListener { shareListener(course) }
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
        val binding =
            ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCourse[position])
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }
}