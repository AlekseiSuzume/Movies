package com.suzume.movies.presentation.movieDetailScreen

import android.content.Context
import android.content.Intent
import android.graphics.Typeface.BOLD
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suzume.movies.App.Companion.appComponent
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMovieDetailBinding
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel
import com.suzume.movies.domain.models.person.PersonDomainModel
import com.suzume.movies.presentation.ViewModelFactory
import com.suzume.movies.presentation.adapters.actor.ActorAdapter
import com.suzume.movies.presentation.adapters.image.ImageAdapter
import com.suzume.movies.presentation.adapters.movieTeam.MovieTeamAdapter
import com.suzume.movies.presentation.adapters.review.ReviewAdapter
import com.suzume.movies.presentation.adapters.trailer.TrailerAdapter
import com.suzume.movies.presentation.imageScreen.ImageFullActivity
import com.suzume.movies.presentation.imageScreen.ImageListActivity
import com.suzume.movies.presentation.personScreen.PersonListActivity
import com.suzume.movies.presentation.reviewScreen.ReviewFullActivity
import com.suzume.movies.presentation.reviewScreen.ReviewListActivity
import com.suzume.movies.presentation.trailerScreen.TrailerListActivity
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    private lateinit var actorAdapter: ActorAdapter
    private lateinit var movieTeamAdapter: MovieTeamAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var trailerAdapter: TrailerAdapter
    private var movieId by Delegates.notNull<Int>()
    private var movieName by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        loadMovieDetail()
        setupOnClickListener()
        setupOnShowMoreClickListener()
    }

    private fun init() {
        movieId = intent.getIntExtra(EXTRA_ID, 0)
        movieName = intent.getStringExtra(EXTRA_NAME) ?: "Movies"
        supportActionBar?.title = movieName

        viewModel.refreshMovieDetailLiveData(movieId)
        viewModel.refreshReviewLiveData(movieId)
        viewModel.refreshImageLiveData(movieId)

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

        imageAdapter = ImageAdapter()
        binding.rvFrame.adapter = imageAdapter
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
        viewModel.movieDtoFromApi.observe(this) {
            setupContent(it)
        }
    }

    private fun setupContent(movieDomainModel: MovieDomainModel) {
        with(movieDomainModel) {
            Glide.with(this@MovieDetailActivity)
                .load(posterUrl)
                .into(binding.ivPoster)
            with(binding) {
                ivName.text = name
                tvAlternativeName.text = alternativeName
                tvRating.text = rating.toString()
                when (rating) {
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
                    resources.getString(R.string.votes, (votes % 1000).toString())
                tvYearGenresCountriesLength.text =
                    resources.getString(
                        R.string.tvYearGenresCountriesLength,
                        year.toString(),
                        genres,
                        countries,
                        (movieLength / 60).toString(),
                        (movieLength - ((movieLength / 60) * 60))
                    )

                val spannableString = SpannableString(
                    resources.getString(
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
                                    as ArrayList<PersonDomainModel>,
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
                                as ArrayList<PersonDomainModel>,
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
                                as ArrayList<PersonDomainModel>,
                        PersonListActivity.MOVIE_TEAM
                    ))
                }
                llReview.setOnClickListener {
                    startActivity(ReviewListActivity.getIntent(
                        this@MovieDetailActivity,
                        movieId,
                        movieName
                    ))
                }

                llFrame.setOnClickListener {
                    startActivity(ImageListActivity.getIntent(this@MovieDetailActivity, movieId))
                }

                llTrailer.setOnClickListener {
                    startActivity(TrailerListActivity.getIntent(
                        this@MovieDetailActivity,
                        trailers as ArrayList<TrailerDomainModel>
                    ))
                }
                trailerAdapter.submitList(trailers)
                tvTrailerCount.text = (trailers?.size ?: 0).toString()
                favoriteObserver(movieDomainModel)
            }
        }

        viewModel.reviewList.observe(this) {
            reviewAdapter.submitList(it.reviews)
            binding.tvReviewCount.text = it.totalReviews.toString()
        }


        viewModel.imagesList.observe(this) {
            imageAdapter.submitList(it.images)
            binding.tvFrameCount.text = it.totalImages.toString()
        }
        binding.root.visibility = View.VISIBLE
    }

    private fun favoriteObserver(movieDomainModel: MovieDomainModel) {
        viewModel.getFavoriteMovie(movieDomainModel.id)
            .observe(this) {
                if (it == null) {
                    with(binding.ivFavorite) {
                        setImageResource(R.drawable.baseline_star_off)
                        setOnClickListener {
                            viewModel.addFavorite(movieDomainModel)
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

    private fun setupOnShowMoreClickListener() {
        actorAdapter.onShowMoreClickListener = {
            startActivity(PersonListActivity.getIntent(
                this,
                actorAdapter.currentList.toList() as ArrayList<PersonDomainModel>,
                PersonListActivity.ACTOR
            ))
        }
        movieTeamAdapter.onShowMoreClickListener = {
            startActivity(PersonListActivity.getIntent(
                this,
                movieTeamAdapter.currentList.toList() as ArrayList<PersonDomainModel>,
                PersonListActivity.MOVIE_TEAM
            ))
        }
        reviewAdapter.onShowMoreClickListener = {
            startActivity(ReviewListActivity.getIntent(this, movieId, movieName))
        }
        imageAdapter.onShowMoreClickListener = {
            startActivity(ImageListActivity.getIntent(this, movieId))
        }
        trailerAdapter.onShowMoreItemClickListener = {
            startActivity(TrailerListActivity.getIntent(
                this@MovieDetailActivity,
                trailerAdapter.currentList.toList() as ArrayList<TrailerDomainModel>
            ))
        }

    }

    private fun setupOnClickListener() {
        actorAdapter.onActorClickListener = {
            //TODO
        }
        reviewAdapter.onReviewClickListener = {
            startActivity(ReviewFullActivity.getIntent(this, it, movieName))
        }
        imageAdapter.onImageClickListener = {
            startActivity(ImageFullActivity.getIntent(this, it.imageUrl))
        }
        trailerAdapter.onTrailerItemClickListener = {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
        }
    }

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_NAME = "movieName"

        fun newIntent(context: Context, fromId: Int, movieName: String): Intent {
            return Intent(context, MovieDetailActivity::class.java)
                .putExtra(EXTRA_ID, fromId)
                .putExtra(EXTRA_NAME, movieName)
        }
    }

}