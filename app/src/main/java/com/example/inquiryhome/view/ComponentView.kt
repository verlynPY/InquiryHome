package com.example.inquiryhome.view

import android.content.ContentValues.TAG
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.Register.RegisterUsersPatient
import com.example.inquiryhome.model.RegisterUsersDoctor
import com.example.inquiryhome.model.UserDoctor
import com.example.inquiryhome.model.UserPacient
import com.example.inquiryhome.model.Utilss.GoRegisterDoctor
import com.example.inquiryhome.model.Utilss.GoRegisterPatient
import com.example.inquiryhome.view.ComponentView.CircularProgress
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
    fun GetStarted2(context: Context, navController: NavController){
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
                        Text(text = "Login", style = MaterialTheme.typography.h6)
                    }
                    Button(onClick = {
                        GoRegisterDoctor(context)
                    }, shape = buttonShape, modifier = buttonModifier) {
                        Text(text = "Register", style = MaterialTheme.typography.h6)
                    }
                }
            }
        }
    }

    @Composable
    fun FormDoctor(fragmentManager: FragmentManager, context: Context){
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
                        value = Name.value,
                            onValueChange = { Name.value = it },
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
                    OutlinedTextField(value = Email.value,
                            onValueChange = { Email.value = it },
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
                    OutlinedTextField(value = Birth.value,
                            onValueChange = { Birth.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Birth 25-10-2000") },
                        leadingIcon = { Icon(vectorResource(ic_date)) }
                    )
                    OutlinedTextField(value = Squatur.value,
                            onValueChange = { Squatur.value = it },
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
                                if(Name.value.toString().isNullOrEmpty() || Last_Name.value.toString().isNullOrEmpty()
                                        || Email.value.toString().isNullOrEmpty() || Birth.value.toString().isNullOrEmpty()
                                        || Speciality.value.toString().isNullOrEmpty() || Squatur.value.toString().isNullOrEmpty()
                                ){

                                    var dialog = DialogManager("You should complete all the fields")
                                    dialog.show(fragmentManager, TAG)
                                }
                                else{

                                    try{
                                        var userDoctor: UserDoctor = UserDoctor(1, Name.value.toString(),
                                            Last_Name.value.toString(), Email.value.toString(),
                                            Birth.value.toString(), Speciality.value.toString(), Squatur.value.toString())
                                        var registerUsers: RegisterUsersDoctor = RegisterUsersDoctor(fragmentManager)
                                        registerUsers.RegisterDoctor(context, userDoctor)
                                        Toast.makeText(context, "Funciona", Toast.LENGTH_SHORT).show()
                                    }
                                    catch (e: Exception){
                                        var dialog = DialogManager("$e")
                                        dialog.show(fragmentManager, TAG)
                                    }


                                }
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
    fun FormPatient(context: Context, fragmentManager: FragmentManager){
        var Name = remember { mutableStateOf("") }
        var Last_Name = remember { mutableStateOf("") }
        var Email = remember { mutableStateOf("") }
        var Birth = remember { mutableStateOf("") }
        var Password = remember { mutableStateOf("") }

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
                        placeholder = { Text(text = "Birth 25-10-2000") },
                        leadingIcon = { Icon(vectorResource(ic_date)) }
                    )
                    OutlinedTextField(value = Password.value, onValueChange = { Password.value = it },
                        inactiveColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        placeholder = { Text(text = "Password") },
                        leadingIcon = { Icon(vectorResource(ic_password)) }
                    )
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center){
                           // CircularProgress(Visible = true)
                        }
                        Button(
                            onClick = {
                                if(Name.value.toString().isNullOrEmpty() || Last_Name.value.toString().isNullOrEmpty()
                                    || Email.value.toString().isNullOrEmpty() || Birth.value.toString().isNullOrEmpty()
                                    || Password.value.toString().isNullOrEmpty()
                                ){
                                    var dialog = DialogManager("You should complete all the fields")
                                    dialog.show(fragmentManager, TAG)
                                }
                                else{
                                    try{
                                        val registerUsersPatient = RegisterUsersPatient(fragmentManager)
                                        val usersPatient = UserPacient("1",Name.value.toString(), Last_Name.value.toString(),
                                            Email.value.toString(), Birth.value, Password.value.toString())
                                        registerUsersPatient.RegisterPatient(context, usersPatient)
                                    }
                                    catch (e: Exception){
                                        var dialog = DialogManager("$e")
                                        dialog.show(fragmentManager, TAG)
                                    }
                                }


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


