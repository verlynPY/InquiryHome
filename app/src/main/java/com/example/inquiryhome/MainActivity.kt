package com.example.inquiryhome

import android.content.Intent
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
import com.example.inquiryhome.model.Utilss
import com.example.inquiryhome.model.Utilss.GoLoginDoctor
import com.example.inquiryhome.model.Utilss.GoLoginPatient
import com.example.inquiryhome.model.Utilss.GoRegisterDoctor
import com.example.inquiryhome.model.Utilss.GoRegisterPatient
import com.example.inquiryhome.view.GetStarted
import com.example.inquiryhome.view.MaterialThemeColors
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    val TAG = "com.example.inquiryhome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = intent.extras
        var Destino = bundle!!.getCharSequence("Destino")
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                if(Destino!!.equals("Register")){
                    GetStarted(context = this, onClick = { GoRegisterPatient(this)},
                        onClick2 = { GoRegisterDoctor(this) })

                }
                else{
                    GetStarted(context = this, onClick = { GoLoginPatient(this)},
                        onClick2 = { GoLoginDoctor(this) })
                }

            }
        }
    }
}

