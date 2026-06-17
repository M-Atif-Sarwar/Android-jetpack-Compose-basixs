package com.example.inventory.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.NavigationDestinationInterface
import com.example.inventory.ui.viewmodel.HomeViewModel
import com.example.inventory.ui.viewmodel.formatedPrice

object HomeDestination: NavigationDestinationInterface{
    override val route: String ="home"
    override val title: String="Inventory"
}

@Composable
fun HomeScreen(
    modifier:Modifier= Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToAddItem:()->Unit,
    navigateToItemUpdate:(Int)->Unit
){
    val homeState by homeViewModel.homeUIState.collectAsState()

    /*
    WindowInsets.safeDrawing represents the parts of the screen that
    might be covered up by system UI —

    .asPaddingValues() converts that raw inset information into a
    PaddingValues object —

    .calculateEndPadding(LocalLayoutDirection.current) then pulls out just one of those four numbers
     — specifically the "end" edge.
     */
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddItem,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_icon)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .safeDrawingPadding()
        ) {
        with(homeState) {
            if(itemList.isEmpty()){
                Text("Inventory is Empty click button below to Add")
            } else {
              ListItem(
                  modifier=Modifier,
                  item = itemList,
                  onItemClick=navigateToItemUpdate
              )
            }
        }

        }
    }

}

// list to display
@Composable
fun ListItem(
    modifier: Modifier=Modifier,
    item:List<Item>,
    onItemClick:(Int) -> Unit
){
    LazyColumn(modifier=modifier) {
        items(
            items = item,
            key = { item -> item.id }
        ) { item ->
            ItemCard(
                name = item.name,
                price = item.formatedPrice(),
                quantity = item.quantity.toString(),
                modifier = Modifier.clickable{onItemClick(item.id)}

            )
        }
    }
}

// single item card
@Composable
fun ItemCard(
    modifier: Modifier=Modifier,
    name:String,
    quantity:String,
    price:String
){
    Card(modifier=modifier){
        Column {
            Row{
                Text(text=name)
                Spacer(Modifier.weight(1f))
                Text(text = price)
            }

            Text(text="$quantity in stock")
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navigateToAddItem = {}, navigateToItemUpdate = {})
}