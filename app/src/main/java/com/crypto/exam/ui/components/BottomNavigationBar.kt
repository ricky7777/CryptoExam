package com.crypto.exam.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.crypto.exam.DemoViewModel
import com.crypto.exam.R
import org.koin.core.context.GlobalContext.get

/**
 * @author Ricky
 * bottom navigation bar, simulation loading
 */
@Composable
fun BottomNavigationBar(demoViewModel: DemoViewModel) {

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Refresh, contentDescription = "Clear") },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.clearCurrencyInfo()
                    demoViewModel.showToast("Clear Success!")
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AddBox, contentDescription = "Insert") },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.insertAllCurrencyInfo()
                    demoViewModel.showToast("Insert Success!")
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_crypto), contentDescription = "ShowA",
                    tint = Color.Unspecified, modifier = Modifier.size(32.dp),
                )
            },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.showCryptoInfo()
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_fiat), contentDescription = "ShowB",
                    tint = Color.Unspecified, modifier = Modifier.size(30.dp),
                )
            },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.showFiatInfo()
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Visibility, contentDescription = "ShowAll") },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.showCurrencyInfo()
                }
            }
        )
    }
}