package com.example.inquiryhome.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.UserDoctor
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class HomePatientActivity : AppCompatActivity() {

    val TAG = "com.example.inquiryhome"
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    var Name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        GetUserInfo()

        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme())
                MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor)
            {
                PatientHomeView(this)
            }
        }
    }


    fun GetUserInfo(){
        var id: String = firebaseAuth.currentUser!!.uid
        databaseReference.child("users").child(id).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    var doctor: UserDoctor = snapshot.getValue(UserDoctor::class.java)!!
                    Log.e(TAG, "Id: ${firebaseAuth.uid} ${doctor}")
                    Name = doctor.Name!!
                    Toast.makeText(this@HomePatientActivity, "$Name", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
