package com.zires.androidfileexplorer.main.viewholders;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zires.androidfileexplorer.R;


public class VolumeViewHolder extends RecyclerView.ViewHolder{
    public ImageView volumeIcon;
    public TextView name, creator, createdDate;


    public VolumeViewHolder(View view) {
        super(view);
        volumeIcon = view.findViewById(R.id.item_volume_icon);
        name = view.findViewById(R.id.item_volume_name);
        creator = view.findViewById(R.id.item_volume_creator);
        createdDate = view.findViewById(R.id.item_volume_created_date);
    }
}
