package com.zires.androidfileexplorer;

import com.zires.androidfileexplorer.model.Folder;
import com.zires.androidfileexplorer.model.User;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by ClassicZires on 10/29/2019.
 **/

public class MangeFilesTest {
    private Folder folder;
    private String folderName = "ZiresFolder";
    private String fileName = "file";

    @Before
    public void setUp() throws Exception {
        User user = new User("classiczires");
        folder = new Folder("RootFolder", user, new Date());
    }

    @Test
    public void createRenameDeleteFile() {
        folder.createFile(fileName);
        assertEquals(folder.getFiles().get(0).getName(),
                fileName.concat(".").concat(folder.getFiles().get(0).getFormat()));

        fileName = "newFileName";
        folder.rename(fileName, 0);
        assertEquals(folder.getFiles().get(0).getName(), fileName);

        folder.delete(fileName);
        assertEquals(folder.getFiles().size(), 0);
    }

    @Test
    public void createRenameDeleteFolder() {
        //folder
        folder.createFolder(folderName);
        assertEquals(folder.getFolders().get(0).getName(), folderName);

        folderName = "newFolderName";
        folder.rename(folderName, 0);
        assertEquals(folder.getFolders().get(0).getName(), folderName);

        folder.delete(folderName);
        assertEquals(folder.getFolders().size(), 0);
    }

    @Test
    public void calculateFolderSize() {
        long folderSize = 0;
        folder.createFolder("folder1");

        folder.getFolders().get(0).createFile("file0");
        folderSize += folder.getFolders().get(0).getFiles().get(0).getSize();
        folder.createFile("file1");
        folderSize += folder.getFiles().get(0).getSize();
        folder.createFile("file2");
        folderSize += folder.getFiles().get(1).getSize();

        assertEquals(folder.getSize(), folderSize);
    }
}
