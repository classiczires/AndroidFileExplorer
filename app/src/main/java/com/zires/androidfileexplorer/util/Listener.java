package com.zires.androidfileexplorer.util;

import android.view.View;

/**
 * Created by ClassicZires on 10/27/2019.
 **/

public interface Listener {
    interface FabClickListener {
        void onItemClick(int position);
    }

    interface RecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    interface StackChangeListener {
        void onStackChanged();
    }
}
