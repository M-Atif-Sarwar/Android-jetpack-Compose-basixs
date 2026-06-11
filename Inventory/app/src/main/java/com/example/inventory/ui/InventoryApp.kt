package com.example.inventory.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InventoryApp(){
    Scaffold() {innerPadding ->
        Column(
            modifier=Modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
            Text("Welcome to inventory")
        }
    }
}