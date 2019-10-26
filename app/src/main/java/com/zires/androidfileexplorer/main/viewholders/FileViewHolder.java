package com.zires.androidfileexplorer.main.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zires.androidfileexplorer.R;


public class FileViewHolder extends RecyclerView.ViewHolder{
    public ImageView fileIcon;
    public TextView name, format, creator, size, createdDate;


    public FileViewHolder(View view) {
        super(view);
        fileIcon = view.findViewById(R.id.item_file_icon);
        name = view.findViewById(R.id.item_file_name);
        format = view.findViewById(R.id.item_file_format);
        creator = view.findViewById(R.id.item_file_creator);
        size = view.findViewById(R.id.item_file_size);
        createdDate = view.findViewById(R.id.item_file_created_date);
    }
}
