package com.example.inquiryhome.view.Chat

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.inquiryhome.model.Chat.Chat
import com.example.inquiryhome.model.StatusProgress.ChatValue
import com.example.inquiryhome.model.Utilss
import com.example.inquiryhome.view.MaterialThemeColors
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.delay

class ChatPatientActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRefence: DatabaseReference
    private lateinit var fragmentManager: FragmentManager
    var mchat: ArrayList<Chat>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        fragmentManager = supportFragmentManager

        var bundle = intent.extras
        var UserId = bundle!!.getCharSequence("UserId")
        auth = FirebaseAuth.getInstance()
        var myId = auth.currentUser!!.uid

        Log.e(TAG, "MyId: ${myId}")
        Log.e(TAG, "UserId: ${UserId}")


            viewModel.GettingMessages(myId, UserId.toString(), fragmentManager)
                    .observe(this, Observer {
                        mchat!!.add(it)
                        ChatValue.value = true
                        Log.e(TAG, "Esto se lanzo")

                        setContent {


                            MaterialTheme(
                                    colors = if (isSystemInDarkTheme())
                                        MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
                            ) {
                                Scaffold(
                                        topBar = {
                                            TopAppBar(
                                                    title = {
                                                        Text(text = "Name")
                                                    },
                                                    navigationIcon = {
                                                        IconButton(onClick = {
                                                            finish()
                                                        }) {
                                                            Icon(
                                                                    Icons.Filled.ArrowBack,
                                                                    tint = Color(200, 200, 200)
                                                            )
                                                        }
                                                    },
                                                    backgroundColor = MaterialTheme.colors.primary,
                                                    contentColor = Color.White,
                                                    elevation = 12.dp
                                            )
                                        }, bodyContent = {

                                    if (ChatValue.value == true) {
                                        Column(
                                                modifier = Modifier.fillMaxHeight(0.9f),
                                                verticalArrangement = Arrangement.Bottom
                                        )
                                        {
                                            ChatItems(myId, chat = mchat!!)
                                        }
                                    }

                                    Column(
                                            modifier = Modifier.fillMaxHeight(),
                                            verticalArrangement = Arrangement.Bottom
                                    )
                                    {
                                        val message = remember { mutableStateOf("") }
                                        OutlinedTextField(
                                                value = message.value,
                                                onValueChange = { message.value = it },
                                                modifier = Modifier.fillMaxWidth(),
                                                inactiveColor = MaterialTheme.colors.primary,
                                                trailingIcon = {
                                                    IconButton(onClick = {
                                                        mchat!!.clear()
                                                        viewModel.SendMessage(
                                                                myId,
                                                                UserId.toString(),
                                                                message.value,
                                                                fragmentManager
                                                        )
                                                        message.value = ""
                                                    }) {
                                                        Icon(
                                                                Icons.Filled.Send,
                                                                tint = MaterialTheme.colors.primary
                                                        )
                                                    }
                                                }
                                        )
                                    }
                                })
                            }

                        }
                    })

    }
}
