package com.profile.tutorialesv2.service.impl;

import java.util.Date;

public class Notification {

    private String text;
    private Date time;

    public Notification(String text, Date time) {
        super();
        this.text = text;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}