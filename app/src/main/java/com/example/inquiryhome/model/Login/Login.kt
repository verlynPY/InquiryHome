package com.example.inquiryhome.model.Login

import android.content.ContentValues
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.Utilss.GoHome
import com.google.firebase.auth.FirebaseAuth

class Login(private val fragmentManager: FragmentManager) {

    private lateinit var auth: FirebaseAuth

    fun PatientLogin(context: Context, Email: String, Password: String){
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    GoHome(context = context)
                }
            }
            .addOnFailureListener { e ->
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
    }
}