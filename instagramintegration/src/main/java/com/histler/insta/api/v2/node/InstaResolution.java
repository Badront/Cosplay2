package com.histler.insta.api.v2.node;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:40.
 */
public class InstaResolution implements Serializable {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
