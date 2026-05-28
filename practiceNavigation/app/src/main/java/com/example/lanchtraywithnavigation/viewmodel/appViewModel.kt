package com.example.lanchtraywithnavigation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lanchtraywithnavigation.data.DataStates
import com.example.lanchtraywithnavigation.data.Summary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
     private var appState=MutableStateFlow(DataStates())
     val appSate=appState.asStateFlow()

    private fun calculateSubTotal(summary: List<Summary>): Double  {
        var subtotal = 0.0

        for (item in summary) {
            subtotal += item.price
        }

        return subtotal
    }

    private fun calculateTotal(subtotal:Double,tax:Double): Double{
        var total=subtotal+tax
        return total
    }

    fun addDishes(title:String,price: Double){
        appState.update { currentState ->

            val updatedSummary=currentState.summary + Summary(title,price)
            val newSubTotal = calculateSubTotal(updatedSummary)
            val newTotal = calculateTotal(newSubTotal, currentState.tax)

            currentState.copy(
                summary =updatedSummary,
                subTotal = newSubTotal,
                total = newTotal
            )

        }
    }

    fun resetOrder() {
        appState.value = DataStates()
    }
}