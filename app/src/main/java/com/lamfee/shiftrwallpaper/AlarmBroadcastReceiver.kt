package com.lamfee.shiftrwallpaper


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "Alarm triggered")
        val chosenWallpaper:String = intent.extras!!.getString("KEY1")!!
        makeWallpaperService(context,chosenWallpaper)
    }


}