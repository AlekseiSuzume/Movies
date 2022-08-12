package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer
import com.suzume.movies.databinding.ActivityTrailerListBinding
import com.suzume.movies.presentation.adapter.trailerListScreen.TrailerListScreenAdapter

class TrailerListActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_FROM_TRAILERS = "trailers"

        fun getIntent(context: Context, trailers: ArrayList<Trailer>): Intent {
            return Intent(context, TrailerListActivity::class.java)
                .putParcelableArrayListExtra(EXTRA_FROM_TRAILERS, trailers)
        }
    }

    private lateinit var binding: ActivityTrailerListBinding
    private var trailers = mutableListOf<Trailer>()
    private val adapter = TrailerListScreenAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityTrailerListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setupOnTrailerClickListener()
    }

    private fun init() {
        intent.getParcelableArrayListExtra<Trailer>(EXTRA_FROM_TRAILERS)
            ?.let { trailers.addAll(it) }
        adapter.submitList(trailers)
        binding.rvTrailerScreen.adapter = adapter

    }

    private fun setupOnTrailerClickListener() {
        adapter.onTrailerClickListener = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }
    }

}