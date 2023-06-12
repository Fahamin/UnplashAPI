package com.fahamin.unplashapi_jetpack.Model.typeConverter

import androidx.room.TypeConverter
import com.fahamin.unplashapi_jetpack.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserTypeConverters {
    @TypeConverter
    fun storedStringToMyUsers(data: String?): User {
        val gson = Gson()
        if (data == null) {
        }
        val listType = object : TypeToken<User?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myUsersToStoredString(myUsers: User?): String {
        val gson = Gson()
        return gson.toJson(myUsers)
    }
}