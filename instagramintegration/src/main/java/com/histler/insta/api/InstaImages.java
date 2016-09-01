package com.histler.insta.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 14:49
 */
public class InstaImages implements Serializable {
    @SerializedName("low_resolution")
    private InstaResolutionImage low;
    private InstaResolutionImage thumbnail;
    @SerializedName("standard_resolution")
    private InstaResolutionImage standard;

    public InstaResolutionImage getLow() {
        return low;
    }

    public void setLow(InstaResolutionImage low) {
        this.low = low;
    }

    public InstaResolutionImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(InstaResolutionImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public InstaResolutionImage getStandard() {
        return standard;
    }

    public void setStandard(InstaResolutionImage standard) {
        this.standard = standard;
    }
}
