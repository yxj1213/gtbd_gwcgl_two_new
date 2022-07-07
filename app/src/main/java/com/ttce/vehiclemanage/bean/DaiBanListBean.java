package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class DaiBanListBean {

    @SerializedName("OrderModule")
    private int orderModule;
    @SerializedName("CountItems")
    private int countItems;
  @SerializedName("NeedToDoTimeFormat")
    private String NeedToDoTimeFormat;

    public String getNeedToDoTimeFormat() {
        return NeedToDoTimeFormat;
    }

    public void setNeedToDoTimeFormat(String needToDoTimeFormat) {
        NeedToDoTimeFormat = needToDoTimeFormat;
    }

    public int getOrderModule() {
        return orderModule;
    }

    public void setOrderModule(int orderModule) {
        this.orderModule = orderModule;
    }

    public int getCountItems() {
        return countItems;
    }

    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }
}
