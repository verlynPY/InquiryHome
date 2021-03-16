package com.example.inquiryhome.model.Chat

import android.content.ContentValues
import android.util.Log
import androidx.annotation.NonNull
import androidx.compose.runtime.emptyContent
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.example.inquiryhome.model.DialogManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.HashMap

class ManageChat(private val fragmentManager: FragmentManager) {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    fun SendMessage(sender: String, receiver: String, message: String, date: String){
        databaseReference = FirebaseDatabase.getInstance().reference
        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("sender", sender)
        hashMap.put("receiver", receiver)
        hashMap.put("message", message)
        hashMap.put("date", date)

        databaseReference.child("Chats").push().setValue(hashMap)
            .addOnFailureListener { e ->
                Log.e(ContentValues.TAG, "Errorr $e")
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }

    }

    fun readMessagges(myid: String, userid: String): MutableLiveData<Chat>{
        var liveData = MutableLiveData<Chat>()
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val chat = snapshot.getValue(Chat::class.java)
                    if (chat!!.receiver == myid && chat.sender == userid ||
                        chat.receiver == userid && chat.sender == myid) {
                        liveData.value = chat!!
                    }
                }
            }
            override fun onCancelled(@NonNull databaseError: DatabaseError) {}
        })
        return liveData
    }

    /*fun Status(Status: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
            .child(auth.currentUser!!.uid)

        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("Status", Status)
        databaseReference.updateChildren(hashMap as Map<String, Any>)
    }*/


}