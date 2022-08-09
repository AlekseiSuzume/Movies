package com.suzume.movies.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMainBinding
import com.suzume.movies.presentation.adapter.movie.MovieAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            startActivity(MovieDetailActivity.newIntent(this, it.id, false))
        }
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            viewModel.refreshMoviesLiveData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemFavoriteMovies){
            val intent = FavoriteMoviesActivity.getIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}