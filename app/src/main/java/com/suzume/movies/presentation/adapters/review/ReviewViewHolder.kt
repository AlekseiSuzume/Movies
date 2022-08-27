package com.suzume.movies.presentation.adapters.review

import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ReviewItemBinding
import com.suzume.movies.domain.models.review.ReviewDomainModel

class ReviewViewHolder(private val binding: ReviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private companion object {
        const val POSITIVE_REVIEW_TYPE = "Позитивный"
        const val NEGATIVE_REVIEW_TYPE = "Негативный"
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
                POSITIVE_REVIEW_TYPE -> imTypeColor.setBackgroundResource(android.R.color.holo_green_dark)
                NEGATIVE_REVIEW_TYPE -> imTypeColor.setBackgroundResource(android.R.color.holo_red_dark)
                else -> imTypeColor.setBackgroundResource(android.R.color.darker_gray)
            }
        }
    }
}