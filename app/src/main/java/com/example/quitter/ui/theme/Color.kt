package com.example.quitter.ui.theme

import androidx.compose.ui.graphics.Color

val chalk = Color(0xFFE1E1E1)
val BackgroundLight = Color(0xFFE1E1E1)

val charcoal = Color(0xFF2B3535)
val BackgroundDark = Color(0xFF2B3535)

sealed class ThemeColors(
    val background: Color
)  {
    data object Dark: ThemeColors(
        background = BackgroundDark
    )
    data object Light: ThemeColors(
        background = BackgroundLight
    )
}