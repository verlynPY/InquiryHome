package com.example.inquiryhome.view.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.MainActivity
import com.example.inquiryhome.R
import com.example.inquiryhome.view.Components
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.viewmodel.MainViewModel


class LoginAcitivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        fragmentManager = supportFragmentManager

        setContent {
            MaterialTheme(
                colors = if (isSystemInDarkTheme())
                    MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
            ) {
                val buttonShape = RoundedCornerShape(25.dp)
                val buttonModifier =
                    Modifier
                        .fillMaxWidth(0.8f)
                        .preferredHeight(60.dp)
                        .padding(6.dp)
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(240.dp)
                    .absolutePadding(left = 12.dp, top = 30.dp, right = 12.dp)
                    .clip(RoundedCornerShape(25.dp)),
                    backgroundColor = MaterialTheme.colors.primary

                ){
                        Row(modifier = Modifier.fillMaxWidth()){
                            Column(modifier = Modifier
                                .fillMaxHeight()
                                .absolutePadding(left = 8.dp), verticalArrangement = Arrangement.Center) {
                                Text(text = "InquiryHome", fontSize = 34.sp, fontWeight = FontWeight.Bold)
                                Text(text = "You can talk to a", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                                Text(text = "programming", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                                Text(text = "from anywhere", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                    Image(imageResource(id = R.mipmap.img), modifier = Modifier
                                        .preferredHeight(160.dp)
                                        .preferredWidth(160.dp))
                                }

                            }


                        }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .absolutePadding(top = 20.dp),
                        verticalArrangement = Arrangement.Center
                    ) {

                        val Email = remember { mutableStateOf("") }
                        val Password = remember { mutableStateOf("") }

                        Text(
                            text = "Login",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Start)
                        )

                        OutlinedTextField(
                            value = Email.value,
                            onValueChange = { Email.value = it },
                            inactiveColor = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(top = 10.dp),
                            placeholder = { Text(text = "Email") },
                            leadingIcon = { Icon(vectorResource(Components.ic_data)) }
                        )
                        OutlinedTextField(
                            value = Password.value,
                            onValueChange = { Password.value = it },
                            inactiveColor = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(top = 10.dp),
                            placeholder = { Text(text = "Password") },
                            leadingIcon = { Icon(vectorResource(Components.ic_password)) }
                        )
                        Spacer(modifier = Modifier.preferredHeight(30.dp))
                        Button(onClick = {
                            viewModel.LogIn(this@LoginAcitivity, fragmentManager, Email.value, Password.value)
                        }, shape = buttonShape, modifier = buttonModifier) {
                            Text(text = "Log In", style = MaterialTheme.typography.h6)
                        }
                                Text(
                                    text = "Forget your password?",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colors.secondary,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .absolutePadding(top = 8.dp)
                                )

                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .absolutePadding(bottom = 8.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(){
                        Text(
                            text = "Don't have an account? ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0,220,220)
                        )
                        Text(
                            text = "Sign up",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.secondary,
                            modifier = Modifier.clickable(onClick = {
                                startActivity(Intent(this@LoginAcitivity, MainActivity::class.java))
                                finish()
                            })
                        )
                    }


                }
            }
        }
    }
}
