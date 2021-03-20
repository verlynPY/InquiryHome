package com.example.inquiryhome.model

import android.app.NotificationChannel
import android.os.Build
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.inquiryhome.R

class NotificationManager {

    private val NotificationChannel = "com.example.inquiryhome"
    private val NameChannel = "InquiryHome"
    private val NotificationId = 0

    fun ShowNotification(context: Context, Name:String, Mesagge: String){

        val builder = NotificationCompat.Builder(context, NotificationChannel)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("InquiryHome")
                .setContentText(Mesagge)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                with(NotificationManagerCompat.from(context)){
                    notify(NotificationId, builder.build())
                }

    }

}