package com.zires.androidfileexplorer.model;

import com.zires.androidfileexplorer.util.CommonUtil;

import java.util.Date;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class File {
    private final String path;
    private String name;
    private String creator;
    private Date createDate;
    private String format;
    private String size;

    public File(String path) {
        this.path = path;
        java.io.File file = new java.io.File(path);
        this.name = file.getName();
        this.createDate = new Date(file.lastModified());
        this.format = path.substring(path.lastIndexOf(".")+1);
        this.size = CommonUtil.humanReadableByteCount(file.length(), true);
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
