package ru.badr.cosplay2.api;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 17:54
 */
public class JuryEntity implements Serializable {
    private String vk;
    private String name;
    private String image;
    private String description;

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
