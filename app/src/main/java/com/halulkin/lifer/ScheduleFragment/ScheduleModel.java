package com.halulkin.lifer.ScheduleFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleModel implements Comparable<ScheduleModel> {

    private String time;
    private String title;
    private boolean status;
    private int parsedTime;

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

    private int parseStringToTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.US);

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(time));
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            parsedTime = (hours * 60) + minutes;

        } catch (ParseException ignored) {
        }
        return parsedTime;
    }

    @Override
    public int compareTo(ScheduleModel scheduleModel) {
        int compareTime = parseStringToTime(((ScheduleModel) scheduleModel).getTime());
        /* For Ascending order*/
        return this.parseStringToTime(time) - compareTime;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }
}