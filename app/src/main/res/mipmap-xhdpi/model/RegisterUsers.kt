package com.example.daggerapp.model

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterUsers {

    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference
    private var Name:String = ""
    private var Last_Name:String = ""
    private var Email:String = ""
    private var Birth:String = ""
    private var Speciality:String = ""
    private var Squatur:String = ""

    fun Register(doctor: UserDoctor){

        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(doctor.Email,doctor.Squatur)
            .addOnCompleteListener(){ task ->
                if(task.isSuccessful){
                    val user: FirebaseUser = auth.currentUser!!
                    val hashMap: HashMap<String, String> = HashMap<String, String>()
                    val id = user!!.uid
                    reference = FirebaseDatabase.getInstance().getReference("users").child(id)
                    hashMap.put("id", id)
                    hashMap.put("name", doctor.Name.toString())
                    hashMap.put("last_name", doctor.Last_Name.toString())
                    hashMap.put("email", doctor.Email.toString())
                    hashMap.put("birth", doctor.Birth.toString())
                    hashMap.put("speciality", doctor.Specility.toString())
                    hashMap.put("squatur", doctor.Squatur.toString())

                    reference.setValue(hashMap).addOnCompleteListener(){ task ->
                        if(task.isSuccessful){
                            Log.e(TAG, "Todo bien")
                        }
                        else{
                            Log.e(TAG, "Hay un fallo")
                        }
                    }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error $e")
                        }
                }
            }
    }
}