package ru.badr.cosplay2.api.cards.info;

import com.google.gson.annotations.SerializedName;

import ru.badr.cosplay2.api.cards.Topic;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 12:54
 */
public class InfoTopic extends Topic {
    @SerializedName("event_id")
    private long eventId;
    private String category;
    @SerializedName("voting_group")
    private String votingGroup;
    private long order;
    private String description;
    @SerializedName("voting_public")
    private String votingPublic;
    @SerializedName("voting_jury")
    private String votingJury;
    @SerializedName("print_badges")
    private String printBadges;
    @SerializedName("card_enabled")
    private String cardEnabled;
    @SerializedName("card_name_rule")
    private String cardNameRule;
    private String intro;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVotingGroup() {
        return votingGroup;
    }

    public void setVotingGroup(String votingGroup) {
        this.votingGroup = votingGroup;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVotingPublic() {
        return votingPublic;
    }

    public void setVotingPublic(String votingPublic) {
        this.votingPublic = votingPublic;
    }

    public String getVotingJury() {
        return votingJury;
    }

    public void setVotingJury(String votingJury) {
        this.votingJury = votingJury;
    }

    public String getPrintBadges() {
        return printBadges;
    }

    public void setPrintBadges(String printBadges) {
        this.printBadges = printBadges;
    }

    public String getCardEnabled() {
        return cardEnabled;
    }

    public void setCardEnabled(String cardEnabled) {
        this.cardEnabled = cardEnabled;
    }

    public String getCardNameRule() {
        return cardNameRule;
    }

    public void setCardNameRule(String cardNameRule) {
        this.cardNameRule = cardNameRule;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
