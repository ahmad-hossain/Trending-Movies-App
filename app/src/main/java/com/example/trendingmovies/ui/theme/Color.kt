package com.example.trendingmovies.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val DeepGray = hexToArgb("#272833")
val GreyBackground = hexToArgb("#1c1c27")
val TextWhite = Color(0xffeeeeee)
val TextGrey = hexToArgb("#6b6b76")
val Yellow = hexToArgb("#ffb43a")

fun hexToArgb(hexColor: String): Color {
    return Color(android.graphics.Color.parseColor(hexColor))
}