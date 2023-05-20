package com.example.boundserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class CalculatorService : Service() {

    private val binder = MyBinder()

    inner class MyBinder : Binder(){
        fun getService(): CalculatorService = this@CalculatorService

    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun calculateSum(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}