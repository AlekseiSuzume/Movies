package com.suzume.movies.presentation.adapter.review

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.data.pojo.reviewResponse.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}