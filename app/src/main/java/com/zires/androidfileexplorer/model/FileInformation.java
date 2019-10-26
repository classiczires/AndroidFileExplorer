package com.zires.androidfileexplorer.model;

import com.zires.androidfileexplorer.util.FilesUtil;

import java.util.Date;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public abstract class FileInformation {
    private String name;
    private User owner;
    private Date createdDate;

    public FileInformation(String name, User owner, Date createdDate) {
        this.name = name;
        this.owner = owner;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public abstract String getHumanReadableSize();
}
