package com.halulkin.lifer.TargetsFragment;

public class TargetsModel {

    private int targetId;
    private String targetName;
    private String targetDate;
    private int targetStatus;

    public TargetsModel() {
    }

    public TargetsModel(String targetName, String targetDate, int targetStatus) {
        this.targetName = targetName;
        this.targetDate = targetDate;
        this.targetStatus = targetStatus;
    }

    public TargetsModel(int targetId, String targetName, String targetDate, int targetStatus) {
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

    public int getTargetStatus() {
        return targetStatus;
    }

    public void setTargetStatus(int targetStatus) {
        this.targetStatus = targetStatus;
    }
}