package com.example.inquiryhome.view

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


object MaterialThemeColors {

    val DarkColor = darkColors(
        primary = Color(0,220,235),
        secondary = Color(0,150,150),
            onPrimary = Color(200,200,200),
            onSecondary = Color(50,50,50)
    )
    val LigthColor = lightColors(
        primary = Color(0,240,245),
        secondary = Color(0,150,150),
            onPrimary = Color(200,200,200),
            onSecondary = Color(230,230,230)
    )



}