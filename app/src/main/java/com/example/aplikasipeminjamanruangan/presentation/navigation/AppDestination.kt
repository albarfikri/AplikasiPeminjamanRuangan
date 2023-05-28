package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

interface AppDestination{
    val icon: ImageVector
    val route: String
    val text: String
}

object Splash: AppDestination{
    override val icon = Icons.Filled.Person
    override val route = "Splash"
    override val text = "Splash"
}

object Home: AppDestination{
    override val icon = Icons.Filled.Home
    override val route = "Home"
    override val text = "Home"
}

object HomeDetail: AppDestination{
    override val icon = Icons.Filled.Home
    override val route = "Home Detail"
    override val text = "Home Detail"
}

object WaitingList: AppDestination{
    override val icon = Icons.Filled.List
    override val route = "Waiting"
    override val text = "Waiting"
}

object History: AppDestination{
    override val icon = Icons.Filled.DateRange
    override val route = "History"
    override val text = "History"
}

val listOfTabScreen = listOf(Home, WaitingList, History)
