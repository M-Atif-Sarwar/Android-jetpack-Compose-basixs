package com.example.inventory.ui.screens

import android.view.RoundedCorner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventory.ui.viewmodel.ItemDetails
import com.example.inventory.ui.viewmodel.ItemEntryViewModel
import java.util.Currency
import java.util.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventory.ui.NavigationDestinationInterface

object EntryScreenDestination: NavigationDestinationInterface{
    override val route: String
        get() = "itemEntry"

    override val title: String
        get() = "Add Inventory"
}
@Composable
fun EntryScreen(
    modifier:Modifier=Modifier,
    entryViewModel: ItemEntryViewModel=hiltViewModel(),

){
    val itemState=entryViewModel.itemUiState.collectAsState().value
    Column(
        modifier=modifier.padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ){
       EntryScreenItem(
           item = itemState.itemDetails,
           onValuedChanged = entryViewModel::updateUIState,
           buttonLabel = "Add Item",
           onButtonClick = {entryViewModel.saveItem(onComplete = {})}
       )
    }
}

@Composable
fun EntryScreenItem(
    modifier: Modifier=Modifier,
    item: ItemDetails,
    onValuedChanged:(ItemDetails)->Unit,
    enabled: Boolean=true,
    buttonLabel:String,
    onButtonClick:() -> Unit
){
    val focusManager= LocalFocusManager.current
    Column(
        modifier=modifier.padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        OutlinedTextField(
            value = item.name,
            onValueChange = {newName->onValuedChanged(item.copy(name=newName))},
            label = {Text("Name")},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )

        )

        OutlinedTextField(
            value = item.price,
            onValueChange = {newPrice -> onValuedChanged(item.copy(price=newPrice))},
            label = {Text("Price")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            ),
            leadingIcon = {Text(Currency.getInstance(Locale.getDefault()).symbol)}
        )

        OutlinedTextField(
            value = item.quantity,
            onValueChange = { newQuantity -> onValuedChanged(item.copy(quantity = newQuantity))},
            label = {Text("Quantity")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
        )

            Button(
                onClick = onButtonClick,
            ) {
                Text(
                    text = buttonLabel
                )
            }



    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  EntryScreenPreview(){
    EntryScreenItem(
        enabled = false,
        onValuedChanged = {newText -> },
        item = ItemDetails(name = "test", quantity = "2", price = "36.0" ),
        onButtonClick ={} ,
        buttonLabel = "Add Item"

    )
}