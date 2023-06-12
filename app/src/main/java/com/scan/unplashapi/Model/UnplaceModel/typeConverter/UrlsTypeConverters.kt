package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.Urls
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UrlsTypeConverters {
    @TypeConverter
    fun storedStringToMyUrlss(data: String?): Urls {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<Urls?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myUrlssToStoredString(myUrlss: Urls?): String {
        val gson = Gson()
        return gson.toJson(myUrlss)
    }
}