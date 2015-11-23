package ru.badr.cosplay2.api.cards.info.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ru.badr.cosplay2.api.cards.User;
import ru.badr.cosplay2.api.cards.info.Badge;
import ru.badr.cosplay2.api.cards.info.Field;
import ru.badr.cosplay2.api.cards.info.InfoCard;
import ru.badr.cosplay2.api.cards.info.InfoTopic;
import ru.badr.cosplay2.api.cards.info.ReqSection;
import ru.badr.cosplay2.api.cards.info.ReqValue;
import ru.badr.cosplay2.api.cards.info.Section;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 13:47
 */
public class GetCardResult implements Serializable {
    private String message;
    @SerializedName("request")
    private InfoCard card;
    private InfoTopic topic;
    private List<Section> sections;
    private List<Field> fields;
    @SerializedName("reqsections")
    private List<ReqSection> reqSections;
    @SerializedName("reqvalues")
    private List<ReqValue> reqValues;
    private List<Badge> badges;
    private List<User> users;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InfoCard getCard() {
        return card;
    }

    public void setCard(InfoCard card) {
        this.card = card;
    }

    public InfoTopic getTopic() {
        return topic;
    }

    public void setTopic(InfoTopic topic) {
        this.topic = topic;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<ReqSection> getReqSections() {
        return reqSections;
    }

    public void setReqSections(List<ReqSection> reqSections) {
        this.reqSections = reqSections;
    }

    public List<ReqValue> getReqValues() {
        return reqValues;
    }

    public void setReqValues(List<ReqValue> reqValues) {
        this.reqValues = reqValues;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
