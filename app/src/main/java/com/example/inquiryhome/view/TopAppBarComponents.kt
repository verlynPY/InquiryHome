package com.example.inquiryhome.view

import android.content.Context
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.inquiryhome.R
import com.example.inquiryhome.model.User.UserPacient
import com.example.inquiryhome.model.Utilss
import com.example.inquiryhome.view.ComponentView.PatientHomeView
import com.google.firebase.auth.FirebaseAuth


@Composable
    fun TopAppBar(context: Context, patient: UserPacient, firebaseAuth: FirebaseAuth){
        Scaffold(
                topBar = {
                    TopAppBar(
                            title = {
                                Text(text = "Name")
                            },

                            navigationIcon = {
                                IconButton(onClick = {  }) {
                                    Icon(Icons.Filled.Menu, tint = MaterialTheme.colors.onPrimary)
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    Utilss.SignOut(context, firebaseAuth = firebaseAuth)
                                }) {
                                    Icon(vectorResource(id = R.drawable.close), tint = Color(255, 0, 0))
                                }
                            },
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White,
                            elevation = 12.dp
                    )
                }, bodyContent = {

            PatientHomeView(context = context, patient = patient)
        })
    }