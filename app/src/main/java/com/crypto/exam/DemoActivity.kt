package com.crypto.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crypto.exam.model.CurrencyInfo

/**
 * @author Ricky Chen
 * All entry point
 */
class DemoActivity : ComponentActivity() {
    val demoViewModel = DemoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoApp()
        }
    }

    @Composable
    private fun DemoApp() {
        var query by remember { mutableStateOf("") }
        val cryptoList = demoViewModel.cryptoList.collectAsState()
        Scaffold(
            topBar = { TopSearchBar(query, onQueryChanged = { query = it }) },
            bottomBar = { BottomNavigationBar() },
            content = { innerPadding ->
                MainDisplayArea(cryptoList, query, innerPadding)
            }
        )
    }

    @Composable
    fun MainDisplayArea(
        cryptoList: State<List<CurrencyInfo>>,
        query: String,
        innerPadding: PaddingValues
    ) {
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn {
                items(cryptoList.value.filter {
                    it.name.contains(query, ignoreCase = true)
                }) { item ->
                    CryptoListItem(item)
                }
            }
        }
    }

    @Composable
    private fun TopSearchBar(query: String, onQueryChanged: (String) -> Unit) {
        TopAppBar(
            title = {
                OutlinedTextField(
                    value = query,
                    onValueChange = { newValue -> onQueryChanged(newValue) },
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true
                )
            }
        )
    }

    @Composable
    fun CryptoListItem(currencyInfo: CurrencyInfo) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    // Handle item click
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currencyInfo.name.firstOrNull()?.uppercase() ?: "",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(text = currencyInfo.name, modifier = Modifier.weight(1f))

            if (currencyInfo.isCrypto()) {
                Text(
                    text = currencyInfo.symbol,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Next",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }


    @Composable
    private fun BottomNavigationBar() {
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Refresh, contentDescription = "Clear") },
                selected = false,
                onClick = { demoViewModel.clearCurrencyInfo() }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.AddBox, contentDescription = "Insert") },
                selected = false,
                onClick = { demoViewModel.insertAllCurrencyInfo() }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.ArrowCircleRight, contentDescription = "ShowA") },
                selected = false,
                onClick = { demoViewModel.showCryptoInfo() }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.ArrowCircleLeft, contentDescription = "ShowB") },
                selected = false,
                onClick = { demoViewModel.showFiatInfo() }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Visibility, contentDescription = "ShowAll") },
                selected = false,
                onClick = { demoViewModel.showCurrencyInfo() }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        DemoApp()
    }
}