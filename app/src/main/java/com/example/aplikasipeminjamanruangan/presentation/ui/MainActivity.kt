package com.example.aplikasipeminjamanruangan.presentation.ui

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aplikasipeminjamanruangan.presentation.navigation.Home
import com.example.aplikasipeminjamanruangan.presentation.navigation.HomeBottomNavBar
import com.example.aplikasipeminjamanruangan.presentation.navigation.HomeDetail
import com.example.aplikasipeminjamanruangan.presentation.navigation.Lending
import com.example.aplikasipeminjamanruangan.presentation.navigation.LendingForm
import com.example.aplikasipeminjamanruangan.presentation.navigation.NavGraph
import com.example.aplikasipeminjamanruangan.presentation.navigation.Splash
import com.example.aplikasipeminjamanruangan.presentation.navigation.listOfTabScreen
import com.example.aplikasipeminjamanruangan.presentation.ui.theme.AplikasiPeminjamanRuanganTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }

    @Composable
    fun MainApp() {
        AplikasiPeminjamanRuanganTheme {
            // A surface container using the 'background' color from the theme
            val navController = rememberNavController()

            val currentBackStack by navController.currentBackStackEntryAsState()

            // fetch currentDestination
            val currentDestination = currentBackStack?.destination

            val currentScreen = listOfTabScreen.find {
                it.route == currentDestination?.route
            } ?: Home

            Scaffold(bottomBar = {
                AnimatedVisibility(
                    visible = currentDestination?.route != Splash.route &&
                            currentDestination?.route != HomeDetail.route
                            && currentDestination?.route != Lending.route &&
                            currentDestination?.route != LendingForm.route
                ) {
                    bottomBar(navBackStackEntry = currentBackStack, navController = navController)
                }
            }) { innerPadding ->
                NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }

    @Composable
    fun bottomBar(navBackStackEntry: NavBackStackEntry?, navController: NavHostController) {
        AnimatedVisibility(visible = true) {
            HomeBottomNavBar(
                navBackStackEntry = navBackStackEntry, navController = navController
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainApp()
    }
}

