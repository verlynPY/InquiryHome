package com.example.inquiryhome.view.Register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.model.User.UserPacient
import com.example.inquiryhome.view.ComponentView.CircularProgress
import com.example.inquiryhome.view.Components
import com.example.inquiryhome.view.Home.HomePatientActivity
import com.example.inquiryhome.view.LoginAcitivity
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.view.buttonShape
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class PatientRegisterActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        auth = FirebaseAuth.getInstance()

        fragmentManager = supportFragmentManager
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor) {

                var Name = remember { mutableStateOf("") }
                var Last_Name = remember { mutableStateOf("") }
                var Email = remember { mutableStateOf("") }
                var Birth = remember { mutableStateOf("") }
                var Password = remember { mutableStateOf("") }

                val isActive = viewModel.isActive.value

                Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.Center) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .padding(35.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Image(imageResource(Components.imagePatient), modifier = Modifier
                                    .preferredHeight(120.dp)
                                    .preferredWidth(120.dp))
                            }
                            OutlinedTextField(value = Name.value, onValueChange = { Name.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { androidx.compose.material.Text(text = "Name") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                            )
                            OutlinedTextField(value = Last_Name.value,
                                    onValueChange = { Last_Name.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { androidx.compose.material.Text(text = "Last Name") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                            )
                            OutlinedTextField(value = Email.value, onValueChange = { Email.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { androidx.compose.material.Text(text = "Email") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                            )
                            OutlinedTextField(value = Birth.value, onValueChange = { Birth.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp),
                                    placeholder = { androidx.compose.material.Text(text = "Birth 25-10-2000") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_date)) }
                            )
                            OutlinedTextField(value = Password.value, onValueChange = { Password.value = it },
                                    inactiveColor = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp),
                                    placeholder = { androidx.compose.material.Text(text = "Password") },
                                    leadingIcon = { Icon(vectorResource(Components.ic_password)) }
                            )
                            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center){
                                     CircularProgress(Visible = isActive)
                                }
                                Button(
                                        onClick = {
                                                    val usersPatient = UserPacient("1",Name.value.toString(), Last_Name.value.toString(),
                                                        Email.value.toString(), Birth.value, Password.value.toString())
                                                        viewModel.GetPatientData(this@PatientRegisterActivity, fragmentManager, usersPatient)
                                        }, modifier = Modifier
                                        .preferredHeight(50.dp)
                                        .fillMaxWidth(),
                                        shape = buttonShape
                                ) {
                                    androidx.compose.material.Text(text = "Register", style = MaterialTheme.typography.h6)
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
                                            startActivity(Intent(this@PatientRegisterActivity, LoginAcitivity::class.java))
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

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(applicationContext, HomePatientActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
