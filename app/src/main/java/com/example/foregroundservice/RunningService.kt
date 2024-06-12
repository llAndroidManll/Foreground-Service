package com.example.foregroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder


/*
* Creating a service
* */
class RunningService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}