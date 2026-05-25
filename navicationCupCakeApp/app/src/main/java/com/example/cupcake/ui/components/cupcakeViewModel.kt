package com.example.cupcake.ui.components

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

data class OrderStates(

    val quantity: Int = 0,
    val flavor: String = "",
    val date: String = "",
    val price: String = "",
    val pickupOptions: List<String> = listOf()
)

class CupCakeViewModel: ViewModel(){

    /** Price for a single cupcake */
    private val PRICE_PER_CUPCAKE = 2.00

    /** Additional cost for same day pickup of an order */
    private val PRICE_FOR_SAME_DAY_PICKUP = 3.00
  private var _orderUiState = MutableStateFlow(OrderStates())
  val cupcakesate=_orderUiState.asStateFlow()

    fun updateQuantity(quantity: Int){
        _orderUiState.update { currentState ->
            currentState.copy(quantity=quantity)
        }
    }

    private fun updateFlavor(desiredFlavor: String){
        _orderUiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }


    private fun updatePickUpDate(pickUpDate: String){
        _orderUiState.update { currentState ->
            currentState.copy(
                date=pickUpDate,
                price = calculatePrice(pickUpDate = pickUpDate)
            )
        }
    }



    private fun pickUpOption():List<String>{
        val dateOption=mutableListOf<String>()
        /*
        E day name
        MMM month name in short
        d  number of day
         */
        val dateFormate= SimpleDateFormat("E MMM d" , Locale.getDefault())
        val calendar= Calendar.getInstance()

        repeat(4){
            dateOption.add(dateFormate.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return dateOption
    }

    private fun calculatePrice(
        quantity: Int=_orderUiState.value.quantity,
        pickUpDate: String=_orderUiState.value.date
    ): String{
        var calculateedPrice=quantity * PRICE_PER_CUPCAKE
        if(pickUpOption()[0] == pickUpDate){
            calculateedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }

        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculateedPrice)
        return formattedPrice
    }
}