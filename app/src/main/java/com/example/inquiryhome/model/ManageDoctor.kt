package com.example.inquiryhome.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.model.User.UserPacient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.disposables.Disposable

class ManageDoctor {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    fun GetAllDoctor(): ArrayList<UserDoctor>{
        var liveData = MutableLiveData<List<UserDoctor>>()
        var listdoctor: ArrayList<UserDoctor> = ArrayList()
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
        databaseReference.child("users")
                .addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(doctor in snapshot.children){
                        var userDoctor: UserDoctor = doctor.getValue(UserDoctor::class.java)!!

                         listdoctor.add(userDoctor)
                       // Utilss.IsReturn.value = true

                    }
                   // liveData.value = listdoctor
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "$error")
            }
        })
        return listdoctor
    }

    fun GetPatient(): MutableLiveData<List<UserPacient>>{
        var liveData = MutableLiveData<List<UserPacient>>()
        var listdoctor: ArrayList<UserPacient> = ArrayList()
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference
        databaseReference.child("userspatient")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(doctor in snapshot.children){
                            var userPacient: UserPacient = doctor.getValue(UserPacient::class.java)!!

                            listdoctor.add(userPacient)

                        }
                        liveData.value = listdoctor
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "$error")
                }
            })
        return liveData
    }

}