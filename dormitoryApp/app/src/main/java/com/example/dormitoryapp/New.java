package com.example.dormitoryapp;

import java.io.Serializable;

public class New implements Serializable {
    private String news_id;
    private String aid;
    private String room_id;
    private String news_date;
    private String news_title;
    private String description;

    public New(
            String news_id,
            String aid,
            String room_id,
            String news_date,
            String news_title,
            String description
    ) {
        this.news_id = news_id;
        this.aid = aid;
        this.room_id=room_id;
        this.news_date=news_date;
        this.news_title=news_title;
        this.description=description;
    }

    // Getter Methods

    public String getNews_id() {
        return news_id;
    }

    public String getAid() {
        return aid;
    }

    public String getRoom_id() {
        return room_id;
    }

    public String getNews_date() {
        return news_date;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
