package ru.badr.cosplay2.api.vk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 14:12
 */
public class VkAlbum implements Serializable, HasId {

    private long id;
    private List<VkSize> sizes;
    @SerializedName("owner_id")
    private long ownerId;
    private String title;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VkSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<VkSize> sizes) {
        this.sizes = sizes;
    }
}
