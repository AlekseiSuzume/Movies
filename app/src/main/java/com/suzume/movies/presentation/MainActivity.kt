package com.suzume.movies.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityMainBinding
import com.suzume.movies.presentation.adapter.movie.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovieAdapter
    private var flagSearch = false
    private var searchName = ""

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
            if (!flagSearch) {
                adapter.submitList(it)
            }
        }
    }

    private fun setupOnMovieClickListener() {
        adapter.onClickListener = {
            startActivity(MovieDetailActivity.newIntent(this, it.id, it.name))
        }
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            if (!flagSearch) {
                viewModel.refreshMoviesLiveData()
            } else {
                viewModel.refreshSearchMovieLiveData(searchName)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu?.findItem(R.id.menuItemSearch)
        val searchView = menuItem?.actionView as SearchView

        searchView.queryHint = resources.getString(R.string.search_movie)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query == null || query.isEmpty()) {
                    return false
                } else {
                    searchName = query.trim()
                    viewModel.refreshSearchMovieLiveData(searchName)
                    viewModel.searchMovies.observe(this@MainActivity) {
                        flagSearch = true
                        if (flagSearch) {
                            adapter.submitList(it)
                        }
                    }
                    removeRecyclerAnimation()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText.isEmpty()) {
                    viewModel.movies.observe(this@MainActivity) {
                        flagSearch = false
                        if (!flagSearch) {
                            adapter.submitList(it)
                        }
                    }
                    removeRecyclerAnimation()
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItemFavoriteMovies) {
            val intent = FavoriteMoviesActivity.getIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeRecyclerAnimation() {
        binding.recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.recyclerView.scrollToPosition(0)
                binding.recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}