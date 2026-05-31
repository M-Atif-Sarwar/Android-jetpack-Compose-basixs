package com.example.internet_mars_image

import android.app.Application
import com.example.internet_mars_image.data.AppContainer
import com.example.internet_mars_image.data.DefaultAppContainer

class MarsPhotoApplication: Application (){
        lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= DefaultAppContainer()
    }
}