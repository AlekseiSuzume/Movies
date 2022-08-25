package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suzume.movies.databinding.ActivityFavoriteBinding
import com.suzume.movies.presentation.adapter.favoriteMovieAdaper.FavoriteMovieAdapter

class FavoriteMoviesActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FavoriteMoviesActivity::class.java)
        }
    }

    lateinit var binding: ActivityFavoriteBinding
    lateinit var viewModel: FavoriteMoviesActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFavoriteBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(this)[FavoriteMoviesActivityViewModel::class.java]
        recyclerView = binding.rvFavoriteMovies
        recyclerView.layoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        adapter = FavoriteMovieAdapter()
        recyclerView.adapter = adapter
        viewModel.allFavoriteMovies.observe(this) {
            adapter.submitList(it)
        }

        setupOnMovieClickListener()
    }

    private fun setupOnMovieClickListener() {
        adapter.onMovieClickListener = {
            startActivity(MovieDetailFromDbActivity.getIntent(this, it.id))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavoriteMoviesFromDb()
    }
}