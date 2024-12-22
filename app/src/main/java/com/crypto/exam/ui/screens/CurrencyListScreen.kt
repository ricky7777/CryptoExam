package com.crypto.exam.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.crypto.exam.DemoViewModel
import com.crypto.exam.ui.components.BottomNavigationBar
import com.crypto.exam.ui.components.LoadingWithBackground
import com.crypto.exam.ui.components.TopSearchBar
import org.koin.androidx.compose.koinViewModel

/**
 * @author Ricky
 * because use jetpack compose, use this to replace CurrencyListFragment
 */
@Composable
fun CurrencyListScreen(demoViewModel: DemoViewModel = koinViewModel()) {
    var query by remember { mutableStateOf("") }
    val cryptoList = demoViewModel.cryptoList.collectAsState()
    val isLoading = demoViewModel.isLoading.collectAsState()
    Scaffold(
        topBar = { TopSearchBar(query, onQueryChanged = { query = it }) },
        bottomBar = {
            BottomNavigationBar(
                demoViewModel = demoViewModel,
            )
        },
        content = { innerPadding ->
            MainScreen(cryptoList, query, innerPadding)
            LoadingWithBackground(isLoading = isLoading.value)
        }
    )
}