package com.example.backgroundservice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        createChannelNotification()
    }

    private fun createChannelNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val channel = NotificationChannel(
                channelID,
                "Channel Service Example",
                NotificationManager.IMPORTANCE_HIGH)
            channel.setSound(null,null)

            val manager : NotificationManager? = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }
    //Dối tượng này sẽ được chia sẻ giữa tất cả các trường hợp của lớp, giống như một trường tĩnh trong Java.
    companion object {
        internal const val channelID: String = "channel_service_foreground"
    }

}