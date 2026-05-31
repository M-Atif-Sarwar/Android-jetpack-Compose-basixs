package com.example.internet_mars_image.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internet_mars_image.network.MarsApi
import com.example.internet_mars_image.network.MarsPhoto
import kotlinx.coroutines.launch
import okio.IOException

sealed interface MarsUiState{
    data class Success(val photos: List<MarsPhoto>): MarsUiState
    object Loading : MarsUiState
    object  Error: MarsUiState
}

class MarsViewModel: ViewModel(){
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
                val listResult: List<MarsPhoto> = MarsApi.retrofitService.getPhotos()
                    MarsUiState.Success(listResult)
               } catch (e: IOException) {
                    MarsUiState.Error
             }

        }
    }
}