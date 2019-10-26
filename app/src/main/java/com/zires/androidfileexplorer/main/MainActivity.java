package com.zires.androidfileexplorer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zires.androidfileexplorer.R;
import com.zires.androidfileexplorer.RecyclerViewItemClickListener;
import com.zires.androidfileexplorer.model.User;
import com.zires.androidfileexplorer.model.Volume;
import com.zires.androidfileexplorer.util.CommonUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FileSystemAdapter adapter;

    private String[] users = new String[]{
            "Masoud",
            "Reza",
            "Farbod",
            "Alireza",
            "Hamed",
            "Ali"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CommonUtil.checkForPermission(this);

        Volume volumePhone = new Volume("Phone Storage", getRandomUser());
        volumePhone.createFile("first file");
        volumePhone.createFolder("first folder");

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FileSystemAdapter(volumePhone, (view, position) -> {
            Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }

    private User getRandomUser(){
        return new User(users[Math.round((float)  Math.random()*5)]);
    }
}
