package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
              ArtSpaceApp()
        }
    }
}

data class ArtData(
    @DrawableRes val img: Int,
    val artistName: String,
    val artDescription: String,

)
@Composable
fun DisplayControllers(
    onNext:()-> Unit,
    onPrevious:()-> Unit,
    modifier: Modifier=Modifier){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier=modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onPrevious
        ) {
            Text("Previous")
        }

        Spacer(Modifier.width(24.dp))
        Button(
            onClick = onNext
        ) {
            Text("Next")
        }

    }
}

@Composable
fun ArtWorkDescriptor(artistName:String,description:String,modifier: Modifier=Modifier){
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
                text=artistName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            Text(
                text=description
            )
        }



    }
}

@Composable
fun ArtWorkWall(art:Int,modifier: Modifier=Modifier){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(art),
            contentDescription = "art",
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp)
        )
    }

}
@Composable
fun ArtSpaceApp(modifier: Modifier=Modifier){
    val listOfArt:List<ArtData> =listOf(
        ArtData(
            img=R.drawable.ic_launcher_background,
            artistName = "Atif Sarwar",
            artDescription = "this is  my art",
        ),
        ArtData(
            img=R.drawable.ic_launcher_foreground,
            artistName = "Zubair",
            artDescription = "zubair s painting",
        )


    )

    val lastIndex=listOfArt.size - 1
    var index by remember { mutableIntStateOf(0) }

    // current dataset
    val current = listOfArt[index]


    val onNextClick={
        index = if(index == lastIndex) 0 else index +1

    }

    val onPreviousClick={
        index = if (index == 0) 0 else index - 1
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier=modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .statusBarsPadding()
            .padding(24.dp)

    ) {
        ArtWorkWall(art=current.img)
        Spacer(Modifier.height(60.dp))
        ArtWorkDescriptor(
            artistName = current.artistName,
            description = current.artDescription,
        )
        Spacer(Modifier.height(50.dp))
        DisplayControllers(
            onNext = onNextClick,
            onPrevious = onPreviousClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}