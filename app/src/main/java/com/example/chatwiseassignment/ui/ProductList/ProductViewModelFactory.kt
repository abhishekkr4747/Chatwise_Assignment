package com.example.chatwiseassignment.ui.ProductList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatwiseassignment.repository.ProductRepository
import javax.inject.Inject

class ProductViewModelFactory @Inject constructor(private val repository: ProductRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repository) as T
    }
}