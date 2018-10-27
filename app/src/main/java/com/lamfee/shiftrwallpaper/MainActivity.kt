package com.lamfee.shiftrwallpaper
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import java.io.ByteArrayOutputStream





@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    private var chosenWallpaper = "None"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSetWallpaper = findViewById<Button>(R.id.set)
        val secondImage = findViewById<ImageView>(R.id.preview)
        val firstIMG = findViewById<ImageView>(R.id.perview2)
        secondImage.setImageResource(R.drawable.mojave_dynamic_2)
        firstIMG.setImageResource(R.drawable.earth_dyn_1)
        firstIMG.setOnClickListener { chosenWallpaper = "earth"
            Toast.makeText(this,chosenWallpaper,Toast.LENGTH_SHORT).show()}
        secondImage.setOnClickListener { chosenWallpaper = "mojave"
            Toast.makeText(this,chosenWallpaper,Toast.LENGTH_SHORT).show()}



        buttonSetWallpaper.setOnClickListener{
            makeWallpaperService()
        }




    }

    private fun getImageUri(inImage: Bitmap, inContext: Context): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage, "Title", null
        )
        return Uri.parse(path)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun makeWallpaperService() {
        Util.scheduleJob(this)
        // use this to start and trigger a service
        val i = Intent(applicationContext, WallpaperShifterService::class.java)
        // potentially add data to the intent
        i.putExtra("KEY1", chosenWallpaper)
        applicationContext.startService(i)

        finish()
        //setWallpaper()

    }
}
