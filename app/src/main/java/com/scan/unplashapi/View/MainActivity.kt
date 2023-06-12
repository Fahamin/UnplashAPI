package com.scan.unplashapi.View

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahamin.hiltcorutingmvvmretrofit.Model.NetworkResult
import com.fahamin.unplashapi_jetpack.UnsplashModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.scan.unplashapi.Adapter.UnsplashModelPagerAdapter
import com.scan.unplashapi.View_Model.MainActivityViewModel
import com.scan.unplashapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.reflect.Type


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel


    var adapter = UnsplashModelPagerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        simpleWayGetData();

        getDataPaging3Way();
    }

    private fun getDataPaging3Way() {



        mainActivityViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            Log.e("json", "" + it.toString())

        }

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                binding.progressDialog.isVisible = true
            else {
                binding.progressDialog.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                    Log.e("json", "" + it.toString())

                }

            }
        }

        lifecycleScope.launch {
            mainActivityViewModel.imageListPage().observe(this@MainActivity) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                    Log.e("json", "" + it.toString())
                    binding.rvUnPlash.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvUnPlash.adapter = adapter
                }
            }
        }

    }






private fun simpleWayGetData() {

    mainActivityViewModel.imageList.observe(this) {
        var progressDialog = ProgressDialog(this)

        when (it) {
            is NetworkResult.Loading -> {
                progressDialog.setMessage("Loading")
                progressDialog.show()
            }

            is NetworkResult.Success -> {
                progressDialog.dismiss()

                Log.e("json", "" + it.data.body().toString())
            }

            is NetworkResult.Failure -> {
                progressDialog.dismiss()

                Log.e("json", "failed data ")
            }
        }
    }
}}
