package com.lamfee.shiftrwallpaper


import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.lamfee.shiftrwallpaper.scheduleJob

class MyStartServiceReceiver : BroadcastReceiver() {

    @TargetApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        scheduleJob(context)
    }

}