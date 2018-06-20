package com.badr.cosplay2.model.cards.info;

import com.badr.cosplay2.model.cards.list.ListCard;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 12:27
 */
public class InfoCard extends ListCard {
    @SerializedName("event_id")
    private long eventId;
    private String number;
    private String status;
    @SerializedName("creation_time")
    private Date creationTime;
    @SerializedName("update_time")
    private Date updateTime;
    @SerializedName("comment_time")
    private Date commentTime;
    @SerializedName("announcement_title")
    private String announcementTitle;
    @SerializedName("diplom_title")
    private String diplomTitle;
    @SerializedName("program_title")
    private String programTitle;
    @SerializedName("win_title")
    private String winTitle;
    private int ER;

    public InfoCard() {
        super();
    }

    public InfoCard(InfoCard infoCard) {
        super(infoCard);
        this.eventId = infoCard.eventId;
        this.number = infoCard.number;
        this.status = infoCard.status;
        this.creationTime = infoCard.creationTime;
        this.updateTime = infoCard.updateTime;
        this.commentTime = infoCard.commentTime;
        this.announcementTitle = infoCard.announcementTitle;
        this.diplomTitle = infoCard.diplomTitle;
        this.programTitle = infoCard.programTitle;
        this.winTitle = infoCard.winTitle;
        this.ER = infoCard.ER;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getDiplomTitle() {
        return diplomTitle;
    }

    public void setDiplomTitle(String diplomTitle) {
        this.diplomTitle = diplomTitle;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getWinTitle() {
        return winTitle;
    }

    public void setWinTitle(String winTitle) {
        this.winTitle = winTitle;
    }

    public int getER() {
        return ER;
    }

    public void setER(int ER) {
        this.ER = ER;
    }
}
