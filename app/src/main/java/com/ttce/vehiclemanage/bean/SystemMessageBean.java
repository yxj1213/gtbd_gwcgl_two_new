package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/19.
 */

public class SystemMessageBean implements Serializable {

    private String Title;//	string
    private String Content;//string

    private String StartTime;// string($date-time)

    private String StartTimeFormat;//string

    private String EndTime;// string($date-time)

    private String EndTimeFormat;//string

    public String getTitle() {
        return Title == null ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content == null ? "" : Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getStartTime() {
        return StartTime == null ? "" : StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getStartTimeFormat() {
        return StartTimeFormat == null ? "" : StartTimeFormat;
    }

    public void setStartTimeFormat(String startTimeFormat) {
        StartTimeFormat = startTimeFormat;
    }

    public String getEndTime() {
        return EndTime == null ? "" : EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getEndTimeFormat() {
        return EndTimeFormat == null ? "" : EndTimeFormat;
    }

    public void setEndTimeFormat(String endTimeFormat) {
        EndTimeFormat = endTimeFormat;
    }
}
