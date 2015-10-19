package ru.badr.cosplay2.api.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.badr.base.util.HasId;

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
}
