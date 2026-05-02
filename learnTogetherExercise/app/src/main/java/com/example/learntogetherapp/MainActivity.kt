package com.example.learntogetherapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogetherapp.ui.theme.LearnTogetherAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTogetherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainSection(
                        title="Jetpack Compose tutorial",
                        firstContent = stringResource(R.string.intro),
                        secondContent = stringResource(R.string.mianContent),
                        modifier=Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun TextSection(title:String, firstContent:String, secondContent: String, modifier: Modifier=Modifier){
    Column(modifier) {
        Text(
            text=title,
            fontSize = 24.sp,
            modifier=Modifier
                .padding(16.dp)

        )
        Text(
            text=firstContent,
            modifier= Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text=secondContent,
            textAlign = TextAlign.Justify,
            modifier=Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ImageSection(modifier: Modifier=Modifier){
    val img= painterResource(R.drawable.bg_compose_background)
    Box(modifier){
        Image(
            painter = img,
            contentDescription = "",
            modifier=Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MainSection(title:String, firstContent:String, secondContent: String, modifier:Modifier=Modifier){
    Column(modifier) {
        ImageSection()
        TextSection(
            title,
            firstContent,
            secondContent
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTogetherAppTheme {
     MainSection(
         title="Jetpack Compose tutorial",
         firstContent = stringResource(R.string.intro),
         secondContent = stringResource(R.string.mianContent)
     )
    }
}