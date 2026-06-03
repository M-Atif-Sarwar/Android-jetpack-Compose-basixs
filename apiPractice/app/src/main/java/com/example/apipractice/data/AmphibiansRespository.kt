package com.example.apipractice.data

import com.example.apipractice.network.ApiData
import com.example.apipractice.network.ApiInterface

interface RepositoryInterface {
    suspend fun getPhotoFromApi(): List<ApiData>
}

class AmphibiansRepository(
    // receiving  api instance from dependency Container
    private val api: ApiInterface
): RepositoryInterface{
    override suspend fun getPhotoFromApi(): List<ApiData> {
        return api.getImages()
    }
}