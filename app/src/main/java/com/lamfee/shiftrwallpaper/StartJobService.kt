package com.lamfee.shiftrwallpaper

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi

import com.lamfee.shiftrwallpaper.scheduleJob

/**
 * JobService to be scheduled by the JobScheduler.
 * start another service
 */

class StartJobService : JobService() {

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onStartJob(params: JobParameters): Boolean {
        val service = Intent(applicationContext, WallpaperShifterService::class.java)
        applicationContext.startService(service)
        scheduleJob(applicationContext) // reschedule the job
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }

    companion object {
        private val TAG = "SyncService"
    }

}
