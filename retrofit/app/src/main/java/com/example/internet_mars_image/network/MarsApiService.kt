package com.example.internet_mars_image.network

import retrofit2.http.GET


// creating api interface
interface MarsApiService{
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}

