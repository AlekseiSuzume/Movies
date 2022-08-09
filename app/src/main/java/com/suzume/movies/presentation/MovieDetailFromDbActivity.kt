package com.suzume.movies.presentation

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
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.databinding.ActivityMovieDetailFromDbBinding
import com.suzume.movies.presentation.adapter.actor.ActorAdapter
import com.suzume.movies.presentation.adapter.movieTeam.MovieTeamAdapter
import kotlin.properties.Delegates

class MovieDetailFromDbActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_FROM_ID = "movieId"
        fun getIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieDetailFromDbActivity::class.java)
                .putExtra(EXTRA_FROM_ID, movieId)
        }
    }

    private lateinit var binding: ActivityMovieDetailFromDbBinding
    private lateinit var viewModel: MovieDetailFromDbViewModel
    private val actorAdapter = ActorAdapter()
    private val movieTeamAdapter = MovieTeamAdapter()
    private var movieId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMovieDetailFromDbBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initSetupContent()

    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MovieDetailFromDbViewModel::class.java]
        movieId = intent.getIntExtra(EXTRA_FROM_ID, 0)
        viewModel.getSingleMovieDetailFromDb(movieId)

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
        viewModel.movieDetailFromDb.observe(this) {
            setupContent(it)
        }
    }

    private fun setupContent(movieDetail: MovieDetail) {
        with(movieDetail) {
            Glide.with(this@MovieDetailFromDbActivity)
                .load(poster.url)
                .into(binding.ivPoster)
            with(binding) {
                ivName.text = name
                tvAlternativeName.text = alternativeName
                tvRating.text = rating.kp.toString()
                when (rating.kp) {
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
                        (votes.kp % 1000).toString())
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
                            .joinToString(", ") { it.name }
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
                    Toast.makeText(this@MovieDetailFromDbActivity, "Actors", Toast.LENGTH_SHORT)
                        .show()
                }
                favoriteObserver(movieDetail)
            }
            binding.root.visibility = View.VISIBLE
        }
    }

    private fun favoriteObserver(movieDetail: MovieDetail) {
        viewModel.getLiveDataMovieDetailFromDb(movieDetail.id).observe(this) {
            if (it == null) {
                binding.ivFavorite.setImageResource(R.drawable.baseline_star_off)
                binding.ivFavorite.setOnClickListener {
                    viewModel.addFavorite(movieDetail)
                }
            } else {
                binding.ivFavorite.setImageResource(R.drawable.baseline_star_on)
                binding.ivFavorite.setOnClickListener {
                    viewModel.removeFavorite(movieId)
                }
            }
        }
    }
}