package ru.badr.cosplay2.api.cards;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:29
 */
public class CardImage implements Serializable, Cloneable {
    private long filename;
    private ImageSize original;
    private ImageSize large;
    private ImageSize medium;

    public CardImage() {
        super();
    }

    public CardImage(CardImage cardImage) {
        super();
        this.filename = cardImage.filename;
        this.original = cardImage.original != null ? new ImageSize(cardImage.original) : null;
        this.large = cardImage.large != null ? new ImageSize(cardImage.large) : null;
        this.medium = cardImage.medium != null ? new ImageSize(cardImage.medium) : null;
    }

    public long getFilename() {
        return filename;
    }

    public void setFilename(long filename) {
        this.filename = filename;
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
