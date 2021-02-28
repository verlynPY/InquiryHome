package com.example.inquiryhome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent

class DoctorRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                FormDoctor(this)
            }
        }
    }
}
