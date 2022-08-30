package com.suzume.movies.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suzume.movies.data.database.converters.PersonConverter
import com.suzume.movies.data.database.models.MovieDbModel

@Database(entities = [MovieDbModel::class], version = 1, exportSchema = false)
@TypeConverters(
    PersonConverter::class,
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        private const val DB_NAME = "movies.db"
        private var INSTANCE: MovieDatabase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): MovieDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}