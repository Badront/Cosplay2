package com.badr.cosplay2.model.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import ru.badr.base.util.HasId;
import ru.badr.cosplay2.api.cards.list.ListCard;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:26
 */
public class Topic implements Serializable, HasId {
    private long id;
    @SerializedName("url_code")
    private String urlCode;
    @SerializedName("card_code")
    private String cardCode;
    private String title;
    private java.util.List<ListCard> cards;

    public java.util.List<ListCard> getCards() {
        return cards;
    }

    public void setCards(java.util.List<ListCard> cards) {
        this.cards = cards;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
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

    public static class List extends ArrayList<Topic> {
        public List(Collection<? extends Topic> collection) {
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
