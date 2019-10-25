package com.zires.androidfileexplorer.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zires.androidfileexplorer.R;
import com.zires.androidfileexplorer.util.CommonUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtil.checkForPermission(this);
    }

}
