package com.suzume.movies.presentation.imageScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityImageFullBinding

class ImageFullActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityImageFullBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        loadImage()

    }

    private fun init() {
        imageUrl = intent.getStringExtra(EXTRA_URL).toString()
    }

    private fun loadImage() {
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.no_poster)
            .into(binding.ivFullImage)
    }

    companion object {
        private const val EXTRA_URL = "imageUrl"

        fun getIntent(context: Context, imageUrl: String): Intent {
            return Intent(context, ImageFullActivity::class.java)
                .putExtra(EXTRA_URL, imageUrl)
        }

    }
}