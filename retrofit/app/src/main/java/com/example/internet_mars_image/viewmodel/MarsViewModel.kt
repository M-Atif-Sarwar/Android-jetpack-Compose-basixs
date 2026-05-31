package com.example.internet_mars_image.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.internet_mars_image.data.MarsPhotoRepository
import com.example.internet_mars_image.data.NetworkMarsPhotoRepository

import com.example.internet_mars_image.network.MarsPhoto
import kotlinx.coroutines.launch
import okio.IOException
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.internet_mars_image.MarsPhotoApplication

sealed interface MarsUiState{
    data class Success(val photos: List<MarsPhoto>): MarsUiState
    object Loading : MarsUiState
    object  Error: MarsUiState
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotoRepository): ViewModel(){
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos(){
        viewModelScope.launch {
            /*
            getting response by call get photo
            method using retrofit sigleton object
             */
            marsUiState = try {
                val listResult=marsPhotosRepository.getMarsPhoto()

                    MarsUiState.Success(listResult)
               } catch (e: IOException) {
                    MarsUiState.Error
             }

        }
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotoApplication)
                val marsPhotosRepository = application.container.marsPhotoRepository
                MarsViewModel(marsPhotosRepository =  marsPhotosRepository)
            }
        }
    }
}