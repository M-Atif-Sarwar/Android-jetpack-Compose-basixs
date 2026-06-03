package com.example.apipractice.di

import com.example.apipractice.data.AmphibiansRepository
import com.example.apipractice.data.RepositoryInterface
import com.example.apipractice.network.ApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit



interface ContainerInterface{
  val repository: RepositoryInterface
}

class DependencyContainer: ContainerInterface{
     // creating a base urs
    private val BASE_URL="https://android-kotlin-fun-mars-server.appspot.com/"

    // creating retrofit
    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()


    //creating singleton retrofit object
    private val retrofitService:ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

    /* passing instance of api to DataRepository
      also singleton passing
     */

    override val repository: RepositoryInterface by lazy {
        AmphibiansRepository(api = retrofitService)
    }

}