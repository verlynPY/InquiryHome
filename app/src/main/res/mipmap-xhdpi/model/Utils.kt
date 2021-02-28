package com.example.inquiryhome.model

import android.content.Context
import android.content.Intent
import com.example.inquiryhome.view.DoctorRegisterActivity
import com.example.inquiryhome.view.PatientRegisterActivity


object Utils {

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

}