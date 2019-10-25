package com.zires.androidfileexplorer.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.List;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class Folder {
    private String path;
    private String name;
    private String creator;
    private Date createDate;

    public Folder(String path) {
        this.path = path;
        File file = new File(path);
        this.name = file.getName();
        this.createDate = new Date(file.lastModified());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
