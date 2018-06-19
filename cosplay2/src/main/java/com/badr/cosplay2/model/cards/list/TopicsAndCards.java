package com.badr.cosplay2.model.cards.list;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ru.badr.cosplay2.api.cards.Topic;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:46
 */
public class TopicsAndCards implements Serializable {
    private List<Topic> topics;
    @SerializedName("requests")
    private List<ListCard> cards;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<ListCard> getCards() {
        return cards;
    }

    public void setCards(List<ListCard> cards) {
        this.cards = cards;
    }
}
