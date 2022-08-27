package com.suzume.movies.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suzume.movies.data.database.models.MovieDbModel

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favorite_movies")
    suspend fun getAllFavoriteMovies2(): List<MovieDbModel>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    fun getFavoriteMovieLiveData(movieId: Int): LiveData<MovieDbModel>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteMovie(movieId: Int): MovieDbModel

    @Insert
    suspend fun insertMovie(movieDbModel: MovieDbModel)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun removeMovie(movieId: Int)

}