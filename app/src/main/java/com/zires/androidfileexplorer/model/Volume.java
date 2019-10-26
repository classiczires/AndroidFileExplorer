package com.zires.androidfileexplorer.model;

import java.util.Date;

/**
 * Created by ClassicZires on 10/26/2019.
 **/

public class Volume extends Folder {
    public Volume(String name, User creator) {
        super(name, creator, new Date());
    }

    @Override
    public boolean isVolume() {
        return true;
    }
}
