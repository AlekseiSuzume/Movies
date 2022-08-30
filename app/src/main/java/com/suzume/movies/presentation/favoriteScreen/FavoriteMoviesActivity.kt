package com.suzume.movies.presentation.favoriteScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.App.Companion.appComponent
import com.suzume.movies.databinding.ActivityFavoriteBinding
import com.suzume.movies.presentation.ViewModelFactory
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailFromDbActivity
import com.suzume.movies.presentation.adapters.movie.MovieAdapter
import javax.inject.Inject

class FavoriteMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteMoviesViewModel::class.java]
    }

    private val binding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        setupOnMovieClickListener()
    }

    private fun init() {
        recyclerView = binding.rvFavoriteMovies
        recyclerView.layoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        adapter = MovieAdapter()
        recyclerView.adapter = adapter
        viewModel.allFavoriteMovies.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupOnMovieClickListener() {
        adapter.onMovieClickListener = {
            startActivity(MovieDetailFromDbActivity.getIntent(this, it.id))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavoriteMoviesFromDb2()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FavoriteMoviesActivity::class.java)
        }
    }
}