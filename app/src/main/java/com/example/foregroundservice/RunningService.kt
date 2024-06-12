package com.example.foregroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


/*TODO: 1. Creating a service
*
* We are creating a Foreign Service,
* and it just needs to inherit from Service
* and inside of the service itself we now specify what kind of service that is.
* */
class RunningService: Service() {

    /*TODO: 2. Overriding onBind Function
    *
    *   You can see we need to implement the function here which is called --onBind-- which receives an intent and returns an --IBinder--
    *   What is that ?
    *       That function is used to create something called a bound service.
    *       So what you can also do with the service, is you can have one active instance and multiple components connect to that single instance
    *       and have done a stream for communication
    * */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /*TODO: 3. Overriding onStartCommand Function
    *
    *   So right now the service obviously doesn't do anything.
    *   Let's change that and first of all Define different commands that our activity or other Android components can send to the service.
    *   On the one hand we want to decide when we launch the service, when we started, and when we stop it.
    *   And we do this by overriding the --onStartCommand-- function.
    *   This is triggered whenever another Android component sends an intent to this --running-- service.
    * */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /*TODO: 5. Checking the intent action
        *
        *   Here on start command before we return something, we want to check the intent action.
        *   If the action is start, then we want to start the service.
        *   If the action is stop, then we want to stop the service
        * */
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
            else -> {
                println("No Action")
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    /*TODO: 4. Specifying an Enum Class for Actions
    * */
    enum class Actions{
        START,
        STOP
    }


    /*TODO: 6. Creating a private function to start the service
    *
    *   I said if we want to make this foreground service it needs to come with persistent notification because otherwise the user wouldn't be aware that there's a service is running.
    *   So what we need to do is we need to call --startForeground-- function, and this takes an --ID-- and a --notification--.
    *   This --ID-- corresponds to the specific notification so if you want to update another notification you would just call this function again with the same ID with the updated notification.
    *
    * */
    private fun start() {

        /*TODO: 7. Creating a notification
        *
        *   So we create that --val notification = NotificationCompat.Builder(this, "running_channel") --,
        *   and with this Builder we can now construct our custom notification and the user will then see when they swipe down their status bar.
        *   This will require the context and as I said that the service is also an Android component which inherits from Context,
        *   so for this context here we can all simply pass `this`, so this service instance (RunningService).
        *   Every service also has a normal life cycle just as an activity.
        *   It has an own create function which you can override it, and it has an own destroy function which you can override it.
        *   So this --Builder-- functions deprecated because we also need something else here and that is a `channel id`
        *
        *   --Channel ID
        *   So that corresponds to something we call notification channels on Android, which we need to create if we want to show a notification.
        **/
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("Time is 03:33")
            .build()
        startForeground(1,notification)
    }


}