package com.suzume.movies.presentation.adapters.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.MovieItemBinding
import com.suzume.movies.domain.models.movieShort.MovieShortInfoDomainModel

class MovieViewHolder(
    private val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieShortInfoDomainModel) {
        with(binding) {
            Glide.with(binding.root.context)
                .load(movie.posterUrl)
                .into(ivPoster)
            tvRating.text = movie.rating.toString()
            when (movie.rating) {
                in 7.0..10.0 -> tvRating.setBackgroundResource(R.drawable.circle_green)
                in 5.0..6.9 -> tvRating.setBackgroundResource(R.drawable.circle_orange)
                in 0.0..4.9 -> tvRating.setBackgroundResource(R.drawable.circle_red)
            }
        }
    }
}