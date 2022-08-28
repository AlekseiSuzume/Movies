package com.suzume.movies.presentation.adapters.movie

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel

class MovieDiffCallback : DiffUtil.ItemCallback<MovieShortInfoDomainModel>() {
    override fun areItemsTheSame(oldItem: MovieShortInfoDomainModel, newItem: MovieShortInfoDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieShortInfoDomainModel, newItem: MovieShortInfoDomainModel): Boolean {
        return oldItem == newItem
    }
}