package com.example.daggerapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.example.daggerapp.view.ui.theme.DaggerAppTheme

class PatientRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                FormPatient()
            }
        }
    }
}
