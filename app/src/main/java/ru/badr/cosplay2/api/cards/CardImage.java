package ru.badr.cosplay2.api.cards;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:29
 */
public class CardImage implements Serializable {
    private long filename;
    private ImageSize original;
    private ImageSize large;
    private ImageSize medium;

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
