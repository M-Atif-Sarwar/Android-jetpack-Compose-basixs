package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                BusinessCard()
            }
        }
    }
}

data class ContactData(val icon:ImageVector,val title:String)

@Composable
fun Contact(modifier: Modifier=Modifier){
    val contacts = listOf(
        ContactData(Icons.Filled.Call, "03472107400"),
        ContactData(Icons.Filled.Share, "Share"),
        ContactData(Icons.Filled.Email, "@abc,com"),

        )
     Column(
         verticalArrangement = Arrangement.spacedBy(6.dp),
         modifier=modifier
             .padding(bottom = 50.dp),) {

         contacts.forEach { items ->

             Row{
                 Icon(
                     imageVector = items.icon,
                     contentDescription = "Call",
                     modifier = Modifier.size(20.dp)
                 )
                 Spacer(Modifier.width(10.dp))
                 Text(
                     text = items.title,
                     fontSize = 14.sp
                 )
             }
         }
     }
}

@Composable
fun Profile(modifier: Modifier=Modifier){
     Column(
         horizontalAlignment = Alignment.CenterHorizontally,
         modifier=modifier) {
         Box(
             contentAlignment = Alignment.Center,
             modifier = Modifier.fillMaxWidth()
         ){
             Image(
                 painter = painterResource(R.drawable.my_img),
                 contentDescription = "my image",
                 modifier = Modifier.size(120.dp)

             )
         }
         Text(
             text="Atif Sarwar",
             fontSize = 24.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center
         )
         Text(
             text="Android Developer"
         )
     }
}
@Composable
fun BusinessCard(modifier: Modifier=Modifier){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Profile(
            modifier = Modifier.align(Alignment.Center)
        )

        Contact(
            modifier = Modifier.align(Alignment.BottomCenter)
        )





    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCard()
}


