package com.zires.androidfileexplorer.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import static androidx.core.app.ActivityCompat.requestPermissions;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class CommonUtil {
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1;

    public static void checkForPermission(Activity activity){
        if(ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
