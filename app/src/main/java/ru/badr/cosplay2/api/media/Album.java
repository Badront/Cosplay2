package ru.badr.cosplay2.api.media;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 12:57
 */
public class Album implements Serializable, HasId {
    private long id;
    @SerializedName("event_id")
    private long eventId;
    @SerializedName("user_title")
    private String title;
    @SerializedName("user_id")
    private Long userId;
    private String url;
    @SerializedName("time_add")
    private Date addedTime;
    private int count;
    private int likes;
    @SerializedName("recalc_time")
    private Date recalcTime;
    @SerializedName("recalc_message")
    private String recalcMessage;

    private long albumId;
    private long ownerId;
    private String thumbnailSrc;

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getThumbnailSrc() {
        return thumbnailSrc;
    }

    public void setThumbnailSrc(String thumbnailSrc) {
        this.thumbnailSrc = thumbnailSrc;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getRecalcTime() {
        return recalcTime;
    }

    public void setRecalcTime(Date recalcTime) {
        this.recalcTime = recalcTime;
    }

    public String getRecalcMessage() {
        return recalcMessage;
    }

    public void setRecalcMessage(String recalcMessage) {
        this.recalcMessage = recalcMessage;
    }
}
