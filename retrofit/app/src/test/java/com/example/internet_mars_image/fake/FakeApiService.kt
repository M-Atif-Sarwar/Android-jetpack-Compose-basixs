package com.example.internet_mars_image.fake

import com.example.internet_mars_image.network.MarsApiService
import com.example.internet_mars_image.network.MarsPhoto

class FakeApiService: MarsApiService{
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }



}