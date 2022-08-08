package com.suzume.movies.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.MovieItemBinding
import com.suzume.movies.data.pojo.movieShortResponse.Movie

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null
    var onClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        if (position == currentList.size - 10) {
            onReachEndListener?.invoke()
        }
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(movie)
        }
    }

}