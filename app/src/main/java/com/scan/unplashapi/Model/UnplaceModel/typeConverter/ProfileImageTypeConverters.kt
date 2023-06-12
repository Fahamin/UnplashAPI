package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.ProfileImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileImageTypeConverters {
    @TypeConverter
    fun storedStringToMyProfileImages(data: String?): ProfileImage {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<ProfileImage?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myProfileImagesToStoredString(myProfileImages: ProfileImage?): String {
        val gson = Gson()
        return gson.toJson(myProfileImages)
    }
}