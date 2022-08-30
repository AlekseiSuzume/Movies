package com.suzume.movies.presentation.reviewScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suzume.movies.databinding.ActivityReviewFullBinding
import com.suzume.movies.domain.models.review.ReviewDomainModel
import kotlin.properties.Delegates

class ReviewFullActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityReviewFullBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
    private lateinit var review: ReviewDomainModel
    private var movieName by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setContent()

    }

    private fun init() {
        intent.getParcelableExtra<ReviewDomainModel>(EXTRA_REVIEW)?.let { review = it }
        movieName = intent.getStringExtra(EXTRA_MOVIE_NAME).toString()
        supportActionBar?.title = movieName
    }

    private fun setContent() {
        with(binding) {
            when (review.type) {
                POSITIVE_REVIEW_TYPE -> imTypeColor.setBackgroundResource(android.R.color.holo_green_dark)
                NEGATIVE_REVIEW_TYPE -> imTypeColor.setBackgroundResource(android.R.color.holo_red_dark)
                else -> imTypeColor.setBackgroundResource(android.R.color.darker_gray)
            }
            tvAuthor.text = review.author

            val date = review.date
                .substringBefore("T")
                .split("-")
                .reversed()
                .joinToString(".") { it }
            tvDate.text = date

            tvThumbUp.text = review.reviewLikes.toString()
            tvThumbDown.text = review.reviewDislikes.toString()

            tvTitle.text = review.title
            tvReview.text = review.review

        }
    }

    companion object {
        private const val EXTRA_REVIEW = "review"
        private const val EXTRA_MOVIE_NAME = "name"
        private const val POSITIVE_REVIEW_TYPE = "Позитивный"
        private const val NEGATIVE_REVIEW_TYPE = "Негативный"

        fun getIntent(
            context: Context,
            reviewDomainModel: ReviewDomainModel,
            movieName: String,
        ): Intent {
            return Intent(context, ReviewFullActivity::class.java)
                .putExtra(EXTRA_REVIEW, reviewDomainModel)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
        }
    }
}