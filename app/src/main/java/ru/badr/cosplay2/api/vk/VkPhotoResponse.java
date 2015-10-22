package ru.badr.cosplay2.api.vk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 16:45
 */
public class VkPhotoResponse implements Serializable {
    @SerializedName("response")
    private List<VkPhoto> photos;

    public List<VkPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<VkPhoto> photos) {
        this.photos = photos;
    }
}
