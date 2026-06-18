package com.qoqokoi.myapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TipCalculatorUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_default_tip() {
        // Inisialisasi UI komponen ke dalam runtime testing
        composeTestRule.setContent {
            TipCalculatorApp()
        }

        // Simulasikan user mengetik tagihan Rp 100.000
        composeTestRule.onNodeWithText("Jumlah Tagihan")
            .performTextInput("100000")

        // Simulasikan user mengetik tip 20%
        composeTestRule.onNodeWithText("Persentase Tip (%)")
            .performTextInput("20")

        // Ambil format mata uang lokal sesuai device runtime untuk validasi teks output
        val expectedTipText = "Jumlah Tip: Rp20.000,00"
        
        // Assert: Pastikan teks hasil kalkulasi muncul di layar dengan tepat
        composeTestRule.onNodeWithText(expectedTipText).assertExists()
    }
}
