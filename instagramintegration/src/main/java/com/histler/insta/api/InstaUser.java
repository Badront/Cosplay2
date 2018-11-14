package com.histler.insta.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 16:15
 */
public class InstaUser implements Serializable {
    private String username;
    @SerializedName("profile_pic_url")
    private String profileImage;
    @SerializedName("pk")
    private String id;
    @SerializedName("full_name")
    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
