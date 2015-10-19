package ru.badr.cosplay2.api.cards;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:30
 */
public class ImageSize implements Serializable {
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
