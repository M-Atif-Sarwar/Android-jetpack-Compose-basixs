package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
              ArtSpaceApp()
        }
    }
}

@Composable
fun DisplayControllers(modifier: Modifier=Modifier){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier=modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {}
        ) {
            Text("Previous")
        }

        Spacer(Modifier.width(24.dp))
        Button(
            onClick = {}
        ) {
            Text("Next")
        }

    }
}

@Composable
fun ArtWorkDescriptor(modifier: Modifier=Modifier){
    Card(
        modifier=modifier
            .fillMaxWidth()

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            Text(
                text="Art name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            Text(
                text="Artist name"
            )
        }



    }
}

@Composable
fun ArtWorkWall(modifier: Modifier=Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "art",
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp)
        )
    }

}
@Composable
fun ArtSpaceApp(modifier: Modifier=Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier=modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .statusBarsPadding()
            .padding(24.dp)

    ) {
        ArtWorkWall()
        Spacer(Modifier.height(60.dp))
        ArtWorkDescriptor()
        Spacer(Modifier.height(50.dp))
        DisplayControllers()
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        DisplayControllers()
    }
}