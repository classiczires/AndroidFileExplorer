package com.zires.androidfileexplorer.util;

import com.zires.androidfileexplorer.model.Folder;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class FilesUtil {

    public static long folderSize(Folder folder) {
        long length = folder.getFilesSize();
        for (Folder inFolder: folder.getFolders()) {
            length += inFolder.getFilesSize();
            folderSize(folder);
        }
        return length;
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
