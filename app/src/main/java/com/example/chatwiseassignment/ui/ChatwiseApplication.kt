package com.example.chatwiseassignment.ui

import android.app.Application
import com.example.chatwiseassignment.di.ApplicationComponent
import com.example.chatwiseassignment.di.DaggerApplicationComponent

class ChatwiseApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}