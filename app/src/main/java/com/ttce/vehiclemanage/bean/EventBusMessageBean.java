package com.ttce.vehiclemanage.bean;

public class EventBusMessageBean {
    public String title;
    public String title2;
    public String title3;
    public String tag;

    public EventBusMessageBean(String tag,String title, String title2, String title3) {
        this.title = title;
        this.title2 = title2;
        this.title3 = title3;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
