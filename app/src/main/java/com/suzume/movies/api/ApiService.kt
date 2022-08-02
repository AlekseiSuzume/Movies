package com.suzume.movies.api

import com.suzume.movies.pojo.movieDetailResponse.MovieDetailResponse
import com.suzume.movies.pojo.movieShortResponse.MovieResponse
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
        const val FIELD_DEFAULT = "rating.kp"
        const val SEARCH_DEFAULT = "5-10"
        const val SORT_FIELD_DEFAULT = "votes.kp"
        const val SORT_TYPE_DEFAULT = -1
        const val LIMIT_DEFAULT = 30
        const val PAGE_DEFAULT = 1
        const val ID_PARAM_DETAIL_DEFAULT = "id"
    }

    @GET("movie")
    fun loadMovies(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_FIELD) field: String = FIELD_DEFAULT,
        @Query(QUERY_PARAM_SEARCH) search: String = SEARCH_DEFAULT,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = SORT_FIELD_DEFAULT,
        @Query(QUERY_PARAM_SORT_TYPE) sortType: Int = SORT_TYPE_DEFAULT,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT_DEFAULT,
        @Query(QUERY_PARAM_PAGE) page: Int = PAGE_DEFAULT
    ): Single<MovieResponse>


    @GET("movie")
    fun loadMovieDetail(
        @Query(QUERY_PARAM_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAM_FIELD) field: String = ID_PARAM_DETAIL_DEFAULT,
        @Query(QUERY_PARAM_SEARCH) searchId: Int,
        ): Single<MovieDetailResponse>
}