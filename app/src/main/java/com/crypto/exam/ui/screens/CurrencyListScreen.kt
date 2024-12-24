package com.crypto.exam.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crypto.exam.DemoViewModel
import com.crypto.exam.ui.components.BottomNavigationBar
import com.crypto.exam.ui.components.LoadingWithBackground
import com.crypto.exam.ui.components.TopSearchBar
import org.koin.androidx.compose.koinViewModel

/**
 * @author Ricky
 * because use jetpack compose, use this to replace CurrencyListFragment
 * this screen have three part
 * top side is search bar
 * bottom side is bottom navigation bar
 * content area is main screen
 */
@Composable
fun CurrencyListScreen(demoViewModel: DemoViewModel = koinViewModel()) {
    var query by remember { mutableStateOf("") }
    val cryptoList = demoViewModel.cryptoList.collectAsState()
    val isLoading = demoViewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopSearchBar(query, onQueryChanged = {
            query = it
            cryptoList.value.filter { currency ->
                demoViewModel.matchingCoin(currency, query)
            }
        }) },
        bottomBar = {
            BottomNavigationBar(demoViewModel = demoViewModel)
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                MainScreen(cryptoList)
                LoadingWithBackground(isLoading = isLoading.value)
            }
        }
    )
}