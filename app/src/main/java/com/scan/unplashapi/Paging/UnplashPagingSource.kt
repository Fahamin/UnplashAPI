package com.scan.unplashapi.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fahamin.hiltcorutingmvvmretrofit.Api.Api
import com.fahamin.hiltcorutingmvvmretrofit.Model.UnplaceModel.UnplashResponse
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.scan.unplashapi.Utility.Constance
import retrofit2.Response
import javax.inject.Inject

class UnplashPagingSource @Inject constructor(private var api: Api) :
    PagingSource<Int, UnsplashModel>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, UnsplashModel> {

        return try {
            val position = params.key ?: 1
            val response = api.getImageListWithPage(Constance.tokernID, 1)
            PagingSource.LoadResult.Page(
                data = response.body()!!.result,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )

        } catch (e: Exception) {
            return PagingSource.LoadResult.Error(e)
        }

    }
}