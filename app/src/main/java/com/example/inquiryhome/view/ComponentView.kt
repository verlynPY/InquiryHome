package com.example.inquiryhome.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inquiryhome.model.RegisterUsers
import com.example.inquiryhome.model.UserDoctor
import com.example.inquiryhome.model.Utilss.GoHome
import com.example.inquiryhome.model.Utilss.GoRegisterDoctor
import com.example.inquiryhome.model.Utilss.GoRegisterPatient
import com.example.inquiryhome.view.Components.ic_data
import com.example.inquiryhome.view.Components.ic_date
import com.example.inquiryhome.view.Components.ic_password
import com.example.inquiryhome.view.Components.imageDoctor
import com.example.inquiryhome.view.Components.imagePatient


val buttonShape = RoundedCornerShape(25.dp)
    val buttonModifier = Modifier
        .preferredHeight(65.dp)
        .preferredWidth(270.dp)
        .padding(8.dp)
    val imagemodifier = Modifier
        .preferredHeight(280.dp)
        .fillMaxWidth()
        .absolutePadding(top = 40.dp)

    @Composable
    fun GetStarted(context: Context){
        Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
            Image(imageResource(Components.imageResource), modifier = imagemodifier)
            Row(modifier = Modifier.fillMaxWidth() ,horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
                    Text(text = "Choose a Role", fontSize = 28.sp, fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                    )
                    Button(onClick = {
                        GoRegisterPatient(context)
                    }, shape = buttonShape, modifier = buttonModifier) {
                        Text(text = "Pacient", style = MaterialTheme.typography.h6)
                    }
                    Button(onClick = {
                        GoRegisterDoctor(context)
                    }, shape = buttonShape, modifier = buttonModifier) {
                        Text(text = "Doctor", style = MaterialTheme.typography.h6)
                    }
                }
            }
        }
    }

    @Composable
    fun FormDoctor(context: Context){
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
                    Image(imageResource(imageDoctor), modifier = Modifier
                        .preferredHeight(120.dp)
                        .preferredWidth(120.dp))}
                    OutlinedTextField(
                        value = Name.value, onValueChange = { Name.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Name") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Last_Name.value,
                        onValueChange = { Last_Name.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Last Name") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Email.value, onValueChange = { Email.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Email") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Speciality.value,
                        onValueChange = { Speciality.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Speciality") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Birth.value, onValueChange = { Birth.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Birth 00-00-0000") },
                        leadingIcon = { Icon(vectorResource(ic_date)) }
                    )
                    OutlinedTextField(value = Squatur.value, onValueChange = { Squatur.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Squatur") },
                        leadingIcon = { Icon(vectorResource(ic_password)) }
                    )
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                        Button(
                            onClick = {
                                /*if(Name.value.toString().isNullOrEmpty() || Last_Name.value.toString().isNullOrEmpty()
                                        || Email.value.toString().isNullOrEmpty() || Birth.value.toString().isNullOrEmpty()
                                        || Speciality.value.toString().isNullOrEmpty() || Squatur.value.toString().isNullOrEmpty()
                                ){
                                    //Error
                                }*/
                               // else{


                                    var userDoctor: UserDoctor = UserDoctor(1, Name.value.toString(), Last_Name.value.toString(), Email.value.toString(),
                                    Birth.value.toString(), Speciality.value.toString(), Squatur.value.toString())
                                    var registerUsers: RegisterUsers = RegisterUsers()
                                    registerUsers.Register(userDoctor)
                                    Toast.makeText(context, "Funciona", Toast.LENGTH_SHORT).show()
                                GoHome(context = context)
                               // }
                            }, modifier = Modifier
                                .preferredHeight(50.dp)
                                .fillMaxWidth(),
                            shape = buttonShape
                        ) {
                            Text(text = "Register", style = MaterialTheme.typography.h6)
                        }
                    }
                }
            }
        }
    }



    @Composable
    fun FormPatient(){
        var Name = remember { mutableStateOf("") }
        var Last_Name = remember { mutableStateOf("") }
        var Email = remember { mutableStateOf("") }
        var Birth = remember { mutableStateOf("") }
        var Contraseña = remember { mutableStateOf("") }

        Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .padding(35.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Image(imageResource(imagePatient), modifier = Modifier
                            .preferredHeight(120.dp)
                            .preferredWidth(120.dp))}
                    OutlinedTextField(value = Name.value, onValueChange = { Name.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Name") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Last_Name.value,
                        onValueChange = { Last_Name.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Last Name") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Email.value, onValueChange = { Email.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Email") },
                        leadingIcon = { Icon(vectorResource(ic_data)) }
                    )
                    OutlinedTextField(value = Birth.value, onValueChange = { Birth.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Birth 00-00-0000") },
                        leadingIcon = { Icon(vectorResource(ic_date)) }
                    )
                    OutlinedTextField(value = Contraseña.value, onValueChange = { Contraseña.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Contraseña") },
                        leadingIcon = { Icon(vectorResource(ic_password)) }
                    )
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                        Button(
                            onClick = {

                            }, modifier = Modifier
                                .preferredHeight(50.dp)
                                .fillMaxWidth(),
                            shape = buttonShape
                        ) {
                            Text(text = "Register", style = MaterialTheme.typography.h6)
                        }
                    }
                }
            }
        }
    }


