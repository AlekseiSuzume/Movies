package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityImageFullBinding

class ImageFullActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_URL = "imageUrl"

        fun getIntent(context: Context, imageUrl: String): Intent {
            return Intent(context, ImageFullActivity::class.java)
                .putExtra(EXTRA_URL, imageUrl)
        }

    }

    private lateinit var binding: ActivityImageFullBinding
    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        loadImage()

    }

    private fun loadImage() {
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.no_poster)
            .into(binding.ivFullImage)
    }

    private fun init() {
        imageUrl = intent.getStringExtra(EXTRA_URL).toString()
    }
}