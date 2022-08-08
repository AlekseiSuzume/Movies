package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Person
import com.suzume.movies.data.pojo.movieDetailResponse.Trailer
import com.suzume.movies.data.pojo.movieDetailResponse.Videos

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

//    @TypeConverter
//    fun personListToJson(personList: List<Person>): String {
//        return gson.toJson(personList, object : TypeToken<ArrayList<Person>>(){}.type)
//    }
//
//    @TypeConverter
//    fun personListFromJson(string: String): List<Person> {
//        return gson.fromJson(string, object : TypeToken<List<Person>>(){}.type)
//    }
}