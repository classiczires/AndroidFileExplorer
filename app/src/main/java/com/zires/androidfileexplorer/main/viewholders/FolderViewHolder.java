package com.zires.androidfileexplorer.main.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zires.androidfileexplorer.R;


public class FolderViewHolder extends RecyclerView.ViewHolder{
    public ImageView folderIcon;
    public TextView name, creator, createdDate;


    public FolderViewHolder(View view) {
        super(view);
        folderIcon = view.findViewById(R.id.item_folder_icon);
        name = view.findViewById(R.id.item_folder_name);
        creator = view.findViewById(R.id.item_folder_creator);
        createdDate = view.findViewById(R.id.item_folder_created_date);
    }
}
