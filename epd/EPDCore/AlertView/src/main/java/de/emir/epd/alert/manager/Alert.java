package de.emir.epd.alert.manager;

import java.time.ZonedDateTime;

public class Alert {
    private String id;
    private String text;
    private AlertState state;
    private ZonedDateTime dateTime;

    public Alert(String id, String text, AlertState state) {
        this(id, text, state, ZonedDateTime.now());
    }

    public Alert(String id, String text, AlertState state, ZonedDateTime dateTime) {
        this.id = id;
        this.text = text;
        this.state = state;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public AlertState getState() {
        return state;
    }

    void setState(AlertState state) {
        this.state = state;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void updateTime() {
        this.setDateTime(ZonedDateTime.now());
    }
}
