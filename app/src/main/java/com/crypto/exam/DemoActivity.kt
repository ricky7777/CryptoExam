package com.crypto.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crypto.exam.ui.screens.CurrencyListScreen

/**
 * @author Ricky Chen
 * entry point connect with view model
 */
class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyListScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        CurrencyListScreen()
    }
}