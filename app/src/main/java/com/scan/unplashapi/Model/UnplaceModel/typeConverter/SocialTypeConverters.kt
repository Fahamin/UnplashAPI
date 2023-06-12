package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.Social
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SocialTypeConverters {
    @TypeConverter
    fun storedStringToMySocials(data: String?): Social {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<Social?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun mySocialsToStoredString(mySocials: Social?): String {
        val gson = Gson()
        return gson.toJson(mySocials)
    }
}