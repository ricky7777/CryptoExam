package com.crypto.exam.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.crypto.exam.DemoViewModel
import com.crypto.exam.R
import androidx.compose.ui.platform.LocalContext

/**
 * @author Ricky
 * bottom navigation bar, simulation loading
 */
@Composable
fun BottomNavigationBar(demoViewModel: DemoViewModel) {
    val context = LocalContext.current

    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.desc_clear)
                )
            },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.clearCurrencyInfo()
                    showToast(context, R.string.text_clear_success)
                }
            })
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Default.AddBox,
                    contentDescription = stringResource(R.string.desc_insert)
                )
            },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.insertAllCurrencyInfo()
                    showToast(context, R.string.text_insert_success)
                    demoViewModel.showCryptoInfo()
                }
            })
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = R.drawable.ic_crypto),
                contentDescription = stringResource(R.string.desc_show_crypto),
                tint = Color.Unspecified, modifier = Modifier.size(32.dp),
            )
        }, selected = false, onClick = {
            demoViewModel.performActionWithLoading {
                demoViewModel.showCryptoInfo()
            }
        })
        BottomNavigationItem(icon = {
            Icon(
                painterResource(id = R.drawable.ic_fiat),
                contentDescription = stringResource(R.string.desc_show_fiat),
                tint = Color.Unspecified, modifier = Modifier.size(30.dp),
            )
        }, selected = false, onClick = {
            demoViewModel.performActionWithLoading {
                demoViewModel.showFiatInfo()
            }
        })
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Default.Visibility,
                    contentDescription = stringResource(R.string.desc_show_all)
                )
            },
            selected = false,
            onClick = {
                demoViewModel.performActionWithLoading {
                    demoViewModel.showCurrencyInfo()
                }
            })
    }

}

private fun showToast(context: Context, messageId: Int) {
    Toast.makeText(context, messageId, Toast.LENGTH_LONG).show()
}