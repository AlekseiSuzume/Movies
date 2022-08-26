package com.suzume.movies.presentation.adapter.favoriteMovieAdaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.databinding.MovieItemBinding

class FavoriteMovieAdapter : ListAdapter<MovieDetail, FavoriteMovieViewHolder>(FavoriteMovieDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null
    var onMovieClickListener: ((MovieDetail) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return FavoriteMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movieDetail = getItem(position)
        holder.bind(movieDetail)
        if (position == currentList.size - 10) {
            onReachEndListener?.invoke()
        }
        holder.itemView.setOnClickListener {
            onMovieClickListener?.invoke(movieDetail)
        }
    }

}