package com.histler.insta.api.v2.node;

import com.google.gson.annotations.SerializedName;
import com.histler.insta.api.v2.InstaCounter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Badr
 * on 02.09.2016 1:52.
 */
public class InstaNode implements Serializable {
    @SerializedName("shortcode")
    private String code;
    @SerializedName("dimensions")
    private InstaResolution resolution;
    private InstaUser owner;
    @SerializedName("edge_media_to_comment")
    private InstaCounter comments;
    @SerializedName("edge_liked_by")
    private InstaCounter likes;
    @SerializedName("edge_media_to_caption")
    private InstaCaptionMediaEdge descriptionHolder;
    @SerializedName("taken_at_timestamp")
    private Date createdTime;
    @SerializedName("thumbnail_src")
    private String thumbnailPath;
    @SerializedName("display_url")
    private String imagePath;
    private String id;
    @SerializedName("is_video")
    private boolean isVideo;
    @SerializedName("video_view_count")
    private int videoViews;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InstaResolution getResolution() {
        return resolution;
    }

    public void setResolution(InstaResolution resolution) {
        this.resolution = resolution;
    }

    public InstaUser getOwner() {
        return owner;
    }

    public void setOwner(InstaUser owner) {
        this.owner = owner;
    }

    public InstaCounter getComments() {
        return comments;
    }

    public void setComments(InstaCounter comments) {
        this.comments = comments;
    }

    public InstaCounter getLikes() {
        return likes;
    }

    public void setLikes(InstaCounter likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return descriptionHolder.getEdges() != null && descriptionHolder.getEdges().size() > 0 ? descriptionHolder.getEdges().get(0).getNode().getText() : null;
    }

    public InstaCaptionMediaEdge getDescriptionHolder() {
        return descriptionHolder;
    }

    public void setDescriptionHolder(InstaCaptionMediaEdge descriptionHolder) {
        this.descriptionHolder = descriptionHolder;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public int getVideoViews() {
        return videoViews;
    }

    public void setVideoViews(int videoViews) {
        this.videoViews = videoViews;
    }
}
