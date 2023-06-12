package com.fahamin.hiltcorutingmvvmretrofit.Api

import com.fahamin.hiltcorutingmvvmretrofit.Model.UnplaceModel.UnplashResponse
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.fahamin.unplashapi_jetpack.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("photos")
    suspend fun getImageList(@Query("client_id") cid: String?): Response<List<UnsplashModel>>

    @GET("photos")
    suspend fun getImageListWithPage(
        @Query("client_id") cid: String?,
        @Query("page") page: Int
    ): Response<UnplashResponse>


    //can search section
    @GET("search/photos")
    suspend fun searchImage(
        @Query("client_id") cid: String?,
        @Query("query") name: String?
    ): Response<List<UnsplashModel>>
}