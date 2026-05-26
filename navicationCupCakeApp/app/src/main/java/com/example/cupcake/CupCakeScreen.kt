package com.example.cupcake

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.components.CupCakeViewModel
import com.example.cupcake.ui.components.OrderSummaryScreen
import com.example.cupcake.ui.components.SelectOptionScreen
import com.example.cupcake.ui.components.StartOrderScreen
import com.example.cupcake.ui.components.quantityOptions

enum class CupCakeScreen(){
    Start,
    Flavor,
    Pickup,
    Summary
}

@Composable
fun CupCakeApp(){
    val navController=rememberNavController()
    val CupCakeModel: CupCakeViewModel= viewModel()
    val cupcakestate by CupCakeModel.cupcakesate.collectAsState()

    Scaffold() {contentPadding ->
        NavHost(
          navController = navController,
          startDestination = CupCakeScreen.Start.name,
          modifier=Modifier.padding(contentPadding)
        ){
            composable(route = CupCakeScreen.Start.name) {
                StartOrderScreen(
                    quantityOption= DataSource.quantityOptions,
                    onNextButtonClick = {quantity ->
                      CupCakeModel.updateQuantity(quantity)
                        navController.navigate(CupCakeScreen.Flavor.name)
                    },
                    modifier= Modifier.fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)

                )

                )
            } // end of compose Route

            composable(route =CupCakeScreen.Flavor.name) {
                val context= LocalContext.current
                SelectOptionScreen(
                    subtotal = cupcakestate.price,
                    // context.resources.getString(id) convert id back to actual string
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = {item -> CupCakeModel.updateFlavor(item)},
                    onNextButtonClicked = { navController.navigate(CupCakeScreen.Pickup.name) },
                    modifier = Modifier.fillMaxHeight()

                )
            }// edn of compose route

            composable(route = CupCakeScreen.Pickup.name) {
                SelectOptionScreen(
                    subtotal = cupcakestate.price,
                    onNextButtonClicked = { navController.navigate(CupCakeScreen.Summary.name) },
                    options = cupcakestate.pickupOptions,
                    onSelectionChanged = { date -> CupCakeModel.updatePickUpDate(date) },
                    modifier = Modifier.fillMaxHeight()
                )
            }// end compoable route

            composable(route = CupCakeScreen.Summary.name) {
                OrderSummaryScreen(
                    orderUiState = cupcakestate,
                    onCancelButtonClicked = {},
                    onSendButtonClicked = { subject: String, summary: String ->

                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }// end of compose
        }
    }
}