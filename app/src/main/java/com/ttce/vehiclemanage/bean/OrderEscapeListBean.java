package com.ttce.vehiclemanage.bean;


public class OrderEscapeListBean {
    private String type;
    private int mEscapeList;

    public OrderEscapeListBean(String type, int mEscapeList) {
        this.type = type;
        this.mEscapeList = mEscapeList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getmEscapeList() {
        return mEscapeList;
    }

    public void setmEscapeList(int mEscapeList) {
        this.mEscapeList = mEscapeList;
    }
}
