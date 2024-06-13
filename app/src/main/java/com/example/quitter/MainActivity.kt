package com.example.quitter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
                        composable("mainPage") { MainPage(navController) }
                        composable("achievementsPage") { AchievementsPage(navController) }
                        composable("statisticsPage") { StatisticsPage(navController) }
                        composable("settingsPage") { SettingsPage(navController, modifier = Modifier, themeViewModel) }
                    }
                }
            }
        }
    }
}

class ThemeViewModel(context: Context) : ViewModel() {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    var isDarkTheme by mutableStateOf(sharedPreferences.getBoolean("is_dark_theme", false))
        private set

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
        editor.putBoolean("is_dark_theme", isDarkTheme)
        editor.apply()
    }
}

class ThemeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}