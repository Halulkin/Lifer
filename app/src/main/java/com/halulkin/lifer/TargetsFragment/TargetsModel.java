package com.halulkin.lifer.TargetsFragment;

public class TargetsModel {

    private String title;
    private String date;
    private boolean status;

    public TargetsModel(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    public TargetsModel(String title, String date, boolean status) {
        this.title = title;
        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
