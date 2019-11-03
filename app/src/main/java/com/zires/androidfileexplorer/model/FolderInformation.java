package com.zires.androidfileexplorer.model;

import java.util.Date;
import java.util.List;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public abstract class FolderInformation extends FileInformation {

    public FolderInformation(String name, User owner, Date createdDate) {
        super(name, owner, createdDate);
    }

    public abstract String createFolder(String name);
    public abstract List<Folder> getFolders();
    public abstract List<File> getFiles();
    public abstract List<FileInformation> getAllContent();
    public abstract String createFile(String name);
    public abstract String delete(String name);
    public abstract String rename(String newName, int position);
}
