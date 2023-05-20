package com.example.backgroundservice

import android.Manifest.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edText: EditText
    private lateinit var btnStart : Button
    private lateinit var btnStop : Button

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(this, permission.POST_NOTIFICATIONS) -> {

            }
            else -> {
                registerForActivityResult(ActivityResultContracts.RequestPermission()) {

                }
                    .launch(permission.POST_NOTIFICATIONS)
            }
        }
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)

        btnStop.setOnClickListener {

            clickStopService()
        }

        btnStart.setOnClickListener {

            clickStartService()
        }
    }

    private fun clickStopService() {
        val intent  = Intent(this, MyService::class.java)
        stopService(intent)
    }

    private fun clickStartService() {
        val song = Song("Thoi em dung di", "MCK ft Trung Tran",R.drawable.mck,R.raw.thoiemdungdi)
        val intent  = Intent(this, MyService::class.java)
        val bundle = Bundle().apply {
            putSerializable("object_song",song)
        }

        intent.putExtras(bundle)
        startService(intent)
    }
}