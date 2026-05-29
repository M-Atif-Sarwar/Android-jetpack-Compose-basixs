package com.example.lanchtraywithnavigation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lanchtraywithnavigation.data.ContentData
import com.example.lanchtraywithnavigation.data.DataSource
import java.text.NumberFormat
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun OptionContent(
    modifier: Modifier=Modifier,
    title:String,
    description:String,
    price: Double
){
    val formattedPrice = NumberFormat.getCurrencyInstance().format(price)
    Column(modifier=modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = formattedPrice,
            style = MaterialTheme.typography.bodySmall,
        )


    }
}


@Composable
fun OptionScreen(
    modifier: Modifier= Modifier,
    contentData:List<ContentData>,
    onNextButton:()-> Unit,
    onCancelButton:()-> Unit,
    onValueChange:(title:String,price: Double)-> Unit

){
    var selectedIndex by rememberSaveable{ mutableStateOf(-1) }
    Column(modifier=modifier) {
        contentData.forEachIndexed {index,item ->
            val title=stringResource(item.title)

            Row(
                modifier= Modifier.selectable(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        onValueChange(title ,item.price)
                    }
                )
            ){

                RadioButton(
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        onValueChange(title ,item.price)
                    }
                )
                OptionContent(
                    title = stringResource(item.title),
                    description = stringResource(item.description),
                    price = item.price,
                )
            }
            Spacer(Modifier.height(15.dp))
        }//end iteration

        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cancel Button
            OutlinedButton(
                onClick = onCancelButton,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, Color.Gray),

                ) {
                Text(
                    text = "CANCEL",
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }

            // Next  Button
            Button(
                onClick = onNextButton,
                enabled = selectedIndex != -1,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),

            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OptionScreenPreview(){
    val entryList= DataSource.entreeList
    OptionScreen(
        modifier = Modifier.fillMaxSize(),
        contentData=entryList,
        onNextButton = {},
        onCancelButton={},
        onValueChange={title,price -> },
    )
}