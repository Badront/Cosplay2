package com.badr.cosplay2.model.event;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by abadretdinov
 * on 20.06.2018
 */
public class Event {
    private long id;
    private String city;
    private String domain;
    @SerializedName("end_time")
    private Date endTime;
    private long eventTypeId;
    private String href;
    private String month;
    @SerializedName("start_time")
    private Date startTime;
    private String subdomain;
    private String time;
    /*past, future*/
    @SerializedName("timestatus")
    private String timeStatus;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(String timeStatus) {
        this.timeStatus = timeStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
