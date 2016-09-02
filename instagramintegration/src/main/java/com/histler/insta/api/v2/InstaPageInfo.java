package com.histler.insta.api.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:36.
 */
public class InstaPageInfo implements Serializable {
    @SerializedName("start_cursor")
    private String startId;
    @SerializedName("end_cursor")
    private String nextPageTag;
    @SerializedName("has_next_page")
    private boolean nextPageAvailable;

    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getNextPageTag() {
        return nextPageTag;
    }

    public void setNextPageTag(String nextPageTag) {
        this.nextPageTag = nextPageTag;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public void setNextPageAvailable(boolean nextPageAvailable) {
        this.nextPageAvailable = nextPageAvailable;
    }
}
