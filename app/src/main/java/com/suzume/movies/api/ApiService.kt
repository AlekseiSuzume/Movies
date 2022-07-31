package com.suzume.movies.api

import com.suzume.movies.pojo.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    private companion object {
        const val QUERY_PARAMS_TOKEN = "token"
        const val QUERY_PARAMS_FIELD = "field"
        const val QUERY_PARAMS_SEARCH = "search"
        const val QUERY_PARAMS_SORT_FIELD = "sortField"
        const val QUERY_PARAMS_SORT_TYPE = "sortType"
        const val QUERY_PARAMS_LIMIT = "limit"
        const val QUERY_PARAMS_PAGE = "page"

        const val TOKEN_DEFAULT = "H682YKN-SQC4F06-PYWC291-YAK7HZP"
        const val FIELD_DEFAULT = "rating.kp"
        const val SEARCH_DEFAULT = "5-10"
        const val SORT_FIELD_DEFAULT = "votes.kp"
        const val SORT_TYPE_DEFAULT = -1
        const val LIMIT_DEFAULT = 30
        const val PAGE_DEFAULT = 1
    }

    @GET("movie")
    fun loadMovies(
        @Query(QUERY_PARAMS_TOKEN) token: String = TOKEN_DEFAULT,
        @Query(QUERY_PARAMS_FIELD) field: String = FIELD_DEFAULT,
        @Query(QUERY_PARAMS_SEARCH) search: String = SEARCH_DEFAULT,
        @Query(QUERY_PARAMS_SORT_FIELD) sortField: String = SORT_FIELD_DEFAULT,
        @Query(QUERY_PARAMS_SORT_TYPE) sortType: Int = SORT_TYPE_DEFAULT,
        @Query(QUERY_PARAMS_LIMIT) limit: Int = LIMIT_DEFAULT,
        @Query(QUERY_PARAMS_PAGE) page: Int = PAGE_DEFAULT
    ): Single<MovieResponse>

    @GET("movie?token=H682YKN-SQC4F06-PYWC291-YAK7HZP&field=rating.kp&search=5-10&&sortField=votes.kp&sortType=-1&limit=50")
    fun loadMoviesTest(): Single<MovieResponse>
}