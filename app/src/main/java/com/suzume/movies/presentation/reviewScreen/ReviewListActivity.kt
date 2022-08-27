package com.suzume.movies.presentation.reviewScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityReviewListBinding
import com.suzume.movies.presentation.adapters.reviewListScreen.ReviewListScreenAdapter
import kotlin.properties.Delegates

class ReviewListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewListBinding
    private var movieId by Delegates.notNull<Int>()
    private var movieName by Delegates.notNull<String>()
    private lateinit var viewModel: ReviewListViewModel
    private lateinit var adapter: ReviewListScreenAdapter
    private var flagType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityReviewListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setupOnReachEndListener()
        setupReviewOnClickListener()

        binding.cvReviewAll.setOnClickListener {
            reviewAllOnClickListener()
        }
        binding.cvReviewPositive.setOnClickListener {
            reviewPositiveOnClickListener()
        }

        binding.cvReviewNegative.setOnClickListener {
            reviewNegativeOnClickListener()
        }

        binding.cvReviewNeutral.setOnClickListener {
            reviewNeutralOnClickListener()
        }
    }

    private fun init() {
        movieId = intent.getIntExtra(EXTRA_FROM_ID, 0)
        movieName = intent.getStringExtra(EXTRA_MOVIE_NAME) ?: "Movies"
        viewModel = ViewModelProvider(this)[ReviewListViewModel::class.java]
        adapter = ReviewListScreenAdapter()
        binding.rvReviewList.adapter = adapter

        viewModel.refreshReviewAllList(movieId)
        viewModel.refreshReviewPositiveList(movieId)
        viewModel.refreshReviewNegativeList(movieId)
        viewModel.refreshReviewNeutralList(movieId)

        viewModel.reviewAllList.observe(this) {
            adapter.submitList(it.reviews)
            binding.tvReviewAllCount.text = it.totalReviews.toString()
        }

        viewModel.reviewAllList.observe(this) {
            binding.tvReviewAllCount.text = it.totalReviews.toString()
        }

        viewModel.reviewPositiveList.observe(this) {
            binding.tvReviewPositiveCount.text = it.totalReviews.toString()
        }

        viewModel.reviewNegativeList.observe(this) {
            binding.tvReviewNegativeCount.text = it.totalReviews.toString()
        }

        viewModel.reviewNeutralList.observe(this) {
            binding.tvReviewNeutralCount.text = it.totalReviews.toString()
        }
        setButtonBackground(ALL_REVIEW)
        binding.rvReviewList.visibility = View.VISIBLE
    }

    private fun reviewAllButtonOn() {
        binding.cvReviewAll.setCardBackgroundColor(resources.getColor(R.color.black, theme))
        binding.tvReviewAllCountLabel.setTextColor(resources.getColor(R.color.white, theme))
        binding.tvReviewAllCount.setTextColor(resources.getColor(R.color.white, theme))
    }

    private fun reviewAllButtonOff() {
        binding.cvReviewAll.setCardBackgroundColor(resources.getColor(R.color.white, theme))
        binding.tvReviewAllCountLabel.setTextColor(resources.getColor(R.color.black, theme))
        binding.tvReviewAllCount.setTextColor(resources.getColor(R.color.black, theme))
    }

    private fun reviewPositiveButtonOn() {
        binding.cvReviewPositive.setCardBackgroundColor(resources.getColor(R.color.black,
            theme))
        binding.tvReviewPositiveCount.setTextColor(resources.getColor(R.color.white, theme))
    }

    private fun reviewPositiveButtonOff() {
        binding.cvReviewPositive.setCardBackgroundColor(resources.getColor(R.color.white,
            theme))
        binding.tvReviewPositiveCount.setTextColor(resources.getColor(R.color.black, theme))
    }

    private fun reviewNegativeButtonOn() {
        binding.cvReviewNegative.setCardBackgroundColor(resources.getColor(R.color.black,
            theme))
        binding.tvReviewNegativeCount.setTextColor(resources.getColor(R.color.white, theme))
    }

    private fun reviewNegativeButtonOff() {
        binding.cvReviewNegative.setCardBackgroundColor(resources.getColor(R.color.white,
            theme))
        binding.tvReviewNegativeCount.setTextColor(resources.getColor(R.color.black, theme))
    }

    private fun reviewNeutralButtonOn() {
        binding.cvReviewNeutral.setCardBackgroundColor(resources.getColor(R.color.black,
            theme))
        binding.tvReviewNeutralCount.setTextColor(resources.getColor(R.color.white, theme))
    }

    private fun reviewNeutralButtonOff() {
        binding.cvReviewNeutral.setCardBackgroundColor(resources.getColor(R.color.white,
            theme))
        binding.tvReviewNeutralCount.setTextColor(resources.getColor(R.color.black, theme))
    }

    private fun reviewAllOnClickListener() {
        viewModel.reviewAllList.observe(this) {
            adapter.submitList(it.reviews)
        }
        removeRecyclerAnimation()
        setButtonBackground(ALL_REVIEW)
        flagType = ALL_REVIEW
    }

    private fun reviewPositiveOnClickListener() {
        viewModel.reviewPositiveList.observe(this) {
            adapter.submitList(it.reviews)
            binding.tvReviewPositiveCount.text = it.totalReviews.toString()
        }
        removeRecyclerAnimation()
        setButtonBackground(POSITIVE_REVIEW)
        flagType = POSITIVE_REVIEW
    }

    private fun reviewNegativeOnClickListener() {
        viewModel.reviewNegativeList.observe(this) {
            adapter.submitList(it.reviews)
            binding.tvReviewNegativeCount.text = it.totalReviews.toString()
        }
        removeRecyclerAnimation()
        setButtonBackground(NEGATIVE_REVIEW)
        flagType = NEGATIVE_REVIEW
    }

    private fun reviewNeutralOnClickListener() {
        viewModel.reviewNeutralList.observe(this) {
            adapter.submitList(it.reviews)
            binding.tvReviewNeutralCount.text = it.totalReviews.toString()
        }
        removeRecyclerAnimation()
        setButtonBackground(NEUTRAL_REVIEW)
        flagType = NEUTRAL_REVIEW
    }

    private fun setButtonBackground(reviewType: Int) {
        when (reviewType) {
            ALL_REVIEW -> {
                reviewAllButtonOn()
                reviewPositiveButtonOff()
                reviewNegativeButtonOff()
                reviewNeutralButtonOff()
            }
            POSITIVE_REVIEW -> {
                reviewPositiveButtonOn()
                reviewAllButtonOff()
                reviewNegativeButtonOff()
                reviewNeutralButtonOff()
            }
            NEGATIVE_REVIEW -> {
                reviewNegativeButtonOn()
                reviewAllButtonOff()
                reviewPositiveButtonOff()
                reviewNeutralButtonOff()
            }
            NEUTRAL_REVIEW -> {
                reviewNeutralButtonOn()
                reviewAllButtonOff()
                reviewPositiveButtonOff()
                reviewNegativeButtonOff()
            }
        }
    }

    private fun removeRecyclerAnimation() {
        binding.rvReviewList.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rvReviewList.scrollToPosition(0)
                binding.rvReviewList.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            when (flagType) {
                ALL_REVIEW -> viewModel.refreshReviewAllList(movieId)
                POSITIVE_REVIEW -> viewModel.refreshReviewPositiveList(movieId)
                NEGATIVE_REVIEW -> viewModel.refreshReviewNegativeList(movieId)
                NEUTRAL_REVIEW -> viewModel.refreshReviewNeutralList(movieId)
            }
        }
    }

    private fun setupReviewOnClickListener() {
        adapter.reviewOnClickListener = {
            Log.d("test", it.toString())
            startActivity(ReviewFullActivity.getIntent(this, it, movieName))
        }
    }

    companion object {
        private const val EXTRA_FROM_ID = "movieId"
        private const val EXTRA_MOVIE_NAME = "movieName"

        private const val ALL_REVIEW = 0
        private const val POSITIVE_REVIEW = 1
        private const val NEGATIVE_REVIEW = 2
        private const val NEUTRAL_REVIEW = 3

        fun getIntent(context: Context, movieId: Int, movieName: String): Intent {
            return Intent(context, ReviewListActivity::class.java)
                .putExtra(EXTRA_FROM_ID, movieId)
                .putExtra(EXTRA_MOVIE_NAME, movieName)
        }

    }
}


