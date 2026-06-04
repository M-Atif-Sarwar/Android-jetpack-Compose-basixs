package com.example.apipractice.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.apipractice.ui.viewModel.UIState

@Composable
fun HomeScreen(
    modifier:Modifier= Modifier,
    uiState: UIState,


) {
    Column(modifier=modifier) {
        when (uiState) {
            UIState.Loading -> Text("Loading...")
            UIState.Error -> Text("Error")
            is UIState.Success -> Text("Loaded ${uiState.data.size} items")
        }
    }
}