package com.scan.unplashapi.View_Model

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fahamin.hiltcorutingmvvmretrofit.Model.NetworkResult
import com.fahamin.hiltcorutingmvvmretrofit.Model.UnplaceModel.UnplashResponse
import com.fahamin.hiltcorutingmvvmretrofit.Repository.UnplashRepository
import com.fahamin.unplashapi_jetpack.UnsplashModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private var repository: UnplashRepository) :
    ViewModel() {

    var imageList = MutableLiveData<NetworkResult<Response<List<UnsplashModel>>>>()
    val errorMessage = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            repository.getImageList().collect() {
                imageList.postValue(it)
            }
        }

    }

    fun imageListPage(): LiveData<PagingData<UnsplashModel>> {
        return repository.getImagePage().cachedIn(viewModelScope)
    }
}