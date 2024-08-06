package com.example.chatwiseassignment.ui.ProductList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwiseassignment.model.ApiResponse
import com.example.chatwiseassignment.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(val repository: ProductRepository): ViewModel() {

    val productsLiveData: LiveData<ApiResponse>
        get() = repository.products

    fun getDataFromApi() {
        viewModelScope.launch {
            repository.getProducts()
        }
    }
}