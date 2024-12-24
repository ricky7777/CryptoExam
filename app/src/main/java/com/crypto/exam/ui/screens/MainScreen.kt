package com.crypto.exam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crypto.exam.R
import com.crypto.exam.model.CurrencyInfo
import com.crypto.exam.ui.components.CryptoListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Ricky
 * Main display area(currencies show area)
 */
@Composable
fun MainScreen(cryptoList: State<List<CurrencyInfo>>) {
    if (cryptoList.value.isEmpty()) {
        EmptyView()
        return
    }

    Column {
        LazyColumn {
            items(cryptoList.value) { item ->
                CryptoListItem(item)
            }
        }
    }

}

@Composable
private fun EmptyView(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
    contentDescription: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.text_no_data_available), style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val mockCryptoList = listOf(
        CurrencyInfo(name = "Bitcoin", symbol = "BTC", id = "", type = "", code = ""),
        CurrencyInfo(name = "Ethereum", symbol = "ETH", id = "", type = "", code = ""),
        CurrencyInfo(name = "Lite coin", symbol = "LTC", id = "", type = "", code = ""),
    )

    val list = MutableStateFlow<List<CurrencyInfo>>(mockCryptoList)

    val cryptoList: StateFlow<List<CurrencyInfo>> = list

    MainScreen(
        cryptoList = cryptoList.collectAsState(),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyView() {
    EmptyView(
        textStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
        contentDescription = "No Data Icon"
    )
}