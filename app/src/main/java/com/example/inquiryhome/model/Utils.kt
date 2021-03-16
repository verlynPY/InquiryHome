package com.example.inquiryhome.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.inquiryhome.view.Register.DoctorRegisterActivity
import com.example.inquiryhome.view.ExploreDoctorAtivity
import com.example.inquiryhome.view.Home.HomeDoctorActivity
import com.example.inquiryhome.view.Home.HomePatientActivity
import com.example.inquiryhome.view.Login.LoginAcitivity
import com.example.inquiryhome.view.Login.LoginDoctorActivity
import com.example.inquiryhome.view.Register.PatientRegisterActivity
import com.example.inquiryhome.view.StartActivity
import com.google.firebase.auth.FirebaseAuth

object Utilss {

    val IsReturn = mutableStateOf(false)

    fun SignOut(context: Context, firebaseAuth: FirebaseAuth){
        firebaseAuth.signOut()
        context.startActivity(Intent(context, StartActivity::class.java))
    }

    fun GoRegisterDoctor(context: Context){
        val intent = Intent(context, DoctorRegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
    fun GoRegisterPatient(context: Context){
        val intent = Intent(context, PatientRegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun GoLoginDoctor(context: Context){
        val intent = Intent(context, LoginDoctorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun GoLoginPatient(context: Context){
        val intent = Intent(context, LoginAcitivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun GoExploreDoctor(Speciality: String, context: Context){
        val intent = Intent(context, ExploreDoctorAtivity::class.java)
        var bundle = Bundle()
        bundle.putCharSequence("Speciality", Speciality)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun GoHome(context: Context){
        val intent = Intent(context, HomePatientActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
    fun GoDoctorHome(context: Context){
        val intent = Intent(context, HomeDoctorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}