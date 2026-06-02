package com.example.apipractice

import android.app.Application
import com.example.apipractice.di.DependencyContainer

class DefaultDependencyContainer: Application(){
    lateinit var container:DependencyContainer
    override fun onCreate() {
        super.onCreate()
       container= DependencyContainer()
    }
}