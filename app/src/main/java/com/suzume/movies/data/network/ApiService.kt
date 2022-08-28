package com.suzume.movies.data.network

import com.suzume.movies.BuildConfig
import com.suzume.movies.data.network.models.imageResponse.ImageResponse
import com.suzume.movies.data.network.models.movieDetailResponse.MovieDto
import com.suzume.movies.data.network.models.movieShortResponse.MovieResponse
import com.suzume.movies.data.network.models.reviewResponse.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun loadMovies(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("field") searchField: String = "rating.kp",
        @Query("search") searchValue: String = "5-10",
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Int = -1,
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 1,
    ): MovieResponse

    @GET("movie")
    suspend fun searchMovie(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("page") page: Int = 1,
        @Query("sortField") sortField: String = "votes.kp",
        @Query("sortType") sortType: Int = -1,
        @Query("isStrict") isStrict: String = "false",
        @Query("field") searchField: String = "name",
        @Query("search") searchName: String,
    ): MovieResponse

    @GET("movie")
    suspend fun loadMovieDetail(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("field") field: String = "id",
        @Query("search") searchId: Int,
    ): MovieDto

    @GET("review")
    suspend fun loadMovieReviewAll(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 5,
        @Query("page") page: Int = 1,
        @Query("sortField") sortField: String = "reviewLikes",
        @Query("sortType") sortType: Int = -1,
        @Query("field") movieId: String = "movieId",
        @Query("search") searchId: Int,
    ): ReviewResponse

    @GET("review")
    suspend fun loadMovieReviewTyped(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 5,
        @Query("page") page: Int = 1,
        @Query("field") movieId: String = "movieId",
        @Query("sortField") sortField: String = "reviewLikes",
        @Query("sortType") sortType: Int = -1,
        @Query("search") searchId: Int,
        @Query("field") typeParam: String = "type",
        @Query("search") searchType: String,
    ): ReviewResponse

    @GET("image")
    suspend fun loadMovieImage(
        @Query("token") token: String = TOKEN_DEFAULT,
        @Query("limit") limit: Int = 30,
        @Query("page") page: Int = 1,
        @Query("field") field: String = "movieId",
        @Query("search") searchId: Int,
    ): ImageResponse

    private companion object {
        const val TOKEN_DEFAULT = BuildConfig.API_TOKEN
    }
}