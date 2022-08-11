package com.suzume.movies.api

import com.suzume.movies.data.pojo.frameResponse.FrameResponse
import com.suzume.movies.data.pojo.movieDetailResponse.MovieDetail
import com.suzume.movies.data.pojo.movieShortResponse.MovieResponse
import com.suzume.movies.data.pojo.reviewResponse.ReviewResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    private companion object {
        const val QUERY_PARAM_TOKEN = "token"
        const val QUERY_PARAM_FIELD = "field"
        const val QUERY_PARAM_SEARCH = "search"
        const val QUERY_PARAM_SORT_FIELD = "sortField"
        const val QUERY_PARAM_SORT_TYPE = "sortType"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_PAGE = "page"

        const val TOKEN_DEFAULT = "H682YKN-SQC4F06-PYWC291-YAK7HZP"
        const val MOVIE_SEARCH_FIELD_DEFAULT = "rating.kp"
        const val MOVIE_SEARCH_VALUE_DEFAULT = "5-10"
        const val MOVIE_SORT_FIELD_DEFAULT = "votes.kp"
        const val MOVIE_SORT_VALUE_DEFAULT = -1
        const val MOVIE_LIMIT_DEFAULT = 30
        const val PAGE_DEFAULT = 1
        const val TYPE_FIELD_DEFAULT = "type"

        const val ID_PARAM_DETAIL_DEFAULT = "id"

        const val SEARCH_FIELD_MOVIE_ID = "movieId"
        const val SEARCH_FIELD_LIMIT_REVIEW_DEFAULT = 5
        const val SEARCH_FIELD_LIMIT_FRAME_DEFAULT = 500
    }

    @GET("movie")
    fun loadMovies(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_FIELD) searchField: String = MOVIE_SEARCH_FIELD_DEFAULT,
        @Query(QUERY_PARAM_SEARCH) searchValue: String = MOVIE_SEARCH_VALUE_DEFAULT,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = MOVIE_SORT_FIELD_DEFAULT,
        @Query(QUERY_PARAM_SORT_TYPE) sortType: Int = MOVIE_SORT_VALUE_DEFAULT,
        @Query(QUERY_PARAM_LIMIT) limit: Int = MOVIE_LIMIT_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE_DEFAULT,
    ): Single<MovieResponse>


    @GET("movie")
    fun loadMovieDetail(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_FIELD) field: String = ID_PARAM_DETAIL_DEFAULT,
        @Query(QUERY_PARAM_SEARCH) searchId: Int,
    ): Single<MovieDetail>

    @GET("review")
    fun loadMovieReviewAll(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_LIMIT) limit: Int = SEARCH_FIELD_LIMIT_REVIEW_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE_DEFAULT,
        @Query(QUERY_PARAM_FIELD) movieId: String = SEARCH_FIELD_MOVIE_ID,
        @Query(QUERY_PARAM_SEARCH) searchId: Int,
    ): Single<ReviewResponse>

    @GET("review")
    fun loadMovieReviewTyped(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_LIMIT) limit: Int = SEARCH_FIELD_LIMIT_REVIEW_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE_DEFAULT,
        @Query(QUERY_PARAM_FIELD) movieId: String = SEARCH_FIELD_MOVIE_ID,
        @Query(QUERY_PARAM_SEARCH) searchId: Int,
        @Query(QUERY_PARAM_FIELD) typeParam: String = TYPE_FIELD_DEFAULT,
        @Query(QUERY_PARAM_SEARCH) searchType: String,
        ): Single<ReviewResponse>

    @GET("image")
    fun loadMovieFrame(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_LIMIT) limit: Int = SEARCH_FIELD_LIMIT_FRAME_DEFAULT,
        @Query(QUERY_PARAM_FIELD) field: String = SEARCH_FIELD_MOVIE_ID,
        @Query(QUERY_PARAM_SEARCH) searchId: Int,
        ): Single<FrameResponse>
}