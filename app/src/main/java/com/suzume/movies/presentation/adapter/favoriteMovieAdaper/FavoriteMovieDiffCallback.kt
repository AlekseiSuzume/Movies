package com.suzume.movies.presentation.adapter.favoriteMovieAdaper

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail

class FavoriteMovieDiffCallback : DiffUtil.ItemCallback<MovieDetail>() {
    override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
        return oldItem == newItem
    }
}