package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplikasipeminjamanruangan.presentation.screen.SplashScreen
import com.example.aplikasipeminjamanruangan.presentation.screen.home.HomeDetailScreen
import com.example.aplikasipeminjamanruangan.presentation.screen.home.HomeScreen
import com.example.aplikasipeminjamanruangan.presentation.screen.home.LendingScreen
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.AppViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.CameraXViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.RetrofitViewModel
import com.example.aplikasipeminjamanruangan.presentation.viewmodel.SharedViewModel

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    val appViewModel: AppViewModel = hiltViewModel()
    val sharedViewModel: SharedViewModel = viewModel()
    val cameraXViewModel: CameraXViewModel = hiltViewModel()
    val retrofitViewModel: RetrofitViewModel = hiltViewModel()

    NavHost(
        navController = navController, startDestination = Splash.route, modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreen(navController = navController, modifier = modifier)
        }
        composable(route = Home.route) {
            HomeScreen(
                appViewModel = appViewModel,
                onHeadingToDetail = { room ->
                    sharedViewModel.addRooms(room)
                    navController.navigateSingleTopTo(HomeDetail.route)
                }, modifier = modifier
            )
        }

        composable(route = HomeDetail.route) {
            HomeDetailScreen(
                sharedViewModel = sharedViewModel,
                onNavBack = {
                    navController.navigateSingleTopTo(Home.route)
                },
                onLending = {
                    navController.navigate(Lending.route)
                },
                modifier = modifier
            )
        }

        composable(route = Lending.route) {
            LendingScreen(
                cameraXViewModel = cameraXViewModel,
                sharedViewModel = sharedViewModel,
                retrofitViewModel = retrofitViewModel,
                onNavBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
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