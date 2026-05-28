package com.example.lanchtraywithnavigation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lanchtraywithnavigation.data.DataSource
import com.example.lanchtraywithnavigation.ui.screen.OptionScreen
import com.example.lanchtraywithnavigation.ui.screen.StartScreen
import com.example.lanchtraywithnavigation.viewmodel.AppViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

enum class AppRoutes {
    Start,
    EntreeMenu,
    SideDishMenu,
    AccompanimentMenu,
    Checkout
}
@OptIn(
    ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(){
    val navController = rememberNavController()

    val appModel: AppViewModel= viewModel()
    val appStates by appModel.appSate.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lunch Tray") })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppRoutes.Start.name) {
                StartScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNextButton = {
                        navController.navigate((AppRoutes.EntreeMenu.name))
                    }
                )


            }

            composable(route = AppRoutes.EntreeMenu.name) {
                OptionScreen(
                    contentData = DataSource.entreeList,
                    onNextButton = {
                        navController.navigate((AppRoutes.SideDishMenu.name))
                    },
                    onCancelButton = {
                        appModel.resetOrder()
                        navController.popBackStack(AppRoutes.Start.name, inclusive = false)
                    },
                    onValueChange = { title, price -> appModel.addDishes(title,price) }
                )

            }

            composable(route = AppRoutes.SideDishMenu.name) {
                OptionScreen(
                    contentData = DataSource.sideDishList,
                    onNextButton = {
                        navController.navigate((AppRoutes.AccompanimentMenu.name))
                    },
                    onCancelButton = {
                        appModel.resetOrder()
                        navController.popBackStack(AppRoutes.Start.name, inclusive = false)
                    },
                    onValueChange = { title, price -> appModel.addDishes(title,price)}
                )

            }

            composable(route = AppRoutes.AccompanimentMenu.name) {
                OptionScreen(
                    contentData = DataSource.accomplishmentList,
                    onNextButton = {
                        navController.navigate((AppRoutes.Checkout.name))
                    },
                    onCancelButton = {
                        appModel.resetOrder()
                        navController.popBackStack(AppRoutes.Start.name, inclusive = false)
                    },
                    onValueChange = { title, price -> appModel.addDishes(title,price) }
                )

            }

            composable(route = AppRoutes.Checkout.name) {
                OptionScreen(
                    contentData = DataSource.accomplishmentList,
                    onNextButton = {
//                        navController.navigate((AppRoutes.Checkout))
                    },
                    onCancelButton = {
                        navController.popBackStack(AppRoutes.Start.name, inclusive = false)
                    },
                    onValueChange = { title, price -> }
                )

            }


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppScreenPreview(){
    AppScreen()
}