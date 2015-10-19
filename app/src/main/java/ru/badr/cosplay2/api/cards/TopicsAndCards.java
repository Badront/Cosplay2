package ru.badr.cosplay2.api.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:46
 */
public class TopicsAndCards implements Serializable {
    List<Topic> topics;
    @SerializedName("requests")
    List<Card> cards;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
