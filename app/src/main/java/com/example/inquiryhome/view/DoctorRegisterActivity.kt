package com.example.inquiryhome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.FragmentManager

class DoctorRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragment: FragmentManager = supportFragmentManager

        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                FormDoctor(fragment, this)
            }
        }
    }
}
