package ru.badr.cosplay2.api.instagram;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 15:11
 */
public class InstaPagination implements Serializable {
    @SerializedName("next_max_tag_id")
    private Long nextMaxTagId;
    @SerializedName("next_url")
    private String nextUrl;

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public Long getNextMaxTagId() {
        return nextMaxTagId;
    }

    public void setNextMaxTagId(Long nextMaxTagId) {
        this.nextMaxTagId = nextMaxTagId;
    }
}
