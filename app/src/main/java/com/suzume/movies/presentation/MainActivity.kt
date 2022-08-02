package com.suzume.movies.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.suzume.movies.databinding.ActivityMainBinding
import com.suzume.movies.presentation.adapter.movie.MovieAdapter

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
        setupOnClickListener()

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

    private fun setupOnClickListener() {
        adapter.onClickListener = {
            startActivity(MovieDetailActivity.newIntent(this, it.id))
        }
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            viewModel.refreshMoviesLiveData()
        }
    }

}