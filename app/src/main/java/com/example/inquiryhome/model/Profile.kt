package com.example.inquiryhome.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Profile {

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    fun GetProfile(){
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        //databaseReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.uid)


    }

}