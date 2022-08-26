package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Person

class PersonConverter {

    private val gson = Gson()

    @TypeConverter
    fun personListToJson(personList: List<Person>): String {
        return gson.toJson(personList)
    }

    @TypeConverter
    fun personListFromJson(json: String): List<Person> {
        return gson.fromJson(json, Array<Person>::class.java).toList()
    }

}