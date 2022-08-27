package com.suzume.movies.data.database

import androidx.room.Database
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

//    companion object {
//        private var db: MovieDatabase? = null
//        private const val DB_NAME = "favoriteMovies.db"
//        private val LOCK = Any()
//
//        fun getDatabase(application: Application): MovieDatabase {
//            synchronized(LOCK) {
//                db?.let {
//                    return it
//                }
//            }
//            synchronized(LOCK) {
//                db?.let { return it }
//                val instance = Room.databaseBuilder(
//                    application,
//                    MovieDatabase::class.java,
//                    DB_NAME
//                ).build()
//                db = instance
//                return instance
//            }
//        }
//    }

}