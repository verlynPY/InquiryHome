package com.example.inquiryhome.model.Chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.inquiryhome.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MessageAdapter: RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    companion object{
        val MSG_LEFT: Int = 0
        val MSG_RIGTH: Int = 1
    }

    private lateinit var mContext: Context
    private var mChat: ArrayList<Chat> = ArrayList()

    private lateinit var fuser: FirebaseAuth

    fun MessageAdapter(mContext: Context, mChat: ArrayList<Chat>) {
        this.mChat = mChat
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
         if (viewType == MSG_RIGTH) {
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.chat_rigth, parent, false)
            return MessageAdapter.ViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.chat_left, parent, false)
            return MessageAdapter.ViewHolder(view)
        }
    }

    override fun onBindViewHolder(@NonNull holder: MessageAdapter.ViewHolder, position: Int) {
        val chat = mChat!![position]
        holder.show_message.text = chat.message

    }

    override fun getItemCount(): Int {
        return mChat!!.size
    }
    override fun getItemViewType(position: Int): Int {
        fuser = FirebaseAuth.getInstance()
        return if (mChat!![position].sender == fuser!!.currentUser!!.uid) {
            MSG_RIGTH
        } else {
            MSG_LEFT
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var show_message: TextView
        var txt_seen: TextView? = null

        init {
            show_message = itemView.findViewById(R.id.show_message)
             //txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }
}