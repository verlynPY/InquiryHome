package com.example.inquiryhome.view.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.tooling.preview.Preview
import com.example.inquiryhome.R
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.model.User.UserPacient
import com.example.inquiryhome.model.Utilss
import com.example.inquiryhome.view.Chat.ChatPatientActivity
import com.example.inquiryhome.view.ChatActivity
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.view.ShowUsers
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeDoctorActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    var result: ArrayList<UserPacient>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        var id = firebaseAuth.currentUser!!.uid
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewmodel.ShowPatient().observe(this, Observer {
            viewmodel.ShowProfileDoctor(this, id).observe(this, Observer { doctor ->
                for (patient in it) {
                    result!!.add(patient)
                }
                setContent {
                    MaterialTheme(
                        colors = if (isSystemInDarkTheme())
                            MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = {
                                        Text(text = "${doctor.Name}")
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
                                                this@HomeDoctorActivity,
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
                                LazyColumn {
                                    itemsIndexed(items = result!!) { index, result ->
                                        ShowPatient(context = this@HomeDoctorActivity, result)
                                    }
                                }
                            })


                    }
                }
            })
        })
    }

    @Composable
    fun ShowPatient(context: Context, patient: UserPacient){
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
                                bundle.putCharSequence("UserId", patient.Id)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }),
                    backgroundColor = MaterialTheme.colors.primary
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
                        Text(text = "${patient.Name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "His problem here...", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Button(onClick = {

                    }, colors = ButtonConstants.defaultButtonColors(Color.White)) {
                        Text(text = "Consult", color = MaterialTheme.colors.primary)
                    }

                }

            }
        }
    }

}

