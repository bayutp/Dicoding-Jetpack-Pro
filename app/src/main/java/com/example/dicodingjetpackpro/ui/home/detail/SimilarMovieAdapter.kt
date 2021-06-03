package com.example.dicodingjetpackpro.ui.home.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingjetpackpro.BuildConfig
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.base.BaseDiffCallback
import com.example.dicodingjetpackpro.databinding.ItemSimilarMovieBinding
import com.example.dicodingjetpackpro.model.response.movie.Result
import com.example.dicodingjetpackpro.model.response.tv.TvResult
import com.example.dicodingjetpackpro.utils.formatDate
import com.example.dicodingjetpackpro.utils.loadImage

class SimilarMovieAdapter<T>(private val listener: (T) -> Unit) :
    RecyclerView.Adapter<SimilarMovieAdapter<T>.ViewHolder>() {

    private val movieList = mutableListOf<T>()
    private var lastPosition = -1
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemSimilarMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        setAnimation(holder.itemView, position)
    }

    fun setData(data: List<T>, isMovie: Boolean = true) {
        val diffCallback: BaseDiffCallback<T>
        val diffResult: DiffUtil.DiffResult
        if (isMovie) {
            diffCallback =
                BaseDiffCallback(movieList, data, { oldPosition, newPosition ->
                    (movieList[oldPosition] as Result).id == (data[newPosition] as Result).id
                }) { oldPosition, newPosition ->
                    val oldItem = movieList[oldPosition] as Result
                    val newItem = data[newPosition] as Result
                    oldItem.title == newItem.title && oldItem.overview == newItem.overview
                }
            diffResult = DiffUtil.calculateDiff(diffCallback)

        } else {
            diffCallback =
                BaseDiffCallback(movieList, data, { oldPosition, newPosition ->
                    (movieList[oldPosition] as TvResult).id == (data[newPosition] as TvResult).id
                }) { oldPosition, newPosition ->
                    val oldItem = movieList[oldPosition] as TvResult
                    val newItem = data[newPosition] as TvResult
                    oldItem.name == newItem.name && oldItem.overview == newItem.overview
                }
            diffResult = DiffUtil.calculateDiff(diffCallback)
        }

        movieList.clear()
        movieList.addAll(data)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition){
            val anim = AnimationUtils.loadAnimation(context, R.anim.rv_anim_down_to_up)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }

    inner class ViewHolder(private val binding: ItemSimilarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T) {
            when (data) {
                is Result -> {
                    with(binding) {
                        tvTitleMovie.text = String.format(
                            "%s (%s)",
                            data.title,
                            data.releaseDate.formatDate("yyyy-MM-dd", "yyyy")
                        )
                        ivMovie.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.posterPath}")
                        tvRating.text = data.voteAverage.toString()

                        itemView.setOnClickListener { listener(data) }
                    }
                }
                is TvResult -> {
                    with(binding) {
                        tvTitleMovie.text = String.format(
                            "%s (%s)",
                            data.name,
                            data.firstAirDate.formatDate("yyyy-MM-dd", "yyyy")
                        )
                        ivMovie.loadImage("${BuildConfig.IMAGE_BASE_URL}${data.backdropPath}")
                        tvRating.text = data.voteAverage.toString()

                        itemView.setOnClickListener { listener(data) }
                    }
                }
            }
        }
    }
}