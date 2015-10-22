package ru.badr.cosplay2.api.vk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 16:44
 */
public class VkPhoto implements Serializable {

    private long id;
    @SerializedName("album_id")
    private long albumId;
    @SerializedName("owner_id")
    private long ownerId;
    @SerializedName("photo_75")
    private String smallSrc;
    @SerializedName("photo_130")
    private String mediumSrc;
    @SerializedName("photo_604")
    private String largeSrc;
    private VkLikes likes;

    public VkLikes getLikes() {
        return likes;
    }

    public void setLikes(VkLikes likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getSmallSrc() {
        return smallSrc;
    }

    public void setSmallSrc(String smallSrc) {
        this.smallSrc = smallSrc;
    }

    public String getMediumSrc() {
        return mediumSrc;
    }

    public void setMediumSrc(String mediumSrc) {
        this.mediumSrc = mediumSrc;
    }

    public String getLargeSrc() {
        return largeSrc;
    }

    public void setLargeSrc(String largeSrc) {
        this.largeSrc = largeSrc;
    }
}
