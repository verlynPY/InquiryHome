package com.example.inquiryhome.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.MainActivity
import com.example.inquiryhome.model.UserDoctor
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class HomePatientActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val id: String = firebaseAuth.currentUser!!.uid

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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
