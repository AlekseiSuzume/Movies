package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.suzume.movies.R
import com.suzume.movies.databinding.ActivityImageListBinding
import com.suzume.movies.presentation.adapter.imageListScreen.ImageListScreenAdapter
import kotlin.properties.Delegates

class ImageListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ID = "movieId"
        fun getIntent(context: Context, movieId: Int): Intent {
            return Intent(context, ImageListActivity::class.java).putExtra(EXTRA_ID, movieId)
        }

        private const val FRAME_IMAGE = 0
        private const val SHOOTING_IMAGE = 1
        private const val POSTER_IMAGE = 2
    }

    private lateinit var binding: ActivityImageListBinding
    private lateinit var viewModel: ImageListViewModel
    private lateinit var adapter: ImageListScreenAdapter
    private var movieId by Delegates.notNull<Int>()
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()
        setupOnReachEndListener()
        setupOnImageClickListener()

        binding.btnFrames.setOnClickListener {
            framesBtnOnClickListener()
        }

        binding.btnShooting.setOnClickListener {
            shootingBtnOnClickListener()
        }

        binding.btnPosters.setOnClickListener {
            postersBtnOnClickListener()
        }

    }

    private fun init() {
        movieId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel = ViewModelProvider(this)[ImageListViewModel::class.java]
        viewModel.refreshImageLiveData(movieId)
        adapter = ImageListScreenAdapter()
        binding.rvImage.adapter = adapter
        binding.rvImage.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )

        viewModel.imageList.observe(this) {
            adapter.submitList(it)
        }
        framesBtnOn()

    }

    private fun framesBtnOnClickListener() {
        setButtonBackground(FRAME_IMAGE)
    }

    private fun shootingBtnOnClickListener() {
        setButtonBackground(SHOOTING_IMAGE)
    }

    private fun postersBtnOnClickListener() {
        setButtonBackground(POSTER_IMAGE)
    }

    private fun setButtonBackground(flagType: Int) {
        when (flagType) {
            FRAME_IMAGE -> {
                framesBtnOn()
                shootingBtnOff()
                postersBtnOff()
            }
            SHOOTING_IMAGE -> {
                shootingBtnOn()
                framesBtnOff()
                postersBtnOff()
            }
            POSTER_IMAGE -> {
                postersBtnOn()
                framesBtnOff()
                shootingBtnOff()
            }
        }
    }

    private fun framesBtnOn() {
        binding.btnFrames.setTextColor(resources.getColor(R.color.white, theme))
        binding.btnFrames.setBackgroundColor(resources.getColor(R.color.black, theme))
    }

    private fun framesBtnOff() {
        binding.btnFrames.setTextColor(resources.getColor(R.color.primary_text, theme))
        binding.btnFrames.setBackgroundColor(resources.getColor(R.color.divider_color, theme))
    }

    private fun shootingBtnOn() {
        binding.btnShooting.setTextColor(resources.getColor(R.color.white, theme))
        binding.btnShooting.setBackgroundColor(resources.getColor(R.color.black, theme))
    }

    private fun shootingBtnOff() {
        binding.btnShooting.setTextColor(resources.getColor(R.color.primary_text, theme))
        binding.btnShooting.setBackgroundColor(resources.getColor(R.color.divider_color, theme))
    }

    private fun postersBtnOn() {
        binding.btnPosters.setTextColor(resources.getColor(R.color.white, theme))
        binding.btnPosters.setBackgroundColor(resources.getColor(R.color.black, theme))
    }

    private fun postersBtnOff() {
        binding.btnPosters.setTextColor(resources.getColor(R.color.primary_text, theme))
        binding.btnPosters.setBackgroundColor(resources.getColor(R.color.divider_color, theme))
    }

    private fun setupOnReachEndListener() {
        adapter.onReachEndListener = {
            viewModel.refreshImageLiveData(movieId)
        }
    }

    private fun setupOnImageClickListener(){
        adapter.onImageClickListener = {
            startActivity(ImageFullActivity.getIntent(this, it.url))
        }
    }
}