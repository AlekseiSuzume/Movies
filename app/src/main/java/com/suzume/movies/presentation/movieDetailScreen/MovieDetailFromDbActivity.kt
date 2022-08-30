package com.suzume.movies.presentation.movieDetailScreen

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
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
import com.suzume.movies.App.Companion.appComponent
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMovieDetailFromDbBinding
import com.suzume.movies.domain.models.movieDetail.MovieDomainModel
import com.suzume.movies.domain.models.person.PersonDomainModel
import com.suzume.movies.presentation.ViewModelFactory
import com.suzume.movies.presentation.adapters.actor.ActorAdapter
import com.suzume.movies.presentation.adapters.movieTeam.MovieTeamAdapter
import com.suzume.movies.presentation.personScreen.PersonListActivity
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailFromDbActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MovieDetailFromDbViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMovieDetailFromDbBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    private val actorAdapter = ActorAdapter()
    private val movieTeamAdapter = MovieTeamAdapter()
    private var movieId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        initSetupContent()
        setupOnShowMoreClickListener()
    }

    private fun init() {
        movieId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.getMovieFromDb(movieId)

        binding.rvActors.adapter = actorAdapter
        binding.rvActors.layoutManager = GridLayoutManager(
            this,
            4,
            GridLayoutManager.HORIZONTAL,
            false
        )

        binding.rvMovieTeam.adapter = movieTeamAdapter
        binding.rvMovieTeam.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false)

    }

    private fun initSetupContent() {
        viewModel.movieFromDb.observe(this) {
            setupContent(it)
        }
    }

    private fun setupContent(movieDomainModel: MovieDomainModel) {
        with(movieDomainModel) {
            Glide.with(this@MovieDetailFromDbActivity)
                .load(posterUrl)
                .into(binding.ivPoster)
            with(binding) {
                ivName.text = name
                tvAlternativeName.text = alternativeName
                tvRating.text = rating.toString()
                when (rating) {
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
                    root.resources.getString(R.string.votes,
                        (votes % 1000).toString())
                tvYearGenresCountriesLength.text =
                    root.resources.getString(
                        R.string.tvYearGenresCountriesLength,
                        year.toString(),
                        genres,
                        countries,
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
                        Toast.makeText(
                            this@MovieDetailFromDbActivity,
                            "TextClickTest", Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                        ds.color =
                            resources.getColor(R.color.secondary_text, theme)
                    }
                }
                spannableString.setSpan(
                    clickableSpan,
                    spannableString.length - 8,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
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
                movieTeamAdapter.submitList(
                    persons.filter { it.enProfession != "actor" }.distinctBy { it.name })
                llActors.setOnClickListener {
                    startActivity(PersonListActivity.getIntent(
                        this@MovieDetailFromDbActivity,
                        persons
                            .filter { it.enProfession == "actor" }
                            .distinctBy { it.name }
                                as ArrayList<PersonDomainModel>,
                        PersonListActivity.ACTOR
                    ))
                }
                llMovieTeam.setOnClickListener {
                    startActivity(PersonListActivity.getIntent(
                        this@MovieDetailFromDbActivity,
                        persons
                            .filter { it.enProfession != "actor" }
                            .distinctBy { it.name }
                                as ArrayList<PersonDomainModel>,
                        PersonListActivity.MOVIE_TEAM
                    ))
                }
                favoriteObserver(movieDomainModel)
            }
            binding.root.visibility = View.VISIBLE
        }
    }

    private fun favoriteObserver(movieDomainModel: MovieDomainModel) {
        viewModel.getLiveDataMovieDetailFromDb(movieDomainModel.id).observe(this) {
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
    }

    companion object {
        private const val EXTRA_ID = "movieId"
        fun getIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieDetailFromDbActivity::class.java)
                .putExtra(EXTRA_ID, movieId)
        }
    }
}