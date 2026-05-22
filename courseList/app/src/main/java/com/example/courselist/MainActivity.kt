package com.example.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courselist.dataSource.DataSource
import com.example.courselist.model.TopicData
import com.example.courselist.ui.theme.CourseListTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseListApp()
        }
    }
}

@Composable
fun CourseItem(courseList: TopicData, modifier: Modifier=Modifier){
    Card {
        Row{
            Image(
                painter = painterResource(courseList.courseImage),
                contentDescription = "courseImage",
                modifier= Modifier
                    .size(68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(modifier=Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(courseList.title),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Start,
                        contentDescription = "start Icon"
                    )

                    Text(
                        text = courseList.numberOfSeats.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )

                }


            }
        }
    }


}


@Composable
fun CourseListApp(modifier: Modifier=Modifier){
    val topic= DataSource.topics

    // creating grid type list
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = rememberLazyGridState(),
        modifier=Modifier.fillMaxSize()
            .safeDrawingPadding()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        // course topic is the data
        items(topic){courseTopic->
           CourseItem(courseTopic)
        }
    }


}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CourseListTheme {
        CourseListApp()
    }
}