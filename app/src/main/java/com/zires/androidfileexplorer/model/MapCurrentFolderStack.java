package com.zires.androidfileexplorer.model;

import com.zires.androidfileexplorer.util.Listener;

import java.util.Stack;

/**
 * Created by ClassicZires on 10/27/2019.
 **/

public class MapCurrentFolderStack extends Stack<Folder> {
    Listener.StackChangeListener changeListener;

    public MapCurrentFolderStack(Listener.StackChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    @Override
    public Folder push(Folder item) {
        Folder folder = super.push(item);
        changeListener.onStackChanged();
        return folder;
    }

    @Override
    public synchronized Folder pop() {
        Folder folder = super.pop();
        changeListener.onStackChanged();
        return folder;
    }
}
