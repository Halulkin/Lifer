package com.halulkin.lifer.ScheduleFragment;

public class ScheduleModel {

    private String time;
    private String title;
    private boolean status;

    public ScheduleModel(String time, String title, boolean status) {
        this.time = time;
        this.title = title;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}