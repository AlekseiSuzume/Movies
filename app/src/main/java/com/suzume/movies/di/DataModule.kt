package com.suzume.movies.di

import android.app.Application
import com.suzume.movies.data.database.MovieDatabase
import com.suzume.movies.data.database.MoviesDao
import com.suzume.movies.data.network.ApiFactory
import com.suzume.movies.data.network.ApiService
import com.suzume.movies.data.repository.imageRepository.ImageRepositoryImpl
import com.suzume.movies.data.repository.movieRepository.MovieRepositoryImpl
import com.suzume.movies.data.repository.reviewRepository.ReviewRepositoryImpl
import com.suzume.movies.domain.repository.ImageRepository
import com.suzume.movies.domain.repository.MovieRepository
import com.suzume.movies.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository

    @AppScope
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @AppScope
    @Binds
    fun bindReviewRepository(impl: ReviewRepositoryImpl): ReviewRepository

    companion object {

        @Provides
        fun provideMovieDao(application: Application): MoviesDao {
            return MovieDatabase.getInstance(application).moviesDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.getApiService()
        }

    }

}