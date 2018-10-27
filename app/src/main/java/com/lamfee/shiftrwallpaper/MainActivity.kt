package com.lamfee.shiftrwallpaper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import java.io.ByteArrayOutputStream





@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    private var chosenWallpaper = "mojave"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init
        val buttonSetWallpaper = findViewById<Button>(R.id.set)
        val secondImage = findViewById<ImageView>(R.id.preview)
        val firstIMG = findViewById<ImageView>(R.id.perview2)
        //set pics

        secondImage.setImageResource(R.drawable.mojave_dynamic_2)
       // firstIMG.setImageResource(R.drawable.earth_dyn_1)

        //on click pics
        firstIMG.setOnClickListener { chosenWallpaper = "earth"
            Toast.makeText(this,chosenWallpaper,Toast.LENGTH_SHORT).show()}
        secondImage.setOnClickListener { chosenWallpaper = "mojave"
            Toast.makeText(this,chosenWallpaper,Toast.LENGTH_SHORT).show()}


        // on click button
        buttonSetWallpaper.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               makeWallpaperServiceMPlus(this,chosenWallpaper)
                Log.d("MAIN", "M PLUS")
            } else {
                Log.d("MAIN", "NOT M")
              makeWallpaperService(this, chosenWallpaper)
            }
           // finish()
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



}
