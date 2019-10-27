package com.zires.androidfileexplorer.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.zires.androidfileexplorer.R;

/**
 * Created by ClassicZires on 10/27/2019.
 **/

public class ViewUtil {
    public static Dialog createDialog(Context context){
        Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.create_item_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
}
