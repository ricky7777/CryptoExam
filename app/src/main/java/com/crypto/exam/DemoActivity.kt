package com.crypto.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertLink
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.RestorePage
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.material.icons.filled.SwitchRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @author Ricky Chen
 * All entry point
 */
class DemoActivity : ComponentActivity() {

    private val demoViewModel = DemoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoApp()
        }
    }

    @Composable
    private fun DemoApp() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHostContainer(navController, Modifier.padding(innerPadding))
        }
    }

    @Composable
    private fun BottomNavigationBar(navController: NavHostController) {
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

    @Composable
    private fun NavHostContainer(navController: NavHostController, modifier: Modifier) {
//        NavHost(navController, startDestination = "home", modifier = modifier) {
//            composable("Clear") { ClearScreen() }
//            composable("Insert") { DashboardScreen() }
//            composable("ShowA") { NotificationScreen() }
//            composable("ShowB") { ProfileScreen() }
//            composable("ShowAll") { SettingsScreen() }
//        }
    }

    @Composable
    private fun ClearScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text("Home Screen")
        }
    }

    @Composable
    private fun DashboardScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text("Dashboard Screen")
        }
    }

    @Composable
    private fun NotificationScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text("Notification Screen")
        }
    }

    @Composable
    private fun ProfileScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text("Profile Screen")
        }
    }

    @Composable
    private fun SettingsScreen() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text("Settings Screen")
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        DemoApp()
    }
}