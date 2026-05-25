package com.example.cupcake

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.components.CupCakeViewModel
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
                        .padding(R.dimen.padding_medium)

                )

            }
        }
    }
}