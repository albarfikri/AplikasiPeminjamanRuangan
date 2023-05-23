package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

interface AppDestination{
    val icon: ImageVector
    val route: String
}

object Splash{
    const val route = "Splash"
}

object Home: AppDestination{
    override val icon = Icons.Filled.Home
    override val route = "Home"
}

object WaitingList: AppDestination{
    override val icon = Icons.Filled.List
    override val route = "Waiting"
}

object History: AppDestination{
    override val icon = Icons.Filled.DateRange
    override val route = "History"
}

val listOfTabScreen = listOf(Home, WaitingList, History)
