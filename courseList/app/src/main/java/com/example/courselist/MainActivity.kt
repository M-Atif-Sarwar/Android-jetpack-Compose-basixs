package com.example.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
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
import com.example.courselist.ui.theme.AppTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.filled.ExpandCircleDown
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme{
                CourseListApp()
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseTopBar(modifier: Modifier=Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier=modifier
    )
}

@Composable
fun CourseBody(modifier: Modifier=Modifier){
    Column(modifier=modifier) {
        Text(
            text = "About" ,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "This is the course",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Composable
fun CourseItem(courseList: TopicData, modifier: Modifier=Modifier){
    var expanded by remember { mutableStateOf(false) }

    // animate color
    val color by animateColorAsState(
        targetValue = if(expanded){
            MaterialTheme.colorScheme.tertiaryContainer
        }
        else {
            MaterialTheme.colorScheme.primaryContainer
        }
    )
        Card (
            colors = CardDefaults.cardColors(
                containerColor = color
            ),
        modifier= Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio= Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )

    ){
        Row{
            Image(
                painter = painterResource(courseList.courseImage),
                contentDescription = "courseImage",
                modifier= Modifier
                    .size(68.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.small),

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
                        style = MaterialTheme.typography.labelSmall,
                    )

                }

             if(expanded){
                 CourseBody()
             }
            }

            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {expanded = !expanded},


            ) {
                Icon(
                    imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }


}


@Composable
fun CourseListApp(modifier: Modifier=Modifier){
    val topic= DataSource.topics


    Scaffold(
        topBar = {
            CourseTopBar()
        },
    ) { innerpadding ->
        // creating grid type list
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            state = rememberLazyGridState(),
            modifier = Modifier.fillMaxSize()
                .padding(innerpadding)
                .padding(vertical = 16.dp, horizontal = 8.dp)

        ) {
            // course topic is the data
            items(topic) { courseTopic ->
                CourseItem(courseTopic)
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme(darkTheme = true) {
        CourseListApp()
    }
}