package com.suzume.movies.presentation.favoriteScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ActivityFavoriteBinding
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailFromDbActivity
import com.suzume.movies.presentation.adapters.movie.MovieAdapter

class FavoriteMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityFavoriteBinding
    lateinit var viewModel: FavoriteMoviesViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFavoriteBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setupOnMovieClickListener()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[FavoriteMoviesViewModel::class.java]

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