package com.example.inquiryhome.view.Register

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.RegisterUsersDoctor
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.view.Components
import com.example.inquiryhome.view.FormDoctor
import com.example.inquiryhome.view.Home.HomeDoctorActivity
import com.example.inquiryhome.view.Login.LoginAcitivity
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.view.buttonShape

class DoctorRegisterActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fragmentManager: FragmentManager = supportFragmentManager
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {
                /*overridePendingTransition(0,0)
                FormDoctor(fragmentManager, this, onClick = {  }, onClick2 = {
                    startActivity(Intent(this, LoginAcitivity::class.java))
                    finish()
                })
                overridePendingTransition(0,0)*/
                var Name = remember { mutableStateOf("") }
                    var Last_Name = remember { mutableStateOf("") }
                    var Email = remember { mutableStateOf("") }
                    var Birth = remember { mutableStateOf("") }
                    var Speciality = remember { mutableStateOf("") }
                    var Squatur = remember { mutableStateOf("") }


                    Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .padding(35.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                    Image(
                                        imageResource(Components.imageDoctor), modifier = Modifier
                                            .preferredHeight(120.dp)
                                            .preferredWidth(120.dp))
                                }
                                OutlinedTextField(
                                    value = Name.value,
                                    onValueChange = { Name.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Name") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                                )
                                OutlinedTextField(value = Last_Name.value,
                                    onValueChange = { Last_Name.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Last Name") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                                )
                                OutlinedTextField(value = Email.value,
                                    onValueChange = { Email.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Email") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                                )
                                OutlinedTextField(value = Speciality.value,
                                    onValueChange = { Speciality.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Speciality") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                                )
                                OutlinedTextField(value = Birth.value,
                                    onValueChange = { Birth.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Birth 25-10-2000") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_date)) }
                                )
                                OutlinedTextField(value = Squatur.value,
                                    onValueChange = { Squatur.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { Text(text = "Squatur") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_password)) }
                                )
                                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                                    Button(
                                        onClick = {
                                            if(Name.value.toString().isNullOrEmpty() || Last_Name.value.toString().isNullOrEmpty()
                                                || Email.value.toString().isNullOrEmpty() || Birth.value.toString().isNullOrEmpty()
                                                || Speciality.value.toString().isNullOrEmpty() || Squatur.value.toString().isNullOrEmpty()
                                            ){

                                                var dialog = DialogManager("You should complete all the fields")
                                                dialog.show(fragmentManager, ContentValues.TAG)
                                            }
                                            else{

                                                try{

                                                    var Statuss = "Online"

                                                    var userDoctor: UserDoctor = UserDoctor("", Name.value.toString(),
                                                        Last_Name.value.toString(), Email.value.toString(),
                                                        Birth.value.toString(), Speciality.value.toString(), Statuss, Squatur.value.toString())
                                                    var registerUsers: RegisterUsersDoctor = RegisterUsersDoctor(fragmentManager)
                                                    registerUsers.RegisterDoctor(this@DoctorRegisterActivity, userDoctor)
                                                  //val intent = Intent(this@DoctorRegisterActivity, HomeDoctorActivity::class.java)
                                                   // startActivity(intent)
                                                    finish()

                                                }
                                                catch (e: Exception){
                                                    var dialog = DialogManager("$e")
                                                    dialog.show(fragmentManager, ContentValues.TAG)
                                                }


                                            }
                                        }, modifier = Modifier
                                            .preferredHeight(50.dp)
                                            .fillMaxWidth(),
                                        shape = buttonShape
                                    ) {
                                        Text(text = "Register", style = MaterialTheme.typography.h6)
                                    }
                                    Spacer(modifier = Modifier.preferredHeight(8.dp))
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                                        Text(
                                            text = "Already have an account? ",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0,220,220)
                                        )
                                        Text(
                                            text = "Sign In",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colors.secondary,
                                            modifier = Modifier.clickable(onClick = {
                                                startActivity(Intent(this@DoctorRegisterActivity, LoginAcitivity::class.java))
                                                finish()
                                            })
                                        )
                                    }
                                }
                            }
                        }
                    }


            }
        }
    }

}
