package com.badr.cosplay2.model.cards.info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 15:23
 */
public class ReqValue implements Serializable, HasId {
    private long id;
    @SerializedName("request_id")
    private long requestId;
    @SerializedName("request_section_id")
    private long requestSectionId;
    @SerializedName("topic_field_id")
    private long fieldId;
    private String value;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getRequestSectionId() {
        return requestSectionId;
    }

    public void setRequestSectionId(long requestSectionId) {
        this.requestSectionId = requestSectionId;
    }

    public long getFieldId() {
        return fieldId;
    }

    public void setFieldId(long fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
