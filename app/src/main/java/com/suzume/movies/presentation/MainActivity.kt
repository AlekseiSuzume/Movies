package com.suzume.movies.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMainBinding
import com.suzume.movies.presentation.adapter.movie.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setupOnReachEndListener()
        setupOnMovieClickListener()

        viewModel.isLoading.observe(this) {
            when (it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun init() {
        adapter = MovieAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.movies.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setupOnMovieClickListener() {
        adapter.onClickListener = {
            startActivity(MovieDetailActivity.newIntent(this, it.id, it.name))
        }
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            viewModel.refreshMoviesLiveData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItemSearch = menu?.findItem(R.id.menuItemSearch)
        menuItemSearch?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemFavoriteMovies) {
            val intent = FavoriteMoviesActivity.getIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}