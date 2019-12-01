package com.halulkin.lifer.TargetsFragment;

public class TargetsModel {

    private int targetId;
    private String targetName;
    private String targetDate;
    private boolean targetStatus;

    public TargetsModel() {
    }

    public TargetsModel(int targetId, String targetName, String targetDate, boolean targetStatus) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.targetDate = targetDate;
        this.targetStatus = targetStatus;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getTargetStatus() {
        return targetStatus;
    }

    public void setTargetStatus(boolean targetStatus) {
        this.targetStatus = targetStatus;
    }
}