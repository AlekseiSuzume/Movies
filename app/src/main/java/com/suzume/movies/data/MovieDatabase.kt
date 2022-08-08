package com.suzume.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suzume.movies.data.converters.*
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail

@Database(entities = [MovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(
    PersonConverter::class,
    GenreConverter::class,
    CountryConverter::class,
    TrailerConverter::class
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}