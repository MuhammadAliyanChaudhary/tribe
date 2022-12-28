package com.example.tribe.Model;

public class EventMOdel {
    String date;
    String description;
    String eventid;
    String publisher;
    String time;
    String title;
    String trybeid;
    String excepted;
    public EventMOdel(){}

    public EventMOdel(String date, String description, String eventid, String publisher, String time, String title, String trybeid, String excepted) {
        this.date = date;
        this.description = description;
        this.eventid = eventid;
        this.publisher = publisher;
        this.time = time;
        this.title = title;
        this.trybeid = trybeid;
        this.excepted = excepted;
    }

    public String getExcepted() {
        return excepted;
    }

    public void setExcepted(String excepted) {
        this.excepted = excepted;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrybeid() {
        return trybeid;
    }

    public void setTrybeid(String trybeid) {
        this.trybeid = trybeid;
    }
}
