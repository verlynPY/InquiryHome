package com.example.inquiryhome.model.Login

import android.content.ContentValues
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.Utilss.GoDoctorHome
import com.example.inquiryhome.model.Utilss.GoHome
import com.google.firebase.auth.FirebaseAuth

class Login(private val fragmentManager: FragmentManager) {

    private lateinit var auth: FirebaseAuth

    fun PatientLogin(context: Context, Email: String, Password: String, Patient: Boolean, Doctor: Boolean){

        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if(Patient == true){
                        GoHome(context = context)
                    }
                    if(Doctor == true){
                        GoDoctorHome(context = context)
                    }

                }
            }
            .addOnFailureListener { e ->
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
    }

}