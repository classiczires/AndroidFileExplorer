package com.zires.androidfileexplorer.main;

import android.content.Context;

import com.zires.androidfileexplorer.util.Listener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import uk.co.markormesher.android_fab.SpeedDialMenuAdapter;
import uk.co.markormesher.android_fab.SpeedDialMenuItem;

/**
 * Created by ClassicZires on 10/27/2019.
 **/

public class FabMenuAdapter extends SpeedDialMenuAdapter {
    List<SpeedDialMenuItem> items;
    Listener.FabClickListener fabClickListener;

    public FabMenuAdapter(List<SpeedDialMenuItem> items, Listener.FabClickListener fabClickListener) {
        this.items = items;
        this.fabClickListener = fabClickListener;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @NotNull
    @Override
    public SpeedDialMenuItem getMenuItem(@NotNull Context context, int i) {
        return items.get(i);
    }

    @Override
    public boolean onMenuItemClick(int position) {
        fabClickListener.onItemClick(position);
        return super.onMenuItemClick(position);
    }
}
