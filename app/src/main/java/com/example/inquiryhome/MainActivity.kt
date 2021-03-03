package com.example.inquiryhome

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inquiryhome.view.GetStarted
import com.example.inquiryhome.view.MaterialThemeColors
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    val TAG = "com.example.inquiryhome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                GetStarted(context = this)
            }
        }
    }
}

