package com.example.inquiryhome.view

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.R
import com.example.inquiryhome.model.Chat.ManageChat
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.model.Utilss
import com.example.inquiryhome.view.Chat.ChatPatientActivity
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class ExploreDoctorAtivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    var result: ArrayList<UserDoctor>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var bundle = intent.extras
        var Speciality = bundle!!.getCharSequence("Speciality")
        var id = firebaseAuth.currentUser!!.uid
        viewmodel.ShowDoctors(Speciality.toString())
        viewmodel.ShowProfile(this, id).observe(this, Observer { patient ->
        viewmodel.listDoctor.observe(this, Observer {
            for (doctor in it) {
                result!!.add(doctor)
                if(!result!!.isEmpty()){
                    Utilss.IsReturn.value = true
                }

            }
        })
            //viewmodel.ShowDoctors()
                setContent {
                        MaterialTheme(
                            colors = if (isSystemInDarkTheme())
                                MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                        ) {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = {
                                            Text(text = "${patient.Name}")
                                        },

                                        navigationIcon = {
                                            IconButton(onClick = { }) {
                                                Icon(
                                                    Icons.Filled.Menu,
                                                    tint = MaterialTheme.colors.onPrimary
                                                )
                                            }
                                        },
                                        actions = {
                                            IconButton(onClick = {
                                                Utilss.SignOut(
                                                    this@ExploreDoctorAtivity,
                                                    firebaseAuth = firebaseAuth
                                                )
                                            }) {
                                                Icon(
                                                    vectorResource(id = R.drawable.close),
                                                    tint = Color(255, 0, 0)
                                                )
                                            }
                                        },
                                        backgroundColor = MaterialTheme.colors.primary,
                                        contentColor = Color.White,
                                        elevation = 12.dp
                                    )
                                }, bodyContent = {
                                    if (Utilss.IsReturn.value == true) {
                                        LazyColumn {
                                            if (result != null) {
                                                itemsIndexed(items = result!!) { index, result ->
                                                    ShowUsers(this@ExploreDoctorAtivity, result)
                                                }
                                            }
                                        }
                                    }
                                })


                        }

                }
            })

    }


}

    @Composable
    fun ShowUsers(context: Context, doctor: UserDoctor){
        Column(Modifier.padding(2.dp)) {
            Card(
                    modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(85.dp)
                            .absolutePadding(left = 2.dp, right = 2.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(onClick = {
                                val intent = Intent(context, ChatPatientActivity::class.java)
                                var bundle = Bundle()
                                bundle.putCharSequence("UserId", doctor.Id)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }),

                    backgroundColor = if(doctor.Status.equals("OnLine")) MaterialTheme.colors.primary
                    else MaterialTheme.colors.onPrimary
            ) {
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    val image = R.mipmap.dafault2
                    Image(imageResource(id = image), modifier = Modifier
                            .preferredHeight(80.dp)
                            .preferredWidth(80.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.absolutePadding(left = 8.dp)) {
                        Text(text = "${doctor.Name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Message Here", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

            }
        }
    }