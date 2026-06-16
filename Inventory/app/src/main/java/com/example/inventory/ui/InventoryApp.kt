package com.example.inventory.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.inventory.ui.screens.EntryScreen
import com.example.inventory.ui.viewmodel.ItemDetails

@Composable
fun InventoryApp(){
    val newText="Atif"
    Scaffold() {innerPadding ->
        Column(
            modifier=Modifier.padding(innerPadding)
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            EntryScreen()
        }
    }
}