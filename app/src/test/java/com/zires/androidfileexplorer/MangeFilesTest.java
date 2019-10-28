package com.zires.androidfileexplorer;

import com.zires.androidfileexplorer.model.File;
import com.zires.androidfileexplorer.model.Folder;
import com.zires.androidfileexplorer.model.User;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ClassicZires on 10/29/2019.
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MangeFilesTest {
    private Folder folder;
    private String fileName = "file";

    @Before
    public void setUp() throws Exception {
        User user = new User("classiczires");
        folder = new Folder("RootFolder", user, new Date());
    }

    @Test
    public void firstCreateFileThenRenameTheFileAndFinallyDeleteTheFile() {
        //create
        folder.createFile(fileName);
        assertEquals(folder.getFiles().get(0).getName(),
                fileName.concat(".").concat(folder.getFiles().get(0).getFormat()));

        //rename
        fileName = "newFileName";
        folder.rename(fileName, 0);
        assertEquals(folder.getFiles().get(0).getName(), fileName);

        //delete
        folder.delete(fileName);
        assertEquals(folder.getFiles().size(), 0);
    }
}
