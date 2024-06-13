package com.example.quitter.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val BackgroundLight = Color(0xFFE1E1E1)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val BackgroundDark = Color(0xFF2B3535)

sealed class ThemeColors(
    val background: Color,
//    val surface: Color,
//    val primary: Color,
//    val text: Color
)  {
    object Dark: ThemeColors(
        background = BackgroundDark,
//        surface = dark_btn,
//        primary = clear_dark,
//        text = Color.White
    )
    object Light: ThemeColors(
        background = BackgroundLight,
//        surface = light_btn,
//        primary = clear_light,
//        text = Color.Black
    )
}