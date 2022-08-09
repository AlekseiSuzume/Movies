package com.suzume.movies.presentation.adapter.favoriteMovieAdaper

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.databinding.MovieItemBinding

class FavoriteMovieViewHolder(
    private val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieDetail: MovieDetail) {
        with(binding) {
            Glide.with(binding.root.context)
                .load(movieDetail.poster.url)
                .into(ivPoster)
            tvRating.text = movieDetail.rating.kp.toString()
            when (movieDetail.rating.kp) {
                in 7.0..10.0 -> tvRating.setBackgroundResource(R.drawable.circle_green)
                in 5.0..6.9 -> tvRating.setBackgroundResource(R.drawable.circle_orange)
                in 0.0..4.9 -> tvRating.setBackgroundResource(R.drawable.circle_red)
            }
        }
    }
}