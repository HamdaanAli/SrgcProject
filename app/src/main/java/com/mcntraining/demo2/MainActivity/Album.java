package com.mcntraining.demo2.MainActivity;

import android.view.View;

/**
 * Created by tiger on 3/7/2018.
 */

public class Album {
    private String name;

    private int thumbnail;




    public Album(String name,  int thumbnail) {
        this.name = name;

        this.thumbnail = thumbnail;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Album() {

    }




}
