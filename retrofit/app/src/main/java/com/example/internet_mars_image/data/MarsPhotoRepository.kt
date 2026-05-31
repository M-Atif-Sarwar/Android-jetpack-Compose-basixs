package com.example.internet_mars_image.data


import com.example.internet_mars_image.network.MarsApiService
import com.example.internet_mars_image.network.MarsPhoto

interface MarsPhotoRepository {
    suspend fun getMarsPhoto():List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
    private val marsApiService: MarsApiService
): MarsPhotoRepository{
    override suspend fun getMarsPhoto(): List<MarsPhoto> = marsApiService.getPhotos()

}