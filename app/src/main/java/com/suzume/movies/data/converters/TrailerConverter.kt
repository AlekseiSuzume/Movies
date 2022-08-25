package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer

class TrailerConverter {

    private val gson = Gson()

    @TypeConverter
    fun trailerListToJson(trailerList: List<Trailer>): String {
        return gson.toJson(trailerList)
    }

    @TypeConverter
    fun trailerListFromJson(json: String): List<Trailer> {
        return gson.fromJson(json, Array<Trailer>::class.java).toList()
    }

}