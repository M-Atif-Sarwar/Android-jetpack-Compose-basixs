package com.example.internet_mars_image.fake

import com.example.internet_mars_image.data.MarsPhotoRepository
import com.example.internet_mars_image.network.MarsPhoto

class FakeNetworkMarsPhotoRepository: MarsPhotoRepository{
    override suspend fun getMarsPhoto(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}