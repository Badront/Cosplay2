package com.histler.insta.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 15:10
 */
public class InstaResult implements Serializable {
    private InstaPagination pagination;
    @SerializedName("data")
    private List<InstaFeed> feeds;

    public InstaPagination getPagination() {
        return pagination;
    }

    public void setPagination(InstaPagination pagination) {
        this.pagination = pagination;
    }

    public List<InstaFeed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<InstaFeed> feeds) {
        this.feeds = feeds;
    }
}
