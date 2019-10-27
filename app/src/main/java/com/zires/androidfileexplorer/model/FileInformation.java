package com.zires.androidfileexplorer.model;

import java.util.Date;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public abstract class FileInformation {
    private String name;
    private User creator;
    private Date createdDate;

    public FileInformation(String name, User creator, Date createdDate) {
        this.name = name;
        this.creator = creator;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public boolean isFile(){
        return false;
    }
    public boolean isFolder(){
        return false;
    }
    public boolean isVolume(){
        return false;
    }
    public abstract String getHumanReadableSize();
}
