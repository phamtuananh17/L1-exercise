package com.example.backgroundservice

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.backgroundservice.MyApplication.Companion.channelID


class MyService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Log.e("message", "My service onCreate")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val bundle : Bundle? = intent?.extras
        if(bundle != null) run {
            val song = bundle.getSerializable("object_song") as Song

            startMusic(song)
            sendNotification(song)
        }

        return START_NOT_STICKY

    }

    private fun startMusic(song: Song?) {
        if (song != null) {
            mediaPlayer = MediaPlayer.create(applicationContext,song.getResource())
        }

        mediaPlayer.start()
    }


    private fun sendNotification(song : Song?) {
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val bitmap : Bitmap = BitmapFactory.decodeResource(resources,song?.getImage()!!)

        val remoteViews = RemoteViews(packageName,R.layout.layout_custom_notification)
        remoteViews.setTextViewText(R.id.tv_title_song, song.getTitle())
        remoteViews.setTextViewText(R.id.tv_single_song, song.getSingle())
        remoteViews.setImageViewBitmap(R.id.img_song, bitmap)

        //remoteViews.setImageViewResource(R.id.img_play_or_pause,R.drawable.outline_pause_black_24)

        val notification : Notification = NotificationCompat.Builder(
            this,
            channelID
        ).setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(pendingIntent)
            .setCustomContentView(remoteViews)
            .setSound(null)
            .build()

        startForeground(1,notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("message", "My service onDestroy")
        mediaPlayer.release()
    }
}