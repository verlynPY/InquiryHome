package com.example.inquiryhome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.setContent
import com.example.inquiryhome.model.Utilss.GoLoginDoctor
import com.example.inquiryhome.model.Utilss.GoLoginPatient
import com.example.inquiryhome.model.Utilss.GoRegisterDoctor
import com.example.inquiryhome.model.Utilss.GoRegisterPatient
import com.example.inquiryhome.view.GetStarted
import com.example.inquiryhome.view.MaterialThemeColors

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

