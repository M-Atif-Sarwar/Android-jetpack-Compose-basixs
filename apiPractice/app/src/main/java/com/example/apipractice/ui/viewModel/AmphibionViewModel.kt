package com.example.apipractice.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.apipractice.DefaultDependencyContainer
import com.example.apipractice.data.RepositoryInterface
import com.example.apipractice.network.ApiData
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okio.IOException

sealed interface UIState{
    data class Success(val data:List<ApiData>): UIState
    object Loading: UIState
    object Error: UIState
}

class AmphibionViewModel(
    private val repository: RepositoryInterface
) : ViewModel()
 {
       var uiState: UIState by mutableStateOf(UIState.Loading)
           private set

      /*
      creating view model via factory to get access to
      Dependency container
       */

     companion object{
         val Factory:ViewModelProvider.Factory = viewModelFactory {
           initializer {
               val application=(this[APPLICATION_KEY] as DefaultDependencyContainer)
               val dataRepository = application.container.repository
               AmphibionViewModel(dataRepository)
           }
         }
     }


     fun getData(){
         viewModelScope.launch {
             uiState=try {
               val result=repository.getPhotoFromApi()
                 Log.d("Connected TO Api ","$result")
                 UIState.Success(result)
             }
             catch (e: IOException){
                 Log.d("API Error",e.toString())
                 UIState.Error
             }
         }
     }

     init{
         getData()
     }
}