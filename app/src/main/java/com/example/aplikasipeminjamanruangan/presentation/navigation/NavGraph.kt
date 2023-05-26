package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplikasipeminjamanruangan.presentation.screen.home.HomeScreen
import com.example.aplikasipeminjamanruangan.presentation.screen.SplashScreen
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController, startDestination = Splash.route, modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreen(navController = navController, modifier = modifier)
        }
        composable(route = Home.route) {
            val appViewModel: AppViewModel = hiltViewModel()
            HomeScreen(modifier = modifier, appViewModel = appViewModel)
        }
        composable(route = WaitingList.route) {

        }
        composable(route = History.route) {

        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}