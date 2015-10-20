package ru.badr.cosplay2.api.cards.info;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 13:51
 */
public class Section implements Serializable, HasId {
    private long id;
    @SerializedName("topic_id")
    private long topicId;
    private String title;
    @SerializedName("kogoTitle")
    private String kogoTitle;
    private String order;
    @SerializedName("max_repeat")
    private String maxRepeat;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKogoTitle() {
        return kogoTitle;
    }

    public void setKogoTitle(String kogoTitle) {
        this.kogoTitle = kogoTitle;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMaxRepeat() {
        return maxRepeat;
    }

    public void setMaxRepeat(String maxRepeat) {
        this.maxRepeat = maxRepeat;
    }
}
