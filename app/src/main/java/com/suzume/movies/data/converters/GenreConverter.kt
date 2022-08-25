package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Genre

class GenreConverter {

    private val gson = Gson()

    @TypeConverter
    fun genreListToJson(genreList: List<Genre>): String {
        return gson.toJson(genreList)
    }

    @TypeConverter
    fun genreListFromJson(json: String): List<Genre> {
        return gson.fromJson(json, Array<Genre>::class.java).toList()
    }

}