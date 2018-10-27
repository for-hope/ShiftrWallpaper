package com.lamfee.shiftrwallpaper
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import java.util.*

fun makeWallpaperServiceAlarm(context: Context, chosenWallpaper: String) {
 //   val HOUR_OF_DAY = 1000*60*60
   // val HOUR_AND_HALF = 1000*60*90
    fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start
    // will return an `Int` between 0 and 10 (incl.)
    val aMinute = 1000*60
    val randomAddedTime = (aMinute..(aMinute * 15)).random()
    val nextAlarm = if (Calendar.getInstance().get(Calendar.MINUTE) >= 30){
        (1000 * 60 * 30) + randomAddedTime
    } else {
        1000*60*60 + randomAddedTime
    }
    Log.d("ALARM3", "added time alarm $nextAlarm and $randomAddedTime")
    val nextAlarmInHours = nextAlarm / 1000 / 60
    Toast.makeText(context,"Wallpaper will change after $nextAlarmInHours minutes", Toast.LENGTH_SHORT).show()
    //init alarm manager.
    val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    // Intent to start the Broadcast Receiver
    val broadcastIntent = Intent(context, AlarmBroadcastReceiver::class.java)
    broadcastIntent.putExtra("KEY1", chosenWallpaper)
    // The Pending Intent to pass in AlarmManager
    val pIntent = PendingIntent.getBroadcast(context,0,broadcastIntent,0)
    alarmMgr.set(
        AlarmManager.RTC_WAKEUP,
        System.currentTimeMillis() + nextAlarm,
        pIntent
    )
    // stop here
}

//Function to select wallpapers through the day
fun selectWallpaper(timeOfDay: Int, context: Context,chosenWP: String): Int {
    var imgKey = 1
    val shiftedWP = ShiftedWP(context, chosenWP)
    shiftedWP.wallpaperSet
    var id = shiftedWP.wallpaperSet[0] as Int
    for (i in 0..22) {
        when {
            i >= 22 -> imgKey = shiftedWP.FINAL_IMAGE
            i > 20 -> imgKey = 15
            i > 8 -> imgKey = i - 5
            i > 6 -> imgKey = 3
            i > 4 -> imgKey = 2
        }
        Log.d("ImgKEY", "" + imgKey)
        if (i == timeOfDay) {
            try {
                id = shiftedWP.wallpaperSet[imgKey] as Int
            } catch (e:IndexOutOfBoundsException) {
                e.printStackTrace()
            }

        }
    }
    return id
}

//Apply wallpaper for android M -
fun makeWallpaperService(context: Context,chosenWallpaper: String) {
    val i = Intent(context, WallpaperShifterService::class.java)
    i.putExtra("KEY1", chosenWallpaper)
    context.startService(i)
    makeWallpaperServiceAlarm(context,chosenWallpaper)
}

//Apply wallpaper for android M +
@TargetApi(Build.VERSION_CODES.M)
 fun makeWallpaperServiceMPlus(context: Context, chosenWallpaper: String) {
    scheduleJob(context)
    // use this to start and trigger a service
    val i = Intent(context, WallpaperShifterService::class.java)
    // potentially add data to the intent
    i.putExtra("KEY1", chosenWallpaper)
    context.startService(i)
}
//sheduleJob for android M +
@RequiresApi(api = Build.VERSION_CODES.M)
fun scheduleJob(context: Context) {
    @SuppressLint("JobSchedulerService") val serviceComponent = ComponentName(context, StartJobService::class.java)
    val builder = JobInfo.Builder(0, serviceComponent)
    builder.setMinimumLatency((60 * 60 * 1000).toLong()) // wait at least
    builder.setOverrideDeadline((90 * 60 * 1000).toLong()) // maximum delay
    //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
    //builder.setRequiresDeviceIdle(true); // device should be idle
    //builder.setRequiresCharging(false); // we don't care if the device is charging or not
    val jobScheduler = context.getSystemService(JobScheduler::class.java)
    jobScheduler.schedule(builder.build())
}