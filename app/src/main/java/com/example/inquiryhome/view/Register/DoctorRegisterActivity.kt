package com.example.inquiryhome.view.Register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.view.FormDoctor
import com.example.inquiryhome.view.MaterialThemeColors

class DoctorRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragment: FragmentManager = supportFragmentManager

        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                overridePendingTransition(0,0)
                FormDoctor(fragment, this)
                overridePendingTransition(0,0)
            }
        }
    }
}
