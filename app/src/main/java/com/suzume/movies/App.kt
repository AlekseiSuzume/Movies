package com.suzume.movies

import android.app.Application
import androidx.room.Room
import com.suzume.movies.data.MovieDatabase

class App : Application() {

    companion object {
        private lateinit var db: MovieDatabase
        private const val DB_NAME = "favoriteMovies.db"
        fun getDatabase(): MovieDatabase {
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            MovieDatabase::class.java,
            DB_NAME)
            .build()
    }
}