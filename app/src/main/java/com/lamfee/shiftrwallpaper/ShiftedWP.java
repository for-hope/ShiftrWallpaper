package com.lamfee.shiftrwallpaper;

import android.content.Context;
import android.util.SparseIntArray;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ShiftedWP {

    private Context context;
    private String wpName;
    public final  int FIRST_IMAGE = 1;
    public int FINAL_IMAGE = 1;

    public ShiftedWP(Context context, String wpName) {
        this.context = context;
        this.wpName = wpName;
        }

     public List getWallpaperSet() {
         Field[] fields = R.drawable.class.getFields();
         List<Integer> drawables = new ArrayList<>();
         SparseIntArray sparseIntArray = new SparseIntArray();
         int key;
             for (Field field : fields) {
                 if (field.getName().startsWith(wpName)) {
                     try {
                         key = Integer.parseInt(getKey(field.getName()));
                         sparseIntArray.append(key,field.getInt(null));
                     } catch (IllegalAccessException e) {
                         Toast.makeText(context, "Failed to import Wallpaper", Toast.LENGTH_SHORT).show();
                         e.printStackTrace();
                     }


                 }
             }

         for (int i = 0; i<=sparseIntArray.size();i++){
             drawables.add(sparseIntArray.get(i));
         }
         FINAL_IMAGE = drawables.size() - 1;
         return drawables;

         }
   private String getKey(String fieldName) {
       String str = fieldName;
       String substring = str.length() > 2 ? str.substring(str.length() - 2) : str;
       if(substring.startsWith("_")) {
           substring = substring.substring(1);
       }
       return substring;
   }
   public String getWpName() {
        return wpName;
   }
}
