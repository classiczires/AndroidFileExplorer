package com.zires.androidfileexplorer.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

import static com.zires.androidfileexplorer.util.ViewUtil.createDialog;

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
            runLayoutAnimation(recyclerView);
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
                createFile();
            }
        });
        FloatingActionButton fabMenu = findViewById(R.id.fabManu);
        fabMenu.setSpeedDialMenuAdapter(fabMenuAdapter);
    }

    private void initRecycler() {
        adapter = new FileSystemAdapter(new Listener.RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (adapter.getItem(position).isFolder()) {
                    mappingCurrentFolder.push((Folder) adapter.getItem(position));
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.inflate(R.menu.popup_menu);
                if (mappingCurrentFolder.peek().getAllContent().get(position).isFile())
                    popup.getMenu().removeItem(R.id.popup_folder_size);
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.popup_rename:
                            rename(position);
                            return true;
                        case R.id.popup_delete:
                            delete(position);
                            return true;
                        case R.id.popup_folder_size:
                            showFolderSize(position);
                            return true;
                        default:
                            return false;
                    }
                });
                popup.show();
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
        Dialog dialog = createDialog(this);
        TextInputLayout name = dialog.findViewById(R.id.createItem_NameContiner);
        name.setHint("Enter folder name");
        dialog.findViewById(R.id.createItem_Confirm).setOnClickListener(v -> {
            TextInputEditText text = dialog.findViewById(R.id.createItem_Name);
            String message;
            if (!text.getText().toString().trim().isEmpty()) {
                message = mappingCurrentFolder.peek().createFolder(text.getText().toString().trim());
                stackChangeListener.onStackChanged();
                dialog.dismiss();
            } else
                message = "Folder name can not be empty.";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
        dialog.show();

    }

    private void createFile() {
        Dialog dialog = createDialog(this);
        TextInputLayout name = dialog.findViewById(R.id.createItem_NameContiner);
        name.setHint("Enter file name");
        dialog.findViewById(R.id.createItem_Confirm).setOnClickListener(v -> {
            TextInputEditText text = dialog.findViewById(R.id.createItem_Name);
            String message;
            if (!text.getText().toString().trim().isEmpty()) {
                message = mappingCurrentFolder.peek().createFile(text.getText().toString().trim());
                stackChangeListener.onStackChanged();
                dialog.dismiss();
            } else
                message = "File name can not be empty.";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
        dialog.show();
    }

    private void rename(int position) {
        Dialog dialog = createDialog(MainActivity.this);
        Button confirm = dialog.findViewById(R.id.createItem_Confirm);
        confirm.setText("Rename");
        TextInputEditText name = dialog.findViewById(R.id.createItem_Name);
        name.setText(mappingCurrentFolder.peek().getAllContent().get(position).getName());
        confirm.setOnClickListener(v -> {
            String message;
            if (!name.getText().toString().trim().isEmpty()) {
                message = mappingCurrentFolder.peek().rename(name.getText().toString().trim(), position);
                stackChangeListener.onStackChanged();
                dialog.dismiss();
            } else
                message = "Name can not be empty.";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
        dialog.show();
    }

    private void delete(int position) {
        String message;
        message = mappingCurrentFolder.peek().delete(mappingCurrentFolder.peek().getAllContent().get(position).getName());
        stackChangeListener.onStackChanged();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showFolderSize(int position) {
        String message;
        message = mappingCurrentFolder.peek().getAllContent().get(position).getHumanReadableSize();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (!mappingCurrentFolder.empty() && mappingCurrentFolder.size() > 0) {
            mappingCurrentFolder.pop();
        } else
            super.onBackPressed();
    }

    private void runLayoutAnimation(RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private User getRandomUser() {
        return new User(users[Math.round((float) Math.random() * 5)]);
    }
}
