package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class Message2Bean {

    @SerializedName("Text")
    private String text;
    @SerializedName("Value")
    private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
