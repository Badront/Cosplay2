package ru.badr.cosplay2.api.instagram;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 14:49
 */
public class InstaResolutionImage implements Serializable {
    private String url;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
