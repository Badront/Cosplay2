package com.badr.cosplay2.model.cards;

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
    @SerializedName("user_id")
    private long userId;
    private String city;
    private String vk;
    private Sex sex;
    @SerializedName("friend_status")
    private String friendStatus;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public enum Sex {
        male,
        female
    }
}
