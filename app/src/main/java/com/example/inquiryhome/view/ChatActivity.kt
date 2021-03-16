package com.example.inquiryhome.view

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inquiryhome.R
import com.example.inquiryhome.model.Chat.Chat
import com.example.inquiryhome.model.Chat.MessageAdapter
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.model.User.UserPacient
import com.example.inquiryhome.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*



class ChatActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRefence: DatabaseReference
    private lateinit var fragmentManager: FragmentManager
    private lateinit var Name: TextView
    private lateinit var Send: ImageButton
    private lateinit var Text_Send: EditText

    private var messageAdapter: MessageAdapter = MessageAdapter()
    var mchat: ArrayList<Chat>? = ArrayList()

    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        fragmentManager = supportFragmentManager
        Name = findViewById(R.id.username)
        Send = findViewById(R.id.btn_send)
        Text_Send = findViewById(R.id.text_send)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.stackFromEnd = true
        recyclerView!!.layoutManager = linearLayoutManager

        var bundle = intent.extras
        var UserId = bundle!!.getCharSequence("UserId")
        auth = FirebaseAuth.getInstance()

        var SenderId:String = auth.currentUser!!.uid
        SendMessage(SenderId, UserId.toString())

        databaseRefence = FirebaseDatabase.getInstance().reference
        databaseRefence.child("userspatient").child(UserId.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //var doctor: UserPacient = snapshot.getValue(UserPacient::class.java)!!
                    //Name.text = doctor.Name
                    readMessagges(SenderId, UserId.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun SendMessage(IdSender: String, Receiver: String){

        Send.setOnClickListener {
            var Message = Text_Send.text.toString()
            viewModel.SendMessage(IdSender, Receiver, Message, fragmentManager)
            Text_Send.setText("")
        }

    }

    fun readMessagges(myid: String, userid: String) {
        mchat = ArrayList()
        databaseRefence = FirebaseDatabase.getInstance().getReference("Chats")
        databaseRefence.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                mchat!!.clear()
                for (snapshot in dataSnapshot.children) {
                    val chat = snapshot.getValue(Chat::class.java)
                    if (chat!!.receiver == myid && chat.sender == userid ||
                            chat.receiver == userid && chat.sender == myid) {
                        mchat!!.add(chat)
                    }
                    messageAdapter.MessageAdapter(this@ChatActivity, mchat!!)
                    recyclerView.adapter = messageAdapter
                }
            }

            override fun onCancelled(@NonNull databaseError: DatabaseError) {}
        })
    }

}