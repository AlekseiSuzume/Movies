package com.suzume.movies.di

import android.app.Application
import com.suzume.movies.presentation.favoriteScreen.FavoriteMoviesActivity
import com.suzume.movies.presentation.imageScreen.ImageListActivity
import com.suzume.movies.presentation.mainScreen.MainActivity
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailActivity
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailFromDbActivity
import com.suzume.movies.presentation.reviewScreen.ReviewListActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DataModule::class,
        PresentationModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(activity: FavoriteMoviesActivity)

    fun inject(activity: ImageListActivity)

    fun inject(activity: MainActivity)

    fun inject(activity: MovieDetailActivity)

    fun inject(activity: MovieDetailFromDbActivity)

    fun inject(activity: ReviewListActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent

    }

}