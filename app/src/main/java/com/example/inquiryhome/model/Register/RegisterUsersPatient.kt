package com.example.inquiryhome.model.Register

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.UserPacient
import com.example.inquiryhome.model.Utilss
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterUsersPatient(private val fragmentManager: FragmentManager) {

    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference


    fun RegisterPatient(context: Context, patient: UserPacient){

        auth = FirebaseAuth.getInstance()
        reference = FirebaseDatabase.getInstance().reference

        auth.createUserWithEmailAndPassword(patient.Email, patient.Password).addOnCompleteListener{ taskauth ->
            if(taskauth.isSuccessful){
                var user: FirebaseUser = auth.currentUser!!
                var id: String = user.uid
                var hashMap: HashMap<String, String> = HashMap()
                hashMap.put("Id", patient.Id.toString())
                hashMap.put("Name", patient.Name.toString())
                hashMap.put("Last_Name", patient.Last_Name.toString())
                hashMap.put("Email", patient.Email.toString())
                hashMap.put("Birth", patient.Birth.toString())
                hashMap.put("Password", patient.Password)

                reference.child("userspatient").child(id).setValue(hashMap).addOnCompleteListener(){ taskdata ->
                    if(taskdata.isSuccessful){
                        Log.e(ContentValues.TAG, "Todo bien")
                        Utilss.GoHome(context = context)
                    }
                }
                    .addOnFailureListener { e ->
                        var dialog = DialogManager("$e")
                        dialog.show(fragmentManager, ContentValues.TAG)
                    }
            }
        }
            .addOnFailureListener { e ->
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
    }
}