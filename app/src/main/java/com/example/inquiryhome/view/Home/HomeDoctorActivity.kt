package com.example.inquiryhome.view.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import com.example.inquiryhome.view.MaterialThemeColors

class HomeDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colors = if (isSystemInDarkTheme())
                    MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
            )
            {

            }
        }
    }
}
