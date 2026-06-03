package com.example.apipractice.network

import retrofit2.http.GET

interface ApiInterface {
    @GET("amphibians")
    suspend fun getImages():List<ApiData>
}