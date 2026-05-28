package com.example.lanchtraywithnavigation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartScreen(
    modifier: Modifier=Modifier,
    onNextButton:()-> Unit
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
           onClick = onNextButton,
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 70.dp),
        ) {
            Text(
                text = "Start Order",
                textAlign = TextAlign.Center,
                fontSize = 24.sp

            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview(){
     StartScreen(
         modifier = Modifier.fillMaxSize(),
         onNextButton = {}
     )
}