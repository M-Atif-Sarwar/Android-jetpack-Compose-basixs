package com.example.internet_mars_image.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.internet_mars_image.R
import com.example.internet_mars_image.viewmodel.MarsUiState

// error screen
@Composable
fun ErrorScreen(retry:()-> Unit,modifier: Modifier=Modifier){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = "",
        )

        Text(text = stringResource(R.string.loading_failed))
        Button(onClick = retry) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorScreenPreview(){
    ErrorScreen(retry={})
}


// loading Screen
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreenPreview(){
    LoadingScreen()
}

@Composable
fun ResultScreen(modifier: Modifier=Modifier,photo:String){
    Column(modifier=modifier) {
        Text(text = photo)
    }
}

// home Screen
@Composable
fun HomeScreen(
    modifier: Modifier=Modifier,
    retry: () -> Unit,
    marsUiState: MarsUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),

    ){
    when(marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success -> ResultScreen(
            modifier = modifier.fillMaxWidth(),
            marsUiState.photos
        )
        is MarsUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize(), retry={})
    }


}


