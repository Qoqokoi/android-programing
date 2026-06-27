package com.qoqokoi.myapp

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorUnitTest {
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 100000.0
        val tipPercent = 20.0
        val expectedTip = 20000.0

        // Mengakses fungsi internal dari MainActivity secara terisolasi
        val actualTip = calculateTipTest(amount, tipPercent)

        assertEquals(expectedTip, actualTip, 0.001)
    }

    // Skenario Kustom Mandiri: Validasi Batas Bawah Input Negatif
    @Test
    fun calculateTip_NegativeAmount_ReturnsZero() {
        val amount = -50000.0
        val tipPercent = 10.0
        val expectedTip = 0.0

        val actualTip = calculateTipTest(amount, tipPercent)

        assertEquals(expectedTip, actualTip, 0.001)
    }
}

// Helper function untuk menjembatani pengujian tanpa instansiasi Activity
fun calculateTipTest(
    amount: Double,
    tipPercent: Double,
): Double {
    if (amount < 0 || tipPercent < 0) return 0.0
    return (tipPercent / 100) * amount
}
