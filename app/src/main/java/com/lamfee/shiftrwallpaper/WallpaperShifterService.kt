package com.lamfee.shiftrwallpaper

import android.app.Service
import android.app.WallpaperManager
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import java.io.IOException
import java.util.Calendar
import java.util.Objects

class WallpaperShifterService : Service() {



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("Shifting", Objects.requireNonNull<Bundle>(intent.extras).getString("KEY1"))
        //init
        val chosenWP : String = intent.extras!!.getString("KEY1")!!
        val rightNow = Calendar.getInstance()
        val currentTime = rightNow.get(Calendar.HOUR_OF_DAY)
        //make wallpaper
        val mWallpaperManager = WallpaperManager.getInstance(this)
        try {
            mWallpaperManager.setResource(selectWallpaper(currentTime,this,chosenWP))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun selectedWallpaper(timeOfDay: Int): Int {
        var id = R.drawable.mojave_dynamic_16
        if (timeOfDay > 22) {
            id = R.drawable.mojave_dynamic_16
        } else if (timeOfDay > 20) {
            id = R.drawable.mojave_dynamic_15
        } else if (timeOfDay > 18) {
            id = R.drawable.mojave_dynamic_14
        } else if (timeOfDay > 17) {
            id = R.drawable.mojave_dynamic_13
        } else if (timeOfDay > 16) {
            id = R.drawable.mojave_dynamic_12
        } else if (timeOfDay > 15) {
            id = R.drawable.mojave_dynamic_11
        } else if (timeOfDay > 14) {
            id = R.drawable.mojave_dynamic_10
        } else if (timeOfDay > 13) {
            id = R.drawable.mojave_dynamic_9
        } else if (timeOfDay > 12) {
            id = R.drawable.mojave_dynamic_8
        } else if (timeOfDay > 11) {
            id = R.drawable.mojave_dynamic_7
        } else if (timeOfDay > 10) {
            id = R.drawable.mojave_dynamic_6
        } else if (timeOfDay > 9) {
            id = R.drawable.mojave_dynamic_5
        } else if (timeOfDay > 8) {
            id = R.drawable.mojave_dynamic_4
        } else if (timeOfDay > 6) {
            id = R.drawable.mojave_dynamic_3
        } else if (timeOfDay > 4) {
            id = R.drawable.mojave_dynamic_2
        } else if (timeOfDay > 3) {
            id = R.drawable.mojave_dynamic_1
        }
        return id
    }


}
