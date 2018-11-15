package com.badr.cosplay2.model.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:29
 */
public class CardImage implements Serializable, Cloneable {
    @SerializedName("filename")
    private long fileName;
    private ImageSize original;
    private ImageSize large;
    private ImageSize medium;

    public CardImage() {
    }

    public CardImage(CardImage cardImage) {
        this();
        this.fileName = cardImage.fileName;
        this.original = cardImage.original != null ? new ImageSize(cardImage.original) : null;
        this.large = cardImage.large != null ? new ImageSize(cardImage.large) : null;
        this.medium = cardImage.medium != null ? new ImageSize(cardImage.medium) : null;
    }

    public long getFileName() {
        return fileName;
    }

    public void setFileName(long fileName) {
        this.fileName = fileName;
    }

    public ImageSize getOriginal() {
        return original;
    }

    public void setOriginal(ImageSize original) {
        this.original = original;
    }

    public ImageSize getLarge() {
        return large;
    }

    public void setLarge(ImageSize large) {
        this.large = large;
    }

    public ImageSize getMedium() {
        return medium;
    }

    public void setMedium(ImageSize medium) {
        this.medium = medium;
    }
}
