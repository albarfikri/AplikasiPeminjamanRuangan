package com.example.aplikasipeminjamanruangan.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aplikasipeminjamanruangan.presentation.navigation.NavGraph
import com.example.aplikasipeminjamanruangan.presentation.navigation.listOfTabScreen
import com.example.aplikasipeminjamanruangan.presentation.ui.theme.AplikasiPeminjamanRuanganTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainApp()
        }
    }
}

@Composable
fun mainApp() {
    AplikasiPeminjamanRuanganTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()

        val currentBackStack by navController.currentBackStackEntryAsState()

        // fetch currentDestination
        val currentDestination = currentBackStack?.destination

        val currentScreen = listOfTabScreen.find {
            it.route == currentDestination?.route
        }

        Scaffold() { innerPadding ->
            NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    mainApp()
}