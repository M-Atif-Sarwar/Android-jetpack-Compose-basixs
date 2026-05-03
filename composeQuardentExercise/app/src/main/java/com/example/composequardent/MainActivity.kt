package com.example.composequardent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequardent.ui.theme.ComposeQuardentTheme


val itemsData=listOf(
    ContentData(title="Text composable",
        description = "Displays text and follows the recommended Material Design guidelines.",
        color= Color(0xFFEADDFF)
    ),
    ContentData(
        title="Image Composable",
        description = "Creates a composable that lays out and draws a given Painter class object",
        color = Color(0xFFD0BCFF)
    ),
    ContentData(
        title="Row composable",
        description = "A layout composable that places its children in a horizontal sequence.",
        color = Color(0xFFB69DF8)
    ),
    ContentData(
        title="Column composable",
        description = "A layout composable that places its children in a vertical sequence.",
        color = Color(0xFFF6EDFF)
    ),
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuardentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContainer(
                        itemsData,
                        modifier=Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContentSection(title:String,description:String,modifier:Modifier=Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier=modifier
    ) {
        Text(
            text=title,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(bottom = 16.dp)
        )
        Text(
            text=description,
            fontWeight = FontWeight.Bold,
            textAlign=TextAlign.Justify,

        )
    }
}

@Composable
fun MainContainer(content:List<ContentData>, modifier:Modifier=Modifier){
    //convert list into pair
    val pairedItem=content.chunked(2)
    Column(
        modifier= Modifier
            .fillMaxSize()

    ) {

         pairedItem.forEach { item ->
             /* each pair make a row
             then inside each pair there are two
             item that create two content in each row
              */
        Row(
            /* weight inside row takes height of
            parent column
             */
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier= Modifier
                .weight(1f)
                .fillMaxWidth()



        ){
            item.forEach {
                /*each of content section weight take
                half width of row
                 */
                ContentSection(
                    title=it.title,
                    description = it.description,
                    modifier=Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(it.color)
                        .padding(16.dp)
                )
            }
        }
    }


    }
}

@Preview(showBackground = true)
@Composable
fun QuardentPreview() {
    ComposeQuardentTheme {
            MainContainer(
               content=itemsData
            )
    }
}

data class ContentData(
    val title: String,
    val description: String,
    val color: Color
)