package com.example.lanchtraywithnavigation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lanchtraywithnavigation.data.DataStates
import java.text.NumberFormat

@Composable
fun OrderSummaryScreen(
    modifier:Modifier = Modifier,
    appState: DataStates,
    onCheckoutButton:()-> Unit,
    onCancelButton:()->Unit
) {

    val primaryPurple = Color(0x6A5ACD)
    val formattedSubTotal = NumberFormat.getCurrencyInstance().format(appState.subTotal)
    val formattedTax = NumberFormat.getCurrencyInstance().format(appState.total)
    val formattedTotal = NumberFormat.getCurrencyInstance().format(appState.total)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header
            Text(
                text = "Order Summary",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            appState.summary.forEach { item ->
                val formattedPrice = NumberFormat.getCurrencyInstance().format(item.price)
                SummaryRow(label = item.title, price = formattedPrice)

            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.5f)
            )

            // Financial Breakdown (Right-aligned using Column layout)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                BreakdownRow(label = "Subtotal: ", value = formattedSubTotal)
                Spacer(modifier = Modifier.height(4.dp))
                BreakdownRow(label = "Tax: ", value = formattedTax)
                Spacer(modifier = Modifier.height(6.dp))
                BreakdownRow(
                    label = "Total: ",
                    value = formattedTotal,
                    isTotal = true
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
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

                // checkout  Button
                Button(
                    onClick = onCheckoutButton,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(contai
                            nerColor = primaryPurple)
                ) {
                    Text(
                        text = "Checkout",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 15.sp, color = Color.DarkGray)
        Text(text = price, fontSize = 15.sp, color = Color.Black)
    }
}

@Composable
fun BreakdownRow(label: String, value: String, isTotal: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 16.sp else 15.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = if (isTotal) 16.sp else 15.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        OrderSummaryScreen(
            appState = DataStates(),
            onCheckoutButton={},
            onCancelButton={}
        )
    }
}