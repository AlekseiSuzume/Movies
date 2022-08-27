package com.suzume.movies.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.database.models.PersonDbModel

class PersonConverter {

    private val gson = Gson()

    @TypeConverter
    fun personListToJson(personDbModels: List<PersonDbModel>): String {
        return gson.toJson(personDbModels)
    }

    @TypeConverter
    fun personListFromJson(json: String): List<PersonDbModel> {
        return gson.fromJson(json, Array<PersonDbModel>::class.java).toList()
    }
}