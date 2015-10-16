package ru.badr.cosplay2.api.instagram;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ABadretdinov
 * 25.09.2015
 * 14:47
 */
public class InstaFeed implements Serializable {
    private List<String> tags;
    @SerializedName("created_time")
    private Date createdTime;
    private InstaLikes likes;
    private InstaImages images;
    private InstaUser user;
    private String link;
    private InstaCaption caption;

    public InstaCaption getCaption() {
        return caption;
    }

    public void setCaption(InstaCaption caption) {
        this.caption = caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public InstaLikes getLikes() {
        return likes;
    }

    public void setLikes(InstaLikes likes) {
        this.likes = likes;
    }

    public InstaImages getImages() {
        return images;
    }

    public void setImages(InstaImages images) {
        this.images = images;
    }

    public InstaUser getUser() {
        return user;
    }

    public void setUser(InstaUser user) {
        this.user = user;
    }
}
