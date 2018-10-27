package com.lamfee.shiftrwallpaper;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;


@SuppressLint("Registered")
public class WallpaperShifterService extends Service {
    private String choosenWP;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Shifitng",Objects.requireNonNull(intent.getExtras()).getString("KEY1"));
        choosenWP = intent.getExtras().getString("KEY1");
        Calendar rightNow = Calendar.getInstance();
        int currentTime = rightNow.get(Calendar.HOUR_OF_DAY);
        Log.d("TAG", "L" + selectWallpaper(currentTime));
        WallpaperManager mWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            mWallpaperManager.setResource(selectWallpaper(currentTime));
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, "error" + selectedWallpaper(currentTime), Toast.LENGTH_SHORT).show();
        }

        //TODO do something useful
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private int selectedWallpaper(int timeOfDay) {
        int id=R.drawable.mojave_dynamic_16;
        if (timeOfDay > 22) {
            id =R.drawable.mojave_dynamic_16;
            } else if (timeOfDay > 20 ) {
            id=R.drawable.mojave_dynamic_15;
            } else if (timeOfDay > 18) {
            id = R.drawable.mojave_dynamic_14;
            } else if (timeOfDay > 17) {
            id= R.drawable.mojave_dynamic_13;
            } else if(timeOfDay > 16) {
            id = R.drawable.mojave_dynamic_12;
            } else if (timeOfDay > 15 ) {
            id=R.drawable.mojave_dynamic_11;
            } else if (timeOfDay > 14) {
            id = R.drawable.mojave_dynamic_10;
            } else if (timeOfDay > 13) {
            id= R.drawable.mojave_dynamic_9;
            } else if(timeOfDay > 12) {
            id = R.drawable.mojave_dynamic_8;
            } else if(timeOfDay > 11) {
            id = R.drawable.mojave_dynamic_7;
            } else if(timeOfDay > 10) {
            id = R.drawable.mojave_dynamic_6;
            } else if(timeOfDay > 9) {
            id = R.drawable.mojave_dynamic_5;
            } else if(timeOfDay > 8) {
            id = R.drawable.mojave_dynamic_4;
            } else if(timeOfDay > 6) {
            id = R.drawable.mojave_dynamic_3;
            } else if(timeOfDay > 4) {
            id = R.drawable.mojave_dynamic_2;
            } else if(timeOfDay > 3) {
            id = R.drawable.mojave_dynamic_1;
            }
            return  id;
    }
    private int selectWallpaper(int timeOfDay) {

        int imgKey = 1;
        ShiftedWP shiftedWP = new ShiftedWP(this,choosenWP);
        shiftedWP.getWallpaperSet();
        int id = (int) shiftedWP.getWallpaperSet().get(0);
        for (int i =0; i<=22; i++) {
            if (i >= 22){
                imgKey = shiftedWP.FINAL_IMAGE;
            } else if(i > 20) {
                imgKey = 15;
            } else if (i>8) {
                imgKey = i - 5;
            } else if (i > 6) {
                imgKey = 3;
            } else if (i > 4) {
                imgKey = 2;
            }
            Log.d("ImgKEY", "" + imgKey);
           if (i == timeOfDay) {

                id = (int) shiftedWP.getWallpaperSet().get(imgKey);
            }

        }
        //id = R.drawable.mojave_dynamic_1;

        return id;
    }
}
