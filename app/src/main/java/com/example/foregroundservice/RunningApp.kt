package com.example.foregroundservice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/*TODO: 8. Creating Application class
* */
class RunningApp : Application() {

    /*TODO: 9. Overriding onCreate method
    *
    *   Check if is our application running on Android Oreo,
    *   and if it is running, we are going to create a notification channel.
    **/

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "running_channel",
                "Running Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

}