package com.suzume.movies.presentation.adapter.movie

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.pojo.movieShortResponse.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}