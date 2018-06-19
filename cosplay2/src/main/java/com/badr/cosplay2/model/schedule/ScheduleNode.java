package com.badr.cosplay2.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by Badr on 15.11.2015.
 */
public class ScheduleNode implements Serializable {
    private Type type;
    private List nodes;
    @SerializedName("request_id")
    private Long cardId;
    @SerializedName("card_code")
    private String cardCode;
    private Card card;
    private String title;
    /*
        @SerializedName("date_start")
        private Date startDate;*/
    @SerializedName("time_start")
    private Date startTime;
    @SerializedName("time_end")
    private Date endTime;
    private boolean collapsed;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List getNodes() {
        return nodes;
    }

    public void setNodes(List nodes) {
        this.nodes = nodes;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(getTitle());
        if (getStartTime() != null) {
            builder.append(' ').append(Utils.SCHEDULE_TIME_FORMAT.format(getStartTime()));
        }
        return builder.toString();
    }

    public enum Type {
        day,
        place,
        event,
        request
    }//http://akinoyume.cosplay2.ru/api/events/get_plan

    public static class List extends ArrayList<ScheduleNode> {
        public List(Collection<? extends ScheduleNode> collection) {
            super(collection);
        }

        public List(int capacity) {
            super(capacity);
        }

        public List() {
            super();
        }
    }
}
