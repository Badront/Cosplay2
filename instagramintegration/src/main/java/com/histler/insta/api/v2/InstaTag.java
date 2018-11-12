package com.histler.insta.api.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:57.
 */
public class InstaTag implements Serializable {
    private String id;
    private String name;
    @SerializedName("edge_hashtag_to_media")
    private InstaMedia media;

    public InstaMedia getMedia() {
        return media;
    }

    public void setMedia(InstaMedia media) {
        this.media = media;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
