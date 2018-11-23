package merryweather.com.tech45.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;


public class Utils {
    public static void savePermanentValue(String key, String value, Context ctx){
        SharedPreferences preferences = ctx.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        Log.d("UTILS","Saving "+key+ ": "+value);
    }

    public static String getPermanentValue(String key, Context ctx){
        String val = null;
        SharedPreferences preferences = ctx.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        val = preferences.getString(key,"");
        Log.d("UTILS","Returning "+key+ ": "+val);
        return val;
    }

    public static Bitmap GetImageFromAssets(Context context, String imagePath){
        Bitmap bmp = null;
        try {
            InputStream bitmap= context.getAssets().open(imagePath);
            bmp = BitmapFactory.decodeStream(bitmap);

        } catch (Exception e1) {
            Log.d("Application Find", e1.getMessage());
        }
        return bmp;
    }

    public static int parseInt(String number, int defaultVal) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static int parseBoolToInt(boolean torf){
        if(torf)
            return 1;
        else
            return 0;
    }


}
