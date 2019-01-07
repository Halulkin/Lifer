package com.halulkin.lifer.TargetsFragment;

public class TargetsModel {

    private int position;
    private String title;
    private boolean status;

    public TargetsModel(int position, String title, boolean status) {
        this.position = position;
        this.title = title;
        this.status = status;
    }

    public TargetsModel(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
