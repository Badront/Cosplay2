package ru.badr.cosplay2.api.cards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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

    public Card() {
        super();
    }

    public Card(Card card) {
        super();
        this.id = card.id;
        this.topicId = card.topicId;
        this.votingNumber = card.votingNumber;
        this.votingTitle = card.votingTitle;
        this.win = card.win;
        this.userId = card.userId;
    }

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

}
