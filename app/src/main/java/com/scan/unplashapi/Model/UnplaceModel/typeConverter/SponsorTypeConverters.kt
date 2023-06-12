package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.Sponsor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SponsorTypeConverters {

    @TypeConverter
    fun storedStringToMySponsors(data: String?): Sponsor {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<Sponsor?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun mySponsorsToStoredString(mySponsors: Sponsor?): String {
        val gson = Gson()
        return gson.toJson(mySponsors)
    }

}