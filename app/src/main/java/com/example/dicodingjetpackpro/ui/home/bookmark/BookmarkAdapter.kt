package com.example.dicodingjetpackpro.ui.home.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.databinding.ItemMovieBinding
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.utils.formatDate
import com.example.dicodingjetpackpro.utils.loadImage

class BookmarkAdapter(private val listener: (MovieEntity) -> Unit) :
    PagedListAdapter<MovieEntity, ViewHolder<MovieEntity>>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.id == newItem.id && oldItem.title == newItem.title
                }

                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<MovieEntity> {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder<MovieEntity>, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }
}

class BookmarkTvAdapter(private val listener: (TvEntity) -> Unit) :
    PagedListAdapter<TvEntity, ViewHolder<TvEntity>>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvEntity> =
            object : DiffUtil.ItemCallback<TvEntity>() {
                override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                    return oldItem.id == newItem.id && oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: TvEntity,
                    newItem: TvEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<TvEntity> {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder<TvEntity>, position: Int) {
        holder.bind(getItem(position) as TvEntity)
    }
}

class ViewHolder<T>(private val binding: ItemMovieBinding, private val listener: (T) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: T) {
        when (data) {
            is MovieEntity -> {
                with(binding) {
                    tvTitleMovie.text = String.format(
                        "%s (%s)",
                        data.title,
                        data.releaseDate.formatDate("yyyy-MM-dd", "yyyy")
                    )
                    ivMovie.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.posterPath}")

                    itemView.setOnClickListener { listener(data) }
                }
            }

            is TvEntity -> {
                with(binding) {
                    tvTitleMovie.text = String.format(
                        "%s (%s)",
                        data.name,
                        data.firstAirDate.formatDate("yyyy-MM-dd", "yyyy")
                    )
                    ivMovie.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.posterPath}")

                    itemView.setOnClickListener { listener(data) }
                }
            }
        }

    }
}