package com.badr.cosplay2.model.cards;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:30
 */
public class ImageSize implements Serializable {
    private int width;
    private int height;

    public ImageSize() {
        super();
    }

    public ImageSize(ImageSize imageSize) {
        super();
        /*we do not trust clones*/
        this.width = imageSize.width;
        this.height = imageSize.height;
    }

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
