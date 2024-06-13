package com.example.quitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quitter.ui.theme.QuitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val themeViewModel: ThemeViewModel = viewModel(factory = ThemeViewModelFactory(applicationContext))

            QuitterTheme(darkTheme = themeViewModel.isDarkTheme) {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "mainPage",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("mainPage") { MainPage(navController, modifier = Modifier) }
                        composable("achievementsPage") { AchievementsPage(navController, modifier = Modifier) }
                        composable("settingsPage") { SettingsPage(navController, modifier = Modifier, themeViewModel) }
                    }
                }
            }
        }
    }
}
