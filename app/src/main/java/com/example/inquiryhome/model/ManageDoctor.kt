package com.example.inquiryhome.model

import androidx.lifecycle.MutableLiveData
import com.example.inquiryhome.model.User.UserDoctor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ManageDoctor {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    fun GetAllDoctor(): MutableLiveData<UserDoctor>{
        var liveData = MutableLiveData<UserDoctor>()
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("users").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(doctor in snapshot.children){
                        var userDoctor: UserDoctor = snapshot.getValue(UserDoctor::class.java)!!

                        assert(userDoctor != null)
                        assert(auth != null)
                        if(!userDoctor.equals(auth.currentUser!!.uid)){
                            liveData.value = userDoctor
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return liveData
    }

}