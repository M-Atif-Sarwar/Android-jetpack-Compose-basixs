package com.example.inventory.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemRepository
import com.example.inventory.data.ItemRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import java.text.NumberFormat
import javax.inject.Inject
import kotlin.Int

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

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
        suspend fun saveItem() {
           if(validateInput()){
               dataRepository.insertItem(itemUiState.itemDetails.toItem())
           }

        }


}