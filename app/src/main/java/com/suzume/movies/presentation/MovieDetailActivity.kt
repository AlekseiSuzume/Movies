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
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMovieDetailBinding
import com.suzume.movies.presentation.adapter.person.PersonAdapter
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
    private lateinit var adapter: PersonAdapter
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
        adapter = PersonAdapter()
        binding.rvActors.adapter = adapter
        binding.rvActors.layoutManager = GridLayoutManager(
            this,
            4,
            GridLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun setupOnClickShowMoreListener() {
        adapter.onClickShowMoreListener = {
            Toast.makeText(this, "onClickListenerShowMore", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupOnClickPersonListener() {
        adapter.onClickPersonListener = {
            Toast.makeText(this, "onClickListenerPerson", Toast.LENGTH_SHORT).show()
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
                adapter.submitList(it.persons.filter { it.enProfession == "actor" })
                constraintLayout.visibility = View.VISIBLE
            }
        }
    }

}