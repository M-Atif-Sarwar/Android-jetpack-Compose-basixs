package com.example.cupcake.ui.components


import android.net.http.QuicOptions
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.data.DataSource



val quantityOptions= DataSource.quantityOptions

@Composable
fun StartOrderScreen(
    quantityOption:List<Pair<Int, Int>>,
    onNextButtonClick:(Int)->Unit,
    modifier: Modifier=Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.padding_small)),

        ) {
            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Image(
                painter = painterResource(R.drawable.cupcake),
                contentDescription = "This is a cupcake",
                modifier=Modifier.size(100.dp)
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Text(
                text = stringResource(R.string.order_cupcakes),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            //second section of screen
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_medium)
                )
            )
            {
                // creating options buttons according to option
                quantityOption.forEach { items ->
                   StartScreenButtons(
                       buttonsLabels = items.first,
                       modifier = Modifier.fillMaxWidth(),
                       onclick = {onNextButtonClick(items.second)}
                   )
                 }

            }

        }
    }
}


@Composable
fun StartScreenButtons(
    onclick:()-> Unit,
    @StringRes buttonsLabels: Int,
    modifier: Modifier= Modifier)
  {
      Button(
          onClick = onclick,
          modifier=Modifier.widthIn(min=250.dp)
      ){
          Text(stringResource(buttonsLabels))
      }


}


@Preview(showBackground = true , showSystemUi = true)
@Composable
fun DisplayStartScreen(){
    StartOrderScreen(
        quantityOption = quantityOptions,
        onNextButtonClick = {}

    )

}

