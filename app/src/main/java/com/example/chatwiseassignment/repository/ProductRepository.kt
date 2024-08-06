package com.example.chatwiseassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatwiseassignment.model.ApiResponse
import com.example.chatwiseassignment.network.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {

    private val _products = MutableLiveData<ApiResponse>()
    val products: LiveData<ApiResponse>
        get() = _products

    suspend fun getProducts() {
        val result = apiService.getProductData()
        if (result.isSuccessful) {
            _products.postValue(result.body())
        }
    }
}