package ru.badr.cosplay2.api.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ru.badr.base.util.HasId;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 13:28
 */
public class Card implements Serializable, HasId {
    private long id;
    @SerializedName("topic_id")
    private long topicId;
    @SerializedName("voting_number")
    private String votingNumber;
    @SerializedName("voting_title")
    private String votingTitle;
    private String win;
    @SerializedName("user_id")
    private long userId;
    private CardImage image;

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

    public String getVotingNumber() {
        return votingNumber;
    }

    public void setVotingNumber(String votingNumber) {
        this.votingNumber = votingNumber;
    }

    public String getVotingTitle() {
        return votingTitle;
    }

    public void setVotingTitle(String votingTitle) {
        this.votingTitle = votingTitle;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public CardImage getImage() {
        return image;
    }

    public void setImage(CardImage image) {
        this.image = image;
    }

    public static class List extends ArrayList<Card> {
    }
}
