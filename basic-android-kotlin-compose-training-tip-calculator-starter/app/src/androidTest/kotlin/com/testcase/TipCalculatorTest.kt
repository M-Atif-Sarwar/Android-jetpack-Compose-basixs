package com.testcase
import com.example.tiptime.calculateTip
import java.text.NumberFormat
import org.junit.Test
import org.junit.Assert.assertEquals

class TipCalculatorTest {
    @Test
    fun calculateTip_20PercentNoRoundUp(){
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}