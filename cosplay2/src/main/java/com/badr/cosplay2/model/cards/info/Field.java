package com.badr.cosplay2.model.cards.info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 14:24
 */
public class Field implements Serializable, HasId {
    private long id;
    @SerializedName("section_id")
    private long sectionId;
    private String title;
    private String type;
    private String order;
    private String hint;
    @SerializedName("internal_name")
    private String internalName;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
}
