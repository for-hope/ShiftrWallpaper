package com.lamfee.shiftrwallpaper

import android.content.Context
import android.util.SparseIntArray
import android.widget.Toast
import java.lang.reflect.Field
import java.util.ArrayList

class ShiftedWP(private val context: Context, private val wpName: String) {
    val FIRST_IMAGE = 1
    var FINAL_IMAGE = 1

    val wallpaperSet: List<*>
        get() {
            val fields = R.drawable::class.java.fields
            val drawables = ArrayList<Int>()
            val sparseIntArray = SparseIntArray()
            var key: Int
            for (field in fields) {
                if (field.name.startsWith(wpName)) {
                    try {
                        key = Integer.parseInt(getKey(field.name))
                        sparseIntArray.append(key, field.getInt(null))
                    } catch (e:IllegalAccessException) {
                        Toast.makeText(context, "Failed to import Wallpaper", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }


                }
            }

            for (i in 0..sparseIntArray.size()) {
                drawables.add(sparseIntArray.get(i))
            }
            FINAL_IMAGE = drawables.size - 1
            return drawables

        }

    private fun getKey(fieldName: String): String {
        var substring = if (fieldName.length > 2) fieldName.substring(fieldName.length - 2) else fieldName
        if (substring.startsWith("_")) {
            substring = substring.substring(1)
        }
        return substring
    }
}
