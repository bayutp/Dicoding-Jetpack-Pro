package com.bayuspace.academy.ui.academy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayuspace.academy.R
import com.bayuspace.academy.data.CourseEntity
import com.bayuspace.academy.databinding.ItemsAcademyBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AcademyAdapter(private val listener: (CourseEntity) -> Unit) :
    RecyclerView.Adapter<AcademyAdapter.ViewHolder>() {
    private var listCourse = ArrayList<CourseEntity>()

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

    fun setCourse(data: List<CourseEntity>) {
        listCourse.clear()
        listCourse.addAll(data)
        notifyDataSetChanged()
        Log.d("TAG", "setCourse: ${data.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemAcademyBinding =
            ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemAcademyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCourse[position])
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }
}