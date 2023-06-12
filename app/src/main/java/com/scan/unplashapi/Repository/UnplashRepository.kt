package com.fahamin.hiltcorutingmvvmretrofit.Repository

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fahamin.hiltcorutingmvvmretrofit.Api.Api
import com.fahamin.hiltcorutingmvvmretrofit.Model.NetworkResult
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.scan.unplashapi.Paging.UnplashPagingSource
import com.scan.unplashapi.Utility.Constance
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnplashRepository @Inject constructor(private var api: Api) {

    suspend fun getImageList() = flow {
        emit(NetworkResult.Loading(true))
        var result = api.getImageList(Constance.tokernID)
        emit(NetworkResult.Success(result))
    }.catch { e ->
        emit(NetworkResult.Failure("" + e.message))
    }


    fun getImagePage(): LiveData<PagingData<UnsplashModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                UnplashPagingSource(api)
            }, initialKey = 1
        ).liveData
    }

}