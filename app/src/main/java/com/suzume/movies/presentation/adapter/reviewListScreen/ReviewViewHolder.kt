package com.suzume.movies.presentation.adapter.reviewListScreen

import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.data.pojo.reviewResponse.Review
import com.suzume.movies.databinding.ReviewItemLargeBinding

class ReviewViewHolder(private val binding: ReviewItemLargeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private companion object {
        const val POSITIVE_REVIEW = "Позитивный"
        const val NEGATIVE_REVIEW = "Негативный"
    }

    fun bind(review: Review) {
        with(binding) {
            tvAuthor.text = review.author
            tvTitle.text = review.title
            tvReview.text = review.review

            val date = review.date
                .substringBefore("T")
                .split("-")
                .reversed()
                .joinToString(".") { it }
            tvDate.text = date

            when (review.type) {
                POSITIVE_REVIEW -> imTypeColor.setBackgroundResource(android.R.color.holo_green_dark)
                NEGATIVE_REVIEW -> imTypeColor.setBackgroundResource(android.R.color.holo_red_dark)
                else -> imTypeColor.setBackgroundResource(android.R.color.darker_gray)
            }
        }
    }
}