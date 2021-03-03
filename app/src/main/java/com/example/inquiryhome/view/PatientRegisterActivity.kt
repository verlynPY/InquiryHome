package com.example.inquiryhome.view

import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import com.example.daggerapp.view.ui.theme.DaggerAppTheme
import com.google.firebase.auth.FirebaseAuth

class PatientRegisterActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        fragmentManager = supportFragmentManager
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                overridePendingTransition(0,0)
                FormPatient(this, fragmentManager)
                overridePendingTransition(0,0)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(applicationContext, HomePatientActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
