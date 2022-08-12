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
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.data.pojo.movieDetailResponse.Person
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer
import com.suzume.movies.databinding.ActivityMovieDetailBinding
import com.suzume.movies.presentation.adapter.actor.ActorAdapter
import com.suzume.movies.presentation.adapter.frame.FrameAdapter
import com.suzume.movies.presentation.adapter.movieTeam.MovieTeamAdapter
import com.suzume.movies.presentation.adapter.review.ReviewAdapter
import com.suzume.movies.presentation.adapter.trailer.TrailerAdapter
import kotlin.properties.Delegates

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_FROM_ID = "id"
        private const val EXTRA_FROM_NAME = "name"
        fun newIntent(context: Context, fromId: Int, movieName: String): Intent {
            return Intent(context, MovieDetailActivity::class.java)
                .putExtra(EXTRA_FROM_ID, fromId)
                .putExtra(EXTRA_FROM_NAME, movieName)
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
        binding =
            ActivityMovieDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        loadMovieDetail()
        setupOnClickPersonListener()
        setupOnClickShowMoreListener()

    }

    private fun init() {
        movieId = intent.getIntExtra(EXTRA_FROM_ID, 0)
        val movieName = intent.getStringExtra(EXTRA_FROM_NAME) ?: "Movies"
        supportActionBar?.title = movieName
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        viewModel.refreshMovieDetailLiveData(movieId)
        viewModel.refreshReviewLiveData(movieId)
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

    private fun loadMovieDetail() {
        viewModel.movieDetailFromApi.observe(this) { it ->
            setupContent(it)
        }
    }

    private fun setupContent(movieDetail: MovieDetail) {
        with(movieDetail) {
            Glide.with(this@MovieDetailActivity)
                .load(poster.url)
                .into(binding.ivPoster)
            with(binding) {
                ivName.text = name
                tvAlternativeName.text = alternativeName
                tvRating.text = rating.kp.toString()
                when (rating.kp) {
                    in 0.0..4.9 -> tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_red_dark, theme)
                    )
                    in 5.0..6.9 -> tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_orange_dark, theme)
                    )
                    in 7.0..10.0 -> tvRating.setTextColor(
                        resources.getColor(android.R.color.holo_green_dark, theme)
                    )
                }
                tvVotes.text =
                    root.resources.getString(R.string.votes, (votes.kp % 1000).toString())
                tvYearGenresCountriesLength.text =
                    root.resources.getString(
                        R.string.tvYearGenresCountriesLength,
                        year.toString(),
                        genres.map { name }.distinct().joinToString(", "),
                        countries.map { name }.distinct().joinToString(", "),
                        (movieLength / 60).toString(),
                        (movieLength - ((movieLength / 60) * 60))
                    )

                val spannableString = SpannableString(
                    root.resources.getString(
                        R.string.tv_actors,
                        persons
                            .filter { it.enProfession == "actor" }
                            .take(4)
                            .joinToString(", ") { it.name.toString() }
                    ))
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        startActivity(PersonListActivity.getIntent(
                            this@MovieDetailActivity,
                            persons
                                .filter { it.enProfession == "actor" }
                                .distinctBy { it.name }
                                    as ArrayList<Person>,
                            PersonListActivity.ACTOR
                        ))
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

                tvDescription.text = description
                tvActorCount.text = resources.getString(
                    R.string.actors_item_count,
                    persons.filter { it.enProfession == "actor" }.size.toString()
                )
                tvMovieTeamCount.text = resources.getString(
                    R.string.film_team_item_count,
                    persons.filter { it.enProfession != "actor" }.size.toString()
                )

                actorAdapter.submitList(persons.filter { it.enProfession == "actor" })
                llActors.setOnClickListener {
                    startActivity(PersonListActivity.getIntent(
                        this@MovieDetailActivity,
                        persons
                            .filter { it.enProfession == "actor" }
                            .distinctBy { it.name }
                                as ArrayList<Person>,
                        PersonListActivity.ACTOR
                    ))
                }
                movieTeamAdapter.submitList(
                    persons.filter { it.enProfession != "actor" }.distinctBy { it.name })
                llMovieTeam.setOnClickListener {
                    startActivity(PersonListActivity.getIntent(
                        this@MovieDetailActivity,
                        persons
                            .filter { it.enProfession != "actor" }
                            .distinctBy { it.name }
                                as ArrayList<Person>,
                        PersonListActivity.MOVIE_TEAM
                    ))
                }
                llReview.setOnClickListener {
                    startActivity(ReviewListActivity.getIntent(
                        this@MovieDetailActivity,
                        movieId
                    ))
                }

                llFrame.setOnClickListener {
                    startActivity(ImageListActivity.getIntent(this@MovieDetailActivity, movieId))
                }

                llTrailer.setOnClickListener {
                    startActivity(TrailerListActivity.getIntent(
                        this@MovieDetailActivity,
                        videos.trailers as ArrayList<Trailer>
                    ))
                }
                trailerAdapter.submitList(videos.trailers)
                tvTrailerCount.text = videos.trailers.size.toString()
                favoriteObserver(movieDetail)
            }
            binding.root.visibility = View.VISIBLE
        }

        viewModel.reviewList.observe(this) {
            reviewAdapter.submitList(it.reviews)
            binding.tvReviewCount.text = it.total.toString()
        }


        viewModel.frameList.observe(this) {
            frameAdapter.submitList(it.images)
            binding.tvFrameCount.text = it.total.toString()
        }
    }

    private fun favoriteObserver(movieDetail: MovieDetail) {
        viewModel.getFavoriteMovie(movieDetail.id)
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

    private fun setupOnClickShowMoreListener() {
        actorAdapter.showMoreOnClickListener = {
            startActivity(PersonListActivity.getIntent(
                this,
                actorAdapter.currentList.toList() as ArrayList<Person>,
                PersonListActivity.ACTOR
            ))
        }
        movieTeamAdapter.onShowMoreClickListener = {
            startActivity(PersonListActivity.getIntent(
                this,
                movieTeamAdapter.currentList.toList() as ArrayList<Person>,
                PersonListActivity.MOVIE_TEAM
            ))
        }
        reviewAdapter.onShowMoreClickListener = {
            startActivity(ReviewListActivity.getIntent(this, movieId))
        }
    }

    private fun setupOnClickPersonListener() {
        actorAdapter.actorOnClickListener = {
            Toast.makeText(this, "OnClickPersonListener", Toast.LENGTH_SHORT).show()
        }
    }

}