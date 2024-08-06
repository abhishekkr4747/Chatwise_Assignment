package com.example.chatwiseassignment.network

import com.example.chatwiseassignment.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProductData(): Response<ApiResponse>
}