package ru.badr.cosplay2.api.vk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 14:14
 */
public class VkAlbumsResponse implements Serializable {
    @SerializedName("response")
    private VkAlbumsHolder albumHolder;

    public VkAlbumsHolder getAlbumHolder() {
        return albumHolder;
    }

    public void setAlbumHolder(VkAlbumsHolder albumHolder) {
        this.albumHolder = albumHolder;
    }
}
