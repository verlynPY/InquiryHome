package com.example.inquiryhome.view.Home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.inquiryhome.R
import com.example.inquiryhome.model.Chat.ManageChat
import com.example.inquiryhome.model.Utilss.SignOut
import com.example.inquiryhome.view.Chat.ChatItems
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.view.StartActivity
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth


class HomePatientActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var manageChat: ManageChat
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager = supportFragmentManager
        manageChat = ManageChat(fragmentManager = fragmentManager)
        firebaseAuth = FirebaseAuth.getInstance()
        val id: String = firebaseAuth.currentUser!!.uid

        setContent{
            MaterialTheme(colors = if (isSystemInDarkTheme())
                MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colors.primary)
                    }
                }
            }
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.ShowProfile(this, id).observe(this, Observer { patient ->
            setContent {
                MaterialTheme(
                        colors = if (isSystemInDarkTheme())
                            MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                )
                {

                    com.example.inquiryhome.view.TopAppBar(context = this, patient = patient,
                            firebaseAuth = firebaseAuth)

                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        manageChat.Status("Ofline")

    }


}
