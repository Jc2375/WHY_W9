package com.example.why_w9;

public class ScheduleModel {
    //must be same name in db
    String name, mondayTime, tuesdayTime,
    wednesdayTime,thursdayTime,fridayTime,
    saturdayTime,sundayTime;

    public String getName() {
        return name;
    }

    public String getMondayTime() {
        return mondayTime;
    }

    public String getTuesdayTime() {
        return tuesdayTime;
    }

    public String getWednesdayTime() {
        return wednesdayTime;
    }

    public String getThursdayTime() {
        return thursdayTime;
    }

    public String getFridayTime() {
        return fridayTime;
    }

    public String getSaturdayTime() {
        return saturdayTime;
    }

    public String getSundayTime() {
        return sundayTime;
    }
}
