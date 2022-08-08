package com.suzume.movies.presentation.adapter.trailer

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer

class TrailerDiffCallback: DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}