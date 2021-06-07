package com.example.dicodingjetpackpro.ui.home.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.databinding.ItemMovieBinding
import com.example.dicodingjetpackpro.model.entity.MovieEntity
import com.example.dicodingjetpackpro.model.entity.TvEntity
import com.example.dicodingjetpackpro.utils.formatDate
import com.example.dicodingjetpackpro.utils.loadImage

class BookmarkAdapter(private val listener: (MovieEntity) -> Unit) :
    PagedListAdapter<MovieEntity, ViewHolder<MovieEntity>>(DIFF_CALLBACK) {

    private var lastPosition = -1
    private var context: Context? = null

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
        context = parent.context
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder<MovieEntity>, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.rv_anim_up_to_down)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }
}

class BookmarkTvAdapter(private val listener: (TvEntity) -> Unit) :
    PagedListAdapter<TvEntity, ViewHolder<TvEntity>>(DIFF_CALLBACK) {

    private var lastPosition = -1
    private var context: Context? = null

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
        context = parent.context
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder<TvEntity>, position: Int) {
        holder.bind(getItem(position) as TvEntity)
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.rv_anim_up_to_down)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
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