package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class MarksBean implements Serializable {
    /**
     * MarkId : 0
     * Name : 3
     * Level : 1
     * AreaName : a
     * MarkTime : 2020-01-20T17:17:58
     * MarkTimeStr : 2020-01-20 17:17:58
     */

    private String MarkId;
    private String Name;
    private int Level;
    private String AreaName;
    private String MarkTime;
    private String MarkTimeStr;
    private int type;//1是重点，2是起点，其他是中间

    public String getMarkId() {
        return MarkId;
    }

    public void setMarkId(String markId) {
        MarkId = markId;
    }

    public String getName() {
        return Name == null ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getAreaName() {
        return AreaName == null ? "" : AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getMarkTime() {
        return MarkTime == null ? "" : MarkTime;
    }

    public void setMarkTime(String markTime) {
        MarkTime = markTime;
    }

    public String getMarkTimeStr() {
        return MarkTimeStr == null ? "" : MarkTimeStr;
    }

    public void setMarkTimeStr(String markTimeStr) {
        MarkTimeStr = markTimeStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
