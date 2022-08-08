package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.databinding.ActivityMovieDetailBinding
import com.suzume.movies.presentation.adapter.actor.ActorAdapter
import com.suzume.movies.presentation.adapter.frame.FrameAdapter
import com.suzume.movies.presentation.adapter.movieTeam.MovieTeamAdapter
import com.suzume.movies.presentation.adapter.review.ReviewAdapter
import com.suzume.movies.presentation.adapter.trailer.TrailerAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.properties.Delegates

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_FROM_ID = "id"

        fun newIntent(context: Context, fromId: Int): Intent {
            return Intent(context, MovieDetailActivity::class.java)
                .putExtra(EXTRA_FROM_ID, fromId)
        }
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var movieTeamAdapter: MovieTeamAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var frameAdapter: FrameAdapter
    private lateinit var trailerAdapter: TrailerAdapter
    private var movieId by Delegates.notNull<Int>()
    private lateinit var viewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupContent()
        setupOnClickPersonListener()
        setupOnClickShowMoreListener()

    }


    private fun init() {
        movieId = intent.getIntExtra(EXTRA_FROM_ID, 0)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.refreshMovieDetailLiveData(movieId)
        viewModel.refreshReviewListLiveData(movieId)
        viewModel.refreshFrameLiveData(movieId)

        actorAdapter = ActorAdapter()
        binding.rvActors.adapter = actorAdapter
        binding.rvActors.layoutManager = GridLayoutManager(
            this,
            4,
            GridLayoutManager.HORIZONTAL,
            false
        )

        movieTeamAdapter = MovieTeamAdapter()
        binding.rvMovieTeam.adapter = movieTeamAdapter
        binding.rvMovieTeam.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        reviewAdapter = ReviewAdapter()
        binding.rvReview.adapter = reviewAdapter
        binding.rvReview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        frameAdapter = FrameAdapter()
        binding.rvFrame.adapter = frameAdapter
        binding.rvFrame.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        trailerAdapter = TrailerAdapter()
        binding.rvTrailer.adapter = trailerAdapter
        binding.rvTrailer.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun setupOnClickShowMoreListener() {
        actorAdapter.onClickShowMoreListener = {
            Toast.makeText(this, "OnClickShowMoreListener", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupOnClickPersonListener() {
        actorAdapter.onClickActorListener = {
            Toast.makeText(this, "OnClickPersonListener", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupContent() {
        viewModel.movieDetail.observe(this) { it ->
            Glide.with(this)
                .load(it.poster.url)
                .into(binding.ivPoster)
            with(binding) {
                ivName.text = it.name
                tvAlternativeName.text = it.alternativeName
                tvRating.text = it.rating.kp.toString()
                when (it.rating.kp) {
                    in 0.0..4.9 -> binding.tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_red_dark, theme)
                    )
                    in 5.0..6.9 -> binding.tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_orange_dark, theme)
                    )
                    in 7.0..10.0 -> binding.tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_green_dark, theme)
                    )
                }
                tvVotes.text =
                    root.resources.getString(R.string.votes, (it.votes.kp % 1000).toString())
                tvYearGenresCountriesLength.text =
                    root.resources.getString(
                        R.string.tvYearGenresCountriesLength,
                        it.year.toString(),
                        it.genres.map { it.name }.distinct().joinToString(", "),
                        it.countries.map { it.name }.distinct().joinToString(", "),
                        (it.movieLength / 60).toString(),
                        (it.movieLength - ((it.movieLength / 60) * 60))
                    )

                val spannableString = SpannableString(
                    root.resources.getString(
                        R.string.tv_actors,
                        it.persons
                            .filter { it.enProfession == "actor" }
                            .take(4)
                            .joinToString(", ") { it.name }
                    ))
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(
                            this@MovieDetailActivity,
                            "TextClickTest", Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                        ds.color = resources.getColor(R.color.secondary_text, theme)
                    }
                }
                spannableString.setSpan(
                    clickableSpan,
                    spannableString.length - 8,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString.setSpan(
                    StyleSpan(BOLD),
                    spannableString.length - 8,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tvActors.text = spannableString
                tvActors.movementMethod = LinkMovementMethod.getInstance()

                tvDescription.text = it.description
                tvActorCount.text = resources.getString(
                    R.string.actors_item_count,
                    it.persons.filter { it.enProfession == "actor" }.size.toString()
                )
                tvMovieTeamCount.text = resources.getString(
                    R.string.film_team_item_count,
                    it.persons.filter { it.enProfession != "actor" }.size.toString()
                )

                actorAdapter.submitList(it.persons.filter { it.enProfession == "actor" })
                movieTeamAdapter.submitList(
                    it.persons.filter { it.enProfession != "actor" }.distinctBy { it.name })
                llActors.setOnClickListener {
                    Toast.makeText(this@MovieDetailActivity, "Actors", Toast.LENGTH_SHORT).show()
                }

                trailerAdapter.submitList(it.videos.trailers)
                tvTrailerCount.text = it.videos.trailers.size.toString()

            }
            binding.root.visibility = View.VISIBLE
            favoriteObserver(it)
        }

        viewModel.reviewList.observe(this) {
            reviewAdapter.submitList(it.reviews)
            binding.tvReviewCount.text = it.total.toString()
        }

        viewModel.frameList.observe(this) {
            frameAdapter.submitList(it.frames)
            binding.tvFrameCount.text = it.total.toString()
        }
    }

    private fun favoriteObserver(movieDetail: MovieDetail) {
        viewModel.getFavoriteMovie(movieId)
            .observe(this) {
                if (it == null) {
                    with(binding.ivFavorite) {
                        setImageResource(R.drawable.baseline_star_off)
                        setOnClickListener {
                            viewModel.addFavorite(movieDetail)
                        }
                    }
                } else {
                    with(binding.ivFavorite) {
                        setImageResource(R.drawable.baseline_star_on)
                        setOnClickListener {
                            viewModel.removeFavorite(movieId)
                        }
                    }
                }
            }

    }
}