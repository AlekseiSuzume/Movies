package com.suzume.movies.presentation.adapters.reviewListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suzume.movies.databinding.ReviewItemLargeBinding
import com.suzume.movies.domain.models.review.ReviewDomainModel

class ReviewListScreenAdapter : ListAdapter<ReviewDomainModel, ReviewViewHolder>(ReviewDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null
    var reviewOnClickListener: ((reviewDomainModel: ReviewDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReviewItemLargeBinding.inflate(
            inflater,
            parent,
            false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
        holder.itemView.setOnClickListener {
            reviewOnClickListener?.invoke(review)
        }
        if (position == currentList.size - 3) {
            onReachEndListener?.invoke()
        }
    }

}