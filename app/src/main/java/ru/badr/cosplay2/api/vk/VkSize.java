package ru.badr.cosplay2.api.vk;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 16:36
 */
public class VkSize implements Serializable {
    private String src;
    private String type;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
