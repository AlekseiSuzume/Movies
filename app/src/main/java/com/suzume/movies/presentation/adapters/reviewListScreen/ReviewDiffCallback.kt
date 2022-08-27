package com.suzume.movies.presentation.adapters.reviewListScreen

import androidx.recyclerview.widget.DiffUtil
import com.suzume.movies.domain.models.review.ReviewDomainModel

class ReviewDiffCallback : DiffUtil.ItemCallback<ReviewDomainModel>() {
    override fun areItemsTheSame(oldItem: ReviewDomainModel, newItem: ReviewDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReviewDomainModel, newItem: ReviewDomainModel): Boolean {
        return oldItem == newItem
    }
}