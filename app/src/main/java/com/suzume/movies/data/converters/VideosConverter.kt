package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Person
import com.suzume.movies.data.pojo.movieDetailResponse.Videos

class VideosConverter {

    private val gson = Gson()

    @TypeConverter
    fun videosListToJson(videosList: List<Videos>): String {
        return gson.toJson(videosList)
    }

    @TypeConverter
    fun videosListFromJson(json: String): List<Videos> {
        return gson.fromJson(json, Array<Videos>::class.java).toList()
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