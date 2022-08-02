package com.suzume.movies.presentation.adapter.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.MovieItemBinding
import com.suzume.movies.pojo.movieShortResponse.Movie

class MovieViewHolder(
    private val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        with(binding) {
            Glide.with(binding.root.context)
                .load(movie.poster.url)
                .into(ivPoster)
            tvRating.text = movie.rating.kp.toString()
            when (movie.rating.kp) {
                in 7.0..10.0 -> tvRating.setBackgroundResource(R.drawable.circle_green)
                in 5.0..6.9 -> tvRating.setBackgroundResource(R.drawable.circle_orange)
                in 0.0..4.9 -> tvRating.setBackgroundResource(R.drawable.circle_red)
            }
        }
    }
}