package com.example.inquiryhome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class ExploreDoctorAtivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = intent.extras
        var Speciality = bundle!!.getCharSequence("Speciality")


        setContent {
            Text(text = "$Speciality")
        }
    }
}
