package ru.badr.cosplay2.api.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 15:33
 */
public class User implements Serializable, HasId {
    private long id;
    private String title;
    @SerializedName("image_update_time")
    private String imageUpdateTime;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUpdateTime() {
        return imageUpdateTime;
    }

    public void setImageUpdateTime(String imageUpdateTime) {
        this.imageUpdateTime = imageUpdateTime;
    }
}
