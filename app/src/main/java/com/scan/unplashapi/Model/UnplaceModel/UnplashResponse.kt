package com.fahamin.hiltcorutingmvvmretrofit.Model.UnplaceModel

import com.fahamin.unplashapi_jetpack.UnsplashModel


data class UnplashResponse(val page: Int,val result: List<UnsplashModel>)