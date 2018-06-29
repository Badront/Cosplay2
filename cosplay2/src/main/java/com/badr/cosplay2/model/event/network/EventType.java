package com.badr.cosplay2.model.event.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
public class EventType {
    private long id;
    private int order;
    @SerializedName("title_many")
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
