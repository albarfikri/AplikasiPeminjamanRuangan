package com.example.aplikasipeminjamanruangan.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

@Composable
fun HomeBottomNavBar(
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController
) {
    val currDestination = navBackStackEntry?.destination
    BottomNavigation {
        listOfTabScreen.forEach{screen ->
            AddItem(screen = screen, currDestination = currDestination , navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: AppDestination,
    currDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(
                text = screen.text,
                fontSize = if (currDestination?.route == screen.route) 14.sp else 12.sp,
                style = if (currDestination?.route == screen.route) MaterialTheme.typography.h3 else
                    MaterialTheme.typography.h3,
            )
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = screen.text)
        },
        selected = currDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigateSingleTopTo(screen.route)
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}