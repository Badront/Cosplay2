package com.histler.insta.api.v2.node;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Badr
 * on 02.09.2016 1:41.
 */
public class InstaUser implements Serializable {
    private String id;
    private String username;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("profile_pic_url")
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstaUser instaUser = (InstaUser) o;

        return id != null ? id.equals(instaUser.id) : instaUser.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
