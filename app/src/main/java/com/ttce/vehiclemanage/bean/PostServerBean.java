package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class PostServerBean {

    @SerializedName("ServerTime")
    private String serverTime;
    @SerializedName("ServerTimeFormat")
    private String serverTimeFormat;

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerTimeFormat() {
        return serverTimeFormat;
    }

    public void setServerTimeFormat(String serverTimeFormat) {
        this.serverTimeFormat = serverTimeFormat;
    }
}
