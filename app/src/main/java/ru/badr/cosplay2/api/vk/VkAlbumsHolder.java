package ru.badr.cosplay2.api.vk;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 17:12
 */
public class VkAlbumsHolder implements Serializable {
    private long count;
    private List<VkAlbum> items;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<VkAlbum> getItems() {
        return items;
    }

    public void setItems(List<VkAlbum> items) {
        this.items = items;
    }
}
