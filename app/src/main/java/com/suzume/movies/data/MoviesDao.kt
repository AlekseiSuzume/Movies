package com.suzume.movies.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): Observable<List<MovieDetail>>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    fun getFavoriteMovieLiveData(movieId: Int): LiveData<MovieDetail>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    fun getFavoriteMovie(movieId: Int): Single<MovieDetail>

    @Insert
    fun insertMovie(movie: MovieDetail): Completable

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    fun removeMovie(movieId: Int): Completable
}