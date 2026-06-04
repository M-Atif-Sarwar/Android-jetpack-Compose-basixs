package com.example.apipractice.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.apipractice.ui.viewModel.AmphibionViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun AppScreen(){

    val amphibiansViewModel: AmphibionViewModel = viewModel(
        factory= AmphibionViewModel.Factory
    )

    Scaffold(
        topBar = {
            Text("Amphibians")
        }
    ) {contentPadding ->
         HomeScreen(
             uiState=amphibiansViewModel.uiState,
             modifier= Modifier.padding(contentPadding)
                 .fillMaxWidth()
         )

    }
}