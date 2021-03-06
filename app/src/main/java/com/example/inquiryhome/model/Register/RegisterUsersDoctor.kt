package com.example.inquiryhome.model

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.User.UserDoctor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterUsersDoctor(private val fragmentManager: FragmentManager) {

    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference
    private var Name:String = ""
    private var Last_Name:String = ""
    private var Email:String = ""
    private var Birth:String = ""
    private var Speciality:String = ""
    private var Squatur:String = ""

    fun RegisterDoctor(context: Context, doctor: UserDoctor){

        auth = FirebaseAuth.getInstance()
        reference = FirebaseDatabase.getInstance().reference

        auth.createUserWithEmailAndPassword(doctor.Email, doctor.Squatur)
            .addOnCompleteListener(){ task ->
                if(task.isSuccessful){
                    Log.e(TAG, "Auth is good")
                    val user: FirebaseUser = auth.currentUser!!
                    val hashMap: HashMap<String, String> = HashMap<String, String>()
                    val id = user!!.uid
                    hashMap.put("Id", id)
                    hashMap.put("Name", doctor.Name.toString())
                    hashMap.put("Last_name", doctor.Last_Name.toString())
                    hashMap.put("Email", doctor.Email.toString())
                    hashMap.put("Birth", doctor.Birth.toString())
                    hashMap.put("Speciality", doctor.Specility.toString())
                    hashMap.put("squatur", doctor.Squatur.toString())

                    reference.child("users").child(id).setValue(hashMap).addOnCompleteListener(){ task ->
                        if(task.isSuccessful){
                            Log.e(TAG, "Todo bien")
                            Utilss.GoHome(context = context)

                        }
                    }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Errorr $e")
                            var dialog = DialogManager("$e")
                            dialog.show(fragmentManager, ContentValues.TAG)
                        }
                }

            }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error $e")
                    var dialog = DialogManager("$e")
                    dialog.show(fragmentManager, ContentValues.TAG)
                }
    }
}