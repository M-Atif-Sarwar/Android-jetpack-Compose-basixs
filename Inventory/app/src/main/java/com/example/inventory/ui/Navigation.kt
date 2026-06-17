package com.example.inventory.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventory.ui.screens.EntryScreenDestination
import com.example.inventory.ui.screens.HomeDestination
import com.example.inventory.ui.screens.HomeScreen


interface NavigationDestinationInterface{
    val route: String
    val title: String
}

@Composable
fun InventoryNavigation(
    navController: NavHostController
){
    NavHost(
        navController=navController,
        startDestination = HomeDestination.route
    ){
        composable(route= HomeDestination.route){
            HomeScreen(
                navigateToAddItem={navController.navigate(EntryScreenDestination.route)}
            )
        }
    }
}