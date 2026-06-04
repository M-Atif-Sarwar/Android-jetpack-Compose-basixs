package com.example.apipractice.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apipractice.network.ApiData
import com.example.apipractice.ui.viewModel.UIState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(
    modifier:Modifier= Modifier,
    uiState: UIState,


) {

    Column(modifier=modifier) {
        when (uiState) {
            UIState.Loading -> LoadingScreen()
            UIState.Error -> ErrorScreen(error= "Fail to load")
            is UIState.Success -> ResultScreen(
                data=uiState.data,
                modifier = Modifier.padding(10.dp)
                )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier=Modifier){
    Column(
        modifier=modifier.safeDrawingPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier=Modifier.fillMaxSize()
        ){
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier=Modifier ,error: String){
    Column(
        modifier=modifier.safeDrawingPadding()
            .fillMaxSize(),
        verticalArrangement =Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(error)
    }
}



@Composable
fun ResultScreen(
    modifier: Modifier=Modifier,
    data:List<ApiData>
){
    LazyColumn(
        modifier=modifier,
        contentPadding = PaddingValues(vertical = 20.dp)
        ){
      items(data){ item ->
       ItemContainer(
           content=item,
           modifier = Modifier.padding(20.dp)
       )

      }
    }
}

@Composable
fun ItemContainer(
    modifier: Modifier=Modifier,
    content: ApiData
    ){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    )

        {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 20.dp)

        ) {

            Text(
                text = content.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(20.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(content.imgSrc)
                    .build(),
                contentDescription = content.type,
                contentScale = ContentScale.Crop,
                modifier=Modifier.clip(RoundedCornerShape(10.dp))

            )

            Spacer(Modifier.height(20.dp))


            Text(
                text = content.description,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify

            )

        }



    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreenPreview(){
    LoadingScreen()
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview(){
    ErrorScreen(error="fail to load")
}
