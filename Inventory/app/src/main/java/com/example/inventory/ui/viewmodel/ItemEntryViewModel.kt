package com.example.inventory.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemRepository
import com.example.inventory.data.ItemRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import javax.inject.Inject
import kotlin.Int
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)


/*
extension function to convert Item details to Item Database
will convert when sending to datbase
 */
fun ItemDetails.toItem(): Item =Item(
    id = id,
    name  = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0,
)


/*
extension function converting back Item database to
ItemDetails on receiving from database
 */

fun Item.toItemDetails(): ItemDetails= ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)

/*
convert item price to formated string
 */

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/*
extension function to convert Item to ItemUiState
 */

fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

@HiltViewModel
class ItemEntryViewModel @Inject constructor(
    private val dataRepository: ItemRepositoryInterface
): ViewModel(){

    private var _itemUiState = MutableStateFlow(ItemUiState())
    val itemUiState=_itemUiState.asStateFlow()

    private fun validateInput(uiState: ItemDetails = _itemUiState.value.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
        fun saveItem(onComplete:()->Unit = {}) {
            viewModelScope.launch {
            if (validateInput()) {
                dataRepository.insertItem(_itemUiState.value.itemDetails.toItem())
                _itemUiState.value = ItemUiState()
                onComplete()
            }

        }

        }

    fun updateUIState(itemDetails: ItemDetails){
          _itemUiState.update {currentState ->
              currentState.copy(
                  itemDetails=itemDetails,
                  isEntryValid = validateInput(itemDetails)
              )
          }
    }


}