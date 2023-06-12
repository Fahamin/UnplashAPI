package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.Links
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LinksTypeConverters {
    @TypeConverter
    fun storedStringToMyLinkss(data: String?): Links {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<Links?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myLinkssToStoredString(myLinkss: Links?): String {
        val gson = Gson()
        return gson.toJson(myLinkss)
    }
}