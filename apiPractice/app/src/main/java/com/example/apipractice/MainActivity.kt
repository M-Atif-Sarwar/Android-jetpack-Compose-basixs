package com.example.apipractice
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apipractice.ui.screens.AppScreen

import com.example.apipractice.ui.theme.ApiPraacticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiPraacticeTheme {
                AppScreen()
                }
            }
        }
    }



