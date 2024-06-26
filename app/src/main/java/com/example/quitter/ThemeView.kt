package com.example.quitter

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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