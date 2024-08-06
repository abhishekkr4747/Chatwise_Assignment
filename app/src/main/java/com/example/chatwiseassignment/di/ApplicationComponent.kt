package com.example.chatwiseassignment.di

import com.example.chatwiseassignment.ui.ProductList.ProductListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(productListActivity: ProductListActivity)
}