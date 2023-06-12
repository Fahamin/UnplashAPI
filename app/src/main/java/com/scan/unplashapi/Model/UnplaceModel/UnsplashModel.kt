package com.fahamin.unplashapi_jetpack

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fahamin.unplashapi_jetpack.Model.typeConverter.LinksTypeConverters
import com.fahamin.unplashapi_jetpack.Model.typeConverter.UrlsTypeConverters
import com.fahamin.unplashapi_jetpack.Model.typeConverter.UserTypeConverters
import com.google.gson.annotations.SerializedName


@Entity
data class UnsplashModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("promoted_at") var promotedAt: String? = null,
    @SerializedName("width") var width: Int? = null,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("height") var height: Int? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("blur_hash") var blurHash: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("alt_description") var altDescription: String? = null,
    @TypeConverters(UrlsTypeConverters::class)
    @SerializedName("urls") var urls: Urls? = Urls(),
    @TypeConverters(LinksTypeConverters::class)
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @TypeConverters(UserTypeConverters::class)
    @SerializedName("user") var user: User? = User()

)