package com.zires.androidfileexplorer.model;

import com.zires.androidfileexplorer.util.CommonUtil;
import com.zires.androidfileexplorer.util.FilesUtil;

import java.util.Date;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class File extends FileInformation {
    private String format;
    private long size;
    private String[] fakeFileFormats = new String[]{
            "mp4",
            "mkv",
            "wav",
            "jpeg",
            "mpeg",
            "docx",
            "jpg",
            "mp3",
            "svg",
            "pdf"
    };


    public File(String name, User creator) {
        super(name, creator, new Date());
        this.format = getFakeFileFormat();
        setName(getName().concat(".").concat(format));
        this.size = (long) (Math.random() * 50000000);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    private String getFakeFileFormat() {
        return fakeFileFormats[Math.round((float) (Math.random() * 9))];
    }

    @Override
    public String getHumanReadableSize() {
        return FilesUtil.humanReadableByteCount(size, true);
    }

    @Override
    public String getType() {
        return "file";
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public long getSize() {
        return size;
    }
}
