package com.example.chatwiseassignment.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwiseassignment.model.ApiResponse
import com.example.chatwiseassignment.network.ApiService
import com.example.chatwiseassignment.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel: ViewModel() {

    // response
    private var _response = MutableLiveData<ApiResponse>()
    val response: LiveData<ApiResponse> get() = _response

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    fun getDataFromApi() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val item = apiService.getProductData()
                    _response.postValue(item)
                    Log.d("TAG", "Success getting data: $item")
                }

            }catch (e: Exception) {
                Log.d("TAG", "API Error: $e")
            }
        }
    }
}