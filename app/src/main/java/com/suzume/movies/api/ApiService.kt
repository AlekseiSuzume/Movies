package com.suzume.movies.api

import com.suzume.movies.BuildConfig
import com.suzume.movies.data.pojo.imageResponse.ImageResponse
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.data.pojo.movieShortResponse.MovieResponse
import com.suzume.movies.data.pojo.reviewResponse.ReviewResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    fun loadMovies(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("field") searchField: String = "rating.kp",
        @Query("search") searchValue: String = "5-10",
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Int = -1,
        @Query("limit") limit: Int = 30,
        @Query("page") page: Int = 1,
    ): Single<MovieResponse>

    @GET("movie")
    fun searchMovie(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("page") page: Int = 1,
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Int = -1,
        @Query("isStrict") isStrict: String = "false",
        @Query("field") searchField: String = "name",
        @Query("search") searchName: String,
    ): Single<MovieResponse>

    @GET("movie")
    fun loadMovieDetail(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("field") field: String = "id",
        @Query("search") searchId: Int,
    ): Single<MovieDetail>

    @GET("review")
    fun loadMovieReviewAll(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 5,
        @Query("page") page: Int = 1,
        @Query("sortField") sortField: String = "reviewLikes",
        @Query("sortType") sortType: Int = -1,
        @Query("field") movieId: String = "movieId",
        @Query("search") searchId: Int,
    ): Single<ReviewResponse>

    @GET("review")
    fun loadMovieReviewTyped(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 5,
        @Query("page") page: Int = 1,
        @Query("field") movieId: String = "movieId",
        @Query("sortField") sortField: String = "reviewLikes",
        @Query("sortType") sortType: Int = -1,
        @Query("search") searchId: Int,
        @Query("field") typeParam: String = "type",
        @Query("search") searchType: String,
    ): Single<ReviewResponse>

    @GET("image")
    fun loadMovieImage(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 30,
        @Query("page") page: Int = 1,
        @Query("field") field: String = "movieId",
        @Query("search") searchId: Int,
    ): Single<ImageResponse>

    private companion object {
        const val TOKEN_DEFAULT = BuildConfig.API_TOKEN
    }
}