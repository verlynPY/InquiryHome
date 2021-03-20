package com.example.inquiryhome.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inquiryhome.MainActivity
import com.example.inquiryhome.view.Home.HomeDoctorActivity
import com.example.inquiryhome.view.Home.HomePatientActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StartActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference


        if(auth.currentUser != null){
            databaseReference.child("userspatient").child(auth.currentUser!!.uid)
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            startActivity(Intent(this@StartActivity, HomePatientActivity::class.java))
                        }
                        else{
                            startActivity(Intent(this@StartActivity, HomeDoctorActivity::class.java))
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
        }
        else {
            setContent {
                MaterialTheme(
                    colors = if (isSystemInDarkTheme())
                        MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                ) {
                    val buttonShape = RoundedCornerShape(25.dp)
                    val buttonModifier =
                        Modifier
                            .fillMaxWidth(0.9f)
                            .preferredHeight(60.dp)
                            .padding(6.dp)
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material.Text(
                            text = "InquiryHome", fontSize = 32.sp,
                            fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {
                                //startActivity(Intent(this@StartActivity, LoginAcitivity::class.java))
                                var intent = Intent(this@StartActivity, MainActivity::class.java)
                                var bundle = Bundle()
                                bundle.putCharSequence("Destino", "Login")
                                intent.putExtras(bundle)
                                startActivity(intent)

                            }, shape = buttonShape, modifier = buttonModifier) {
                                androidx.compose.material.Text(
                                    text = "Login",
                                    style = MaterialTheme.typography.h6
                                )
                            }
                            Button(onClick = {
                                //startActivity(Intent(this@StartActivity, MainActivity::class.java))
                                var intent = Intent(this@StartActivity, MainActivity::class.java)
                                var bundle = Bundle()
                                bundle.putCharSequence("Destino", "Register")
                                intent.putExtras(bundle)
                                startActivity(intent)
                            }, shape = buttonShape, modifier = buttonModifier) {
                                androidx.compose.material.Text(
                                    text = "Register",
                                    style = MaterialTheme.typography.h6
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        /*if(auth.currentUser != null){
            startActivity(Intent(this, HomePatientActivity::class.java))
        }*/
    }
}
