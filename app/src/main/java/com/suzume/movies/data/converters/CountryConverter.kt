package com.suzume.movies.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.suzume.movies.data.pojo.movieDetailResponse.Country

class CountryConverter {

    private val gson = Gson()

    @TypeConverter
    fun countryListToJson(countryList: List<Country>): String {
        return gson.toJson(countryList)
    }

    @TypeConverter
    fun countryListFromJson(json: String): List<Country> {
        return gson.fromJson(json, Array<Country>::class.java).toList()
    }

}