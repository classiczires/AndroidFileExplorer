package com.zires.androidfileexplorer.main;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.zires.androidfileexplorer.R;
import com.zires.androidfileexplorer.model.Folder;
import com.zires.androidfileexplorer.model.MapCurrentFolderStack;
import com.zires.androidfileexplorer.model.User;
import com.zires.androidfileexplorer.model.Volume;
import com.zires.androidfileexplorer.util.Listener;

import java.util.ArrayList;
import java.util.List;

import uk.co.markormesher.android_fab.FloatingActionButton;
import uk.co.markormesher.android_fab.SpeedDialMenuItem;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FileSystemAdapter adapter;
    TextView emptyFolderPlaceHolder;

    MapCurrentFolderStack mappingCurrentFolder;
    Listener.StackChangeListener stackChangeListener = () -> {
        if (!mappingCurrentFolder.empty()) {
            adapter.setCurrentFolder(mappingCurrentFolder.peek());
            if (mappingCurrentFolder.peek().getAllContent().size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                emptyFolderPlaceHolder.setVisibility(View.GONE);
            } else {
                emptyFolderPlaceHolder.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    };
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
        recyclerView = findViewById(R.id.mainRecyclerView);
        emptyFolderPlaceHolder = findViewById(R.id.emptyFolderPlaceHolder);
        mappingCurrentFolder = new MapCurrentFolderStack(stackChangeListener);
        initFabMenu();
        initRecycler();
    }

    private void initFabMenu() {
        List<SpeedDialMenuItem> menuItems = new ArrayList<>();
        SpeedDialMenuItem speedDialMenuItemFolder = new SpeedDialMenuItem(this, R.drawable.ic_folder_black_24dp, "New folder");
        SpeedDialMenuItem speedDialMenuItemFile = new SpeedDialMenuItem(this, R.drawable.ic_file_black_24dp, "New file");
        menuItems.add(speedDialMenuItemFolder);
        menuItems.add(speedDialMenuItemFile);

        FabMenuAdapter fabMenuAdapter = new FabMenuAdapter(menuItems, position -> {
            if (position == 0) {
                createFolder();
            } else if (position == 1) {
                Toast.makeText(this, "file", Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fabMenu = findViewById(R.id.fabManu);
        fabMenu.setSpeedDialMenuAdapter(fabMenuAdapter);
    }

    private void initRecycler() {
        adapter = new FileSystemAdapter((view, position) -> {
            if (adapter.getItem(position).isFolder()) {
                mappingCurrentFolder.push((Folder) adapter.getItem(position));
            }
        });
        recyclerView.setAdapter(adapter);

        Volume volumePhone = new Volume("Phone Storage", getRandomUser());
        volumePhone.createFile("first file");
        volumePhone.createFolder("first folder");

        mappingCurrentFolder.push(volumePhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createFolder() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.create_item_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.findViewById(R.id.createItem_Confirm).setOnClickListener(v -> {
            TextInputEditText text = dialog.findViewById(R.id.createItem_Name);
            if (!text.getText().toString().trim().isEmpty()) {
                mappingCurrentFolder.peek().createFolder(text.getText().toString().trim());
                stackChangeListener.onStackChanged();
                dialog.dismiss();
            } else
                Toast.makeText(this, "Folder name can not be empty.", Toast.LENGTH_SHORT).show();
        });
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        if (mappingCurrentFolder.size() > 0) {
            mappingCurrentFolder.pop();
        } else
            super.onBackPressed();
    }

    private User getRandomUser() {
        return new User(users[Math.round((float) Math.random() * 5)]);
    }
}
