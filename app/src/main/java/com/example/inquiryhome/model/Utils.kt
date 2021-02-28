package com.example.inquiryhome.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.inquiryhome.view.DoctorRegisterActivity
import com.example.inquiryhome.view.ExploreDoctorAtivity
import com.example.inquiryhome.view.HomeActivity
import com.example.inquiryhome.view.PatientRegisterActivity

object Utilss {

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

    fun GoExploreDoctor(Speciality: String, context: Context){
        val intent = Intent(context, ExploreDoctorAtivity::class.java)
        var bundle = Bundle()
        bundle.putCharSequence("Speciality", Speciality)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun GoHome(context: Context){
        val intent = Intent(context, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}