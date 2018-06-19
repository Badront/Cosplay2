package com.badr.cosplay2.model.cards.info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 14:59
 */
public class ReqSection implements Serializable, HasId {
    private long id;
    @SerializedName("request_id")
    private long requestId;
    @SerializedName("topic_section_id")
    private long topicSectionId;
    private String order;

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

    public long getTopicSectionId() {
        return topicSectionId;
    }

    public void setTopicSectionId(long topicSectionId) {
        this.topicSectionId = topicSectionId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
