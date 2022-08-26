package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suzume.movies.data.pojo.reviewResponse.Review
import com.suzume.movies.databinding.ActivityReviewFullBinding
import kotlin.properties.Delegates

class ReviewFullActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_REVIEW = "review"
        private const val EXTRA_MOVIE_NAME = "name"
        private const val POSITIVE_REVIEW_TYPE = "Позитивный"
        private const val NEGATIVE_REVIEW_TYPE = "Негативный"

        fun getIntent(context: Context, review: Review, movieName: String): Intent {
            return Intent(context, ReviewFullActivity::class.java)
                .putExtra(EXTRA_REVIEW, review)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
        }
    }

    private lateinit var binding: ActivityReviewFullBinding
    private lateinit var review: Review
    private var movieName by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewFullBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setContent()

    }

    private fun init() {
        intent.getParcelableExtra<Review>(EXTRA_REVIEW)?.let { review = it }
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
}