package ru.badr.cosplay2.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 17:54
 */
public class JuryEntity implements Serializable {
    private String vk;
    private String name;
    private String city;
    private String image;
    @SerializedName("image_large")
    private String largeImage;
    private String description;
    @SerializedName("show_up_date")
    private Date showUpDate;

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public Date getShowUpDate() {
        return showUpDate;
    }

    public void setShowUpDate(Date showUpDate) {
        this.showUpDate = showUpDate;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
