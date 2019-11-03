package com.zires.androidfileexplorer.model;

import com.zires.androidfileexplorer.util.FilesUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class Folder extends FolderInformation {
    private List<Folder> folders;
    private List<File> files;

    public Folder(String name, User creator, Date createdDate) {
        super(name, creator, createdDate);
        folders = new ArrayList<>();
        files = new ArrayList<>();
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public List<FileInformation> getAllContent() {
        List<FileInformation> all = new ArrayList<>();
        all.addAll(folders);
        all.addAll(files);
        return all;
    }

    @Override
    public String createFolder(String name) {
        try {
            for (Folder folder : folders) {
                if (folder.getName().toLowerCase().equals(name.toLowerCase()))
                    return "There is already a folder with the same name in this location.";
            }
            Folder folder = new Folder(name, this.getCreator(), this.getCreatedDate());
            this.folders.add(folder);
            return "Your folder created successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String createFile(String name) {
        try {
            File newfile = new File(name, this.getCreator());
            for (File file : files) {
                if (file.getName().toLowerCase().equals(newfile.getName().toLowerCase()))
                    return "There is already a file with the same name in this location.";
            }
            this.files.add(newfile);
            return "Your file created successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String delete(String name) {
        try {
            for (FileInformation fileInformation : getAllContent()) {
                if (fileInformation.getName().equals(name)) {
                    if (fileInformation.isFolder()){
                        folders.remove(fileInformation);
                        return "Your folder has been deleted.";
                    }else if (fileInformation.isFile()){
                        files.remove(fileInformation);
                        return "Your file has been deleted.";
                    }
                }
            }
            return "Deleting file failure.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String rename(String newName, int position) {
        try {
            for (FileInformation fileInformation : getAllContent()) {
                if (fileInformation.getName().toLowerCase().equals(newName)) {
                    return "There is already a " + fileInformation.getType() + " with the same name in this location.";
                }
            }
            getAllContent().get(position).setName(newName);
            return "Your " + getAllContent().get(position).getType() + " renamed successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public long getSize() {
        long size = 0;
        for (FileInformation fileInformation : getAllContent()) {
            size += fileInformation.getSize();
        }
        return size;
    }

    @Override
    public String getHumanReadableSize() {
        return FilesUtil.humanReadableByteCount(getSize(), true);
    }

    @Override
    public String getType() {
        return "folder";
    }

    @Override
    public boolean isFolder() {
        return true;
    }
}
