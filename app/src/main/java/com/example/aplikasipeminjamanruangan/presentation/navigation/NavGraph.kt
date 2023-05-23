package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplikasipeminjamanruangan.presentation.screen.HomeScreen
import com.example.aplikasipeminjamanruangan.presentation.ui.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController, startDestination = Splash.route, modifier = modifier
    ){
        composable(route = Splash.route){
            SplashScreen(navController = navController, modifier = modifier )
        }
        composable(route = Home.route){
            HomeScreen(modifier = modifier)
        }
        composable(route = WaitingList.route){

        }
        composable(route = History.route){

        }
    }
}