package com.histler.insta.api.v2;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:59.
 */
public class InstaSecondResult implements Serializable {
    private String status;
    //private InstaMedia media;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public InstaMedia getMedia() {
        return media;
    }

    public void setMedia(InstaMedia media) {
        this.media = media;
    }*/
}
