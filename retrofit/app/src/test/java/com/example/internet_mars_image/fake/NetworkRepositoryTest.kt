package com.example.internet_mars_image.fake

import com.example.internet_mars_image.data.NetworkMarsPhotoRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class NetworkRepositoryTest{
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest{
        val repository= NetworkMarsPhotoRepository(
            marsApiService = FakeApiService()
        )
        assertEquals(FakeDataSource.photosList,repository.getMarsPhoto())
    }
}