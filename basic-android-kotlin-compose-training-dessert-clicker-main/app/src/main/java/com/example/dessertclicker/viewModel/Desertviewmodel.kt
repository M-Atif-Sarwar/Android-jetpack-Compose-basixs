package com.example.dessertclicker.viewModel

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DesertviewModel: ViewModel(){
   private var _desertState= MutableStateFlow(DesertStates())
    val desertState= _desertState.asStateFlow()

    //getting data set
   private val _dataset:List<Dessert> = dessertList

    // getting value for stateflow  as current values
    val currentDessertPrice: Int
        get() = _dataset[_desertState.value.desertIndex].price

    val currentDessertImageId: Int
        get() = _dataset[_desertState.value.desertIndex].imageId



    //helper function to determine which to show
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()

        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }

        return dessertToShow
    }

    fun DesertClicked(){
      _desertState.update { currentState ->
          val newSold = currentState.dessertsSold + 1
          val newDessert = determineDessertToShow(_dataset, newSold)

          val newIndex = _dataset.indexOf(newDessert)

          currentState.copy(
              revenue = currentState.revenue + _dataset[currentState.desertIndex].price,
              dessertsSold = newSold,
              desertIndex = newIndex
          )


      }

    }
}