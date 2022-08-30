package com.suzume.movies.di

import androidx.lifecycle.ViewModel
import com.suzume.movies.presentation.favoriteScreen.FavoriteMoviesViewModel
import com.suzume.movies.presentation.imageScreen.ImageListViewModel
import com.suzume.movies.presentation.mainScreen.MainViewModel
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailFromDbViewModel
import com.suzume.movies.presentation.movieDetailScreen.MovieDetailViewModel
import com.suzume.movies.presentation.reviewScreen.ReviewListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(FavoriteMoviesViewModel::class)
    fun bindFavoriteMoviesViewModel(impl: FavoriteMoviesViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ImageListViewModel::class)
    fun bindImageListViewModel(impl: ImageListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MovieDetailFromDbViewModel::class)
    fun bindMovieDetailFromDbViewModel(impl: MovieDetailFromDbViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(impl: MovieDetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ReviewListViewModel::class)
    fun bindReviewListViewModel(impl: ReviewListViewModel): ViewModel

}