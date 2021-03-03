package com.example.inquiryhome.model

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class FirebaseData {


    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    var Name: String = ""

    fun GetUserInfo(context: Context, id: String): MutableLiveData<UserPacient>{
        var liveData = MutableLiveData<UserPacient>()
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("userspatient").child(id).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var doctor: UserPacient = snapshot.getValue(UserPacient::class.java)!!
                    Log.e(TAG, "Id: ${firebaseAuth.uid} ${doctor}")
                    Name = doctor.Name!!
                    liveData.value = doctor
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return liveData
    }

}