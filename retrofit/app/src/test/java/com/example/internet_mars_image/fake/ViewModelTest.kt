package com.example.internet_mars_image.fake

import com.example.internet_mars_image.rules.TestDispatcherRules
import com.example.internet_mars_image.viewmodel.MarsUiState
import com.example.internet_mars_image.viewmodel.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import retrofit2.http.GET

class MarsViewModelTest{

    @get:Rule
    val testdispatcher = TestDispatcherRules()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest{
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotoRepository()

            )

            assertEquals(
                MarsUiState.Success(FakeDataSource.photosList),
                marsViewModel.marsUiState
                )


        }
}