package com.ttce.vehiclemanage.bean;

public class MessageBean {
    private String text;
    private String value;

    public MessageBean(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text==null?"":text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
