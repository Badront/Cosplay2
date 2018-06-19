package com.badr.cosplay2.model.cards.info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 15:37
 */
public class Badge implements Serializable {
    @SerializedName("request_section_id")
    private long sectionId;
    private String card;
    /*@SerializedName("another_requests")
    private String anotherRequests;*/

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
