package com.zires.androidfileexplorer.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zires.androidfileexplorer.R;
import com.zires.androidfileexplorer.main.viewholders.FileViewHolder;
import com.zires.androidfileexplorer.main.viewholders.FolderViewHolder;
import com.zires.androidfileexplorer.main.viewholders.VolumeViewHolder;
import com.zires.androidfileexplorer.model.File;
import com.zires.androidfileexplorer.model.FileInformation;
import com.zires.androidfileexplorer.model.Folder;
import com.zires.androidfileexplorer.model.Volume;
import com.zires.androidfileexplorer.util.Listener;

import java.util.ArrayList;
import java.util.List;


public class FileSystemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_FILE = 0;
    private static final int VIEW_TYPE_FOLDER = 1;
    private static final int VIEW_TYPE_VOLUME = 2;
    private List<FileInformation> allContent;
    private Listener.RecyclerViewItemClickListener mClickListener;


    public FileSystemAdapter(Listener.RecyclerViewItemClickListener clickListener) {
        this.allContent = new ArrayList<>();
        this.mClickListener = clickListener;
    }

    public void setCurrentFolder(Folder folder){
        this.allContent.clear();
        this.allContent.addAll(folder.getFolders());
        this.allContent.addAll(folder.getFiles());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (allContent.get(position).isVolume())
            return VIEW_TYPE_VOLUME;
        else if (allContent.get(position).isFolder())
            return VIEW_TYPE_FOLDER;
        else
            return VIEW_TYPE_FILE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FILE) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_file, parent, false);
            return new FileViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_FOLDER) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_folder, parent, false);
            return new FolderViewHolder(itemView);
        } else {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_volume, parent, false);
            return new VolumeViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> mClickListener.onItemClick(v, position));
        holder.itemView.setOnLongClickListener(v -> {
            mClickListener.onItemLongClick(v, position);
            return true;
        });
        if (holder instanceof FileViewHolder) {
            FileViewHolder fileViewHolder = (FileViewHolder) holder;
            File file = (File) allContent.get(position);

            fileViewHolder.name.setText(file.getName());
            fileViewHolder.creator.setText(file.getCreator().getName());
            fileViewHolder.size.setText(file.getHumanReadableSize());
            fileViewHolder.format.setText(file.getFormat());
            fileViewHolder.createdDate.setText(file.getCreatedDate().toString());
        } else if (holder instanceof FolderViewHolder) {
            FolderViewHolder folderViewHolder = (FolderViewHolder) holder;
            Folder folder = (Folder) allContent.get(position);

            folderViewHolder.name.setText(folder.getName());
            folderViewHolder.creator.setText(folder.getCreator().getName());
            folderViewHolder.createdDate.setText(folder.getCreatedDate().toString());
        } else if (holder instanceof VolumeViewHolder) {
            VolumeViewHolder volumeViewHolder = (VolumeViewHolder) holder;
            Volume volume = (Volume) allContent.get(position);

            volumeViewHolder.name.setText(volume.getName());
            volumeViewHolder.creator.setText(volume.getCreator().getName());
            volumeViewHolder.createdDate.setText(volume.getCreatedDate().toString());
        }
    }

    @Override
    public int getItemCount() {
        return allContent.size();
    }

    public FileInformation getItem(int position){
        return allContent.get(position);
    }
}
