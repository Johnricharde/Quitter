package com.example.quitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.quitter.ui.theme.QuitterTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            QuitterTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "mainPage",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("mainPage") { MainPage(navController) }
                        composable("achievementsPage") { AchievementsPage(navController) }
                        composable("statisticsPage") { StatisticsPage(navController) }
                        composable("settingsPage") { SettingsPage(navController) }
                    }
                }
            }
        }
    }
}