package com.suzume.movies.presentation.adapters.reviewListScreen

import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ReviewItemLargeBinding
import com.suzume.movies.domain.models.review.ReviewDomainModel

class ReviewViewHolder(private val binding: ReviewItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private companion object {
        const val POSITIVE_REVIEW = "Позитивный"
        const val NEGATIVE_REVIEW = "Негативный"
    }

    fun bind(reviewDomainModel: ReviewDomainModel) {
        with(binding) {
            tvAuthor.text = reviewDomainModel.author
            tvTitle.text = reviewDomainModel.title
            tvReview.text = reviewDomainModel.review

            val date = reviewDomainModel.date
                .substringBefore("T")
                .split("-")
                .reversed()
                .joinToString(".") { it }
            tvDate.text = date

            when (reviewDomainModel.type) {
                POSITIVE_REVIEW -> imTypeColor.setBackgroundResource(android.R.color.holo_green_dark)
                NEGATIVE_REVIEW -> imTypeColor.setBackgroundResource(android.R.color.holo_red_dark)
                else -> imTypeColor.setBackgroundResource(android.R.color.darker_gray)
            }
        }
    }
}