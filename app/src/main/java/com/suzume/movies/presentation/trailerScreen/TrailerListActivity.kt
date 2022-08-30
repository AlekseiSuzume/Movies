package com.suzume.movies.presentation.trailerScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suzume.movies.databinding.ActivityTrailerListBinding
import com.suzume.movies.domain.models.movieDetail.TrailerDomainModel
import com.suzume.movies.presentation.adapters.trailerListScreen.TrailerListScreenAdapter

class TrailerListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTrailerListBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    private var trailers = mutableListOf<TrailerDomainModel>()
    private val adapter = TrailerListScreenAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setupOnTrailerClickListener()
    }

    private fun init() {
        intent.getParcelableArrayListExtra<TrailerDomainModel>(EXTRA_TRAILERS)
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

    companion object {
        private const val EXTRA_TRAILERS = "trailers"

        fun getIntent(context: Context, trailers: ArrayList<TrailerDomainModel>?): Intent {
            return Intent(context, TrailerListActivity::class.java)
                .putParcelableArrayListExtra(EXTRA_TRAILERS, trailers)
        }
    }
}