package com.zires.androidfileexplorer.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zires.androidfileexplorer.R;
import com.zires.androidfileexplorer.RecyclerViewItemClickListener;
import com.zires.androidfileexplorer.model.Folder;


public class FileSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_FILE = 0;
    private static final int VIEW_TYPE_FOLDER = 1;
    private RecyclerViewItemClickListener mClickListener;
    private Folder folder;


    public FileSystemAdapter(List<T> folder, RecyclerViewItemClickListener clickListener) {
        this.folder = folder;
        this.mClickListener = clickListener;
    }

    public Folder getFolder() {
        return folder;
    }

    @Override
    public int getItemViewType(int position) {
        return ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FILE) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_folder, parent, false);
            return new ListViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_FOLDER) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_file, parent, false);
            return new GridViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setOnClickListener(v -> mClickListener.onItemClick(v, position));


            if (holder instanceof ListViewHolder) {
            } else if (holder instanceof GridViewHolder) {
            }
    }


    @Override
    public int getItemCount() {
        return .size();
    }
}
