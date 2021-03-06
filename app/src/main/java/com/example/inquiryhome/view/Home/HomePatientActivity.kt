package com.example.inquiryhome.view.Home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.view.StartActivity
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth


class HomePatientActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val id: String = firebaseAuth.currentUser!!.uid

        setContent{
            MaterialTheme(colors = if (isSystemInDarkTheme())
                MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.primary)
                    }
                }
            }
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.ShowProfile(this, id).observe(this, Observer { patient ->

            setContent {
                MaterialTheme(
                    colors = if (isSystemInDarkTheme())
                        MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                )
                {
                    overridePendingTransition(0, 0)
                    PatientHomeView(context = this, patient = patient, onClick = { SignOut(firebaseAuth = firebaseAuth) })
                    overridePendingTransition(0, 0)

                }
            }
        })
    }

    private fun SignOut(firebaseAuth: FirebaseAuth){
        firebaseAuth.signOut()
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

}
