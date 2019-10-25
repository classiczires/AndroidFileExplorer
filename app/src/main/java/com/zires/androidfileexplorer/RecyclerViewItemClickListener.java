package com.zires.androidfileexplorer;

import android.view.View;

/**
 * Created by ClassicZires on 10/26/2019.
 */
public interface RecyclerViewItemClickListener {
    void onItemClick(View view, int position);


    public interface NestedRecyclerViewItemClickListener {
        void onItemClick(View view, int recyclerPosition, int position);
    }
}
