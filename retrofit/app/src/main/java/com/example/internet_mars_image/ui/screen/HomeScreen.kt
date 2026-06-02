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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.internet_mars_image.R
import com.example.internet_mars_image.network.MarsPhoto
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
fun ResultScreen(modifier: Modifier=Modifier,photo:List<MarsPhoto>){
    Column(modifier=modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier.padding(horizontal = 4.dp),

        ) {
            items(items=photo, key={photo ->photo.id}) { photo ->
               PhotoCard(
                   photo = photo,
                   modifier = modifier
                       .padding(4.dp)
                       .fillMaxWidth()
                       .aspectRatio(1.5f)

               )
            }
        }
    }
}


@Composable
fun PhotoCard(modifier:Modifier=Modifier,photo:MarsPhoto){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.description),
            error = painterResource(R.drawable.ic_connection_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()

        )
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


