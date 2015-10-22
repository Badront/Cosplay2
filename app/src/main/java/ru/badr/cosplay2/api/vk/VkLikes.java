package ru.badr.cosplay2.api.vk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 17:17
 */
public class VkLikes implements Serializable {
    private int count;
    @SerializedName("user_likes")
    private int userLikes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(int userLikes) {
        this.userLikes = userLikes;
    }
}
