package com.example.cupcake

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.components.CupCakeViewModel
import com.example.cupcake.ui.components.OrderSummaryScreen
import com.example.cupcake.ui.components.SelectOptionScreen
import com.example.cupcake.ui.components.StartOrderScreen


enum class CupCakeScreen(@StringRes title:Int){
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}


private fun cancelOrderAndNavigateToStart(
    viewModel: CupCakeViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(CupCakeScreen.Start.name, inclusive = false)
}

private fun shareOrder(
    context: Context,
    subject:String,
    summary:String,
    ) {
        val intent= Intent().apply {
            action= Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, summary)
            type="text/plain"
        }
      val shareIntent = Intent.createChooser(intent, context.getString(R.string.new_cupcake_order))

     context.startActivity(shareIntent)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    currentScreen: CupCakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val context=LocalContext.current
    TopAppBar(
        title = { Text(currentScreen.name) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }

        },
        actions = {
            IconButton(onClick ={shareOrder(context,subject = "test od share",summary="this is summary")}) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}
@Composable
fun CupCakeApp(){
    val navController=rememberNavController()
    val CupCakeModel: CupCakeViewModel= viewModel()
    val cupcakestate by CupCakeModel.cupcakesate.collectAsState()

    /*
    getting current navigation screen and
    covert to state
     */
    val backStackEntry by navController.currentBackStackEntryAsState()

    /*
    it get current route backStackEntry?.destination?.route
    if route is null ,get first enum nam
    valueof() Convert to enum to String
     */
    val currentScreen = CupCakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupCakeScreen.Start.name
    )


    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack =  navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()}
            )
        }
    ) {contentPadding ->
        NavHost(
          navController = navController,
          startDestination = CupCakeScreen.Start.name,
          modifier=Modifier.padding(contentPadding)
        ){
            composable(route = CupCakeScreen.Start.name) {
                StartOrderScreen(
                    quantityOption= DataSource.quantityOptions,
                    onNextButtonClick = {quantity ->
                      CupCakeModel.updateQuantity(quantity)
                        navController.navigate(CupCakeScreen.Flavor.name)
                    },
                    modifier= Modifier.fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)

                )

                )
            } // end of compose Route

            composable(route =CupCakeScreen.Flavor.name) {
                val context= LocalContext.current
                SelectOptionScreen(
                    subtotal = cupcakestate.price,
                    // context.resources.getString(id) convert id back to actual string
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = {item -> CupCakeModel.updateFlavor(item)},
                    onNextButtonClicked = { navController.navigate(CupCakeScreen.Pickup.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(CupCakeModel, navController)
                    },
                    modifier = Modifier.fillMaxHeight()

                )
            }// edn of compose route

            composable(route = CupCakeScreen.Pickup.name) {
                SelectOptionScreen(
                    subtotal = cupcakestate.price,
                    onNextButtonClicked = { navController.navigate(CupCakeScreen.Summary.name) },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(CupCakeModel, navController)
                    },
                    options = cupcakestate.pickupOptions,
                    onSelectionChanged = { date -> CupCakeModel.updatePickUpDate(date) },
                    modifier = Modifier.fillMaxHeight()
                )
            }// end compoable route

            composable(route = CupCakeScreen.Summary.name) {
                val context=LocalContext.current
                OrderSummaryScreen(
                    orderUiState = cupcakestate,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(CupCakeModel, navController)
                    },
                    onSendButtonClicked = { subject: String, summary: String ->
                         shareOrder(context,subject,summary)
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }// end of compose
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CupCakScreenPreview(){
    CupCakeApp()
}