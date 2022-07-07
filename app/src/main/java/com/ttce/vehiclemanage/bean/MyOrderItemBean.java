package com.ttce.vehiclemanage.bean;

public class MyOrderItemBean {
    private String ycrname;
    private String ycrtel;
    private String state;
    private String qdaddress;
    private String qdname;
    private String zdaddress;
    private String zdname;
    private String ddbh;
    private String time;
    private String orderid;
    private boolean isJieDan;

    public MyOrderItemBean(String ycrname, String ycrtel, String state, String qdaddress, String qdname, String zdaddress, String zdname, String ddbh, String time) {
        this.ycrname = ycrname;
        this.ycrtel = ycrtel;
        this.state = state;
        this.qdaddress = qdaddress;
        this.qdname = qdname;
        this.zdaddress = zdaddress;
        this.zdname = zdname;
        this.ddbh = ddbh;
        this.time = time;
    }

    public MyOrderItemBean(String ycrname, String ycrtel, String state, String qdaddress, String qdname, String zdaddress, String zdname, String ddbh, String time, boolean isJieDan) {
        this.ycrname = ycrname;
        this.ycrtel = ycrtel;
        this.state = state;
        this.qdaddress = qdaddress;
        this.qdname = qdname;
        this.zdaddress = zdaddress;
        this.zdname = zdname;
        this.ddbh = ddbh;
        this.time = time;
        this.orderid = orderid;
        this.isJieDan = isJieDan;
    }

    public boolean isJieDan() {
        return isJieDan;
    }

    public void setJieDan(boolean jieDan) {
        isJieDan = jieDan;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getYcrname() {
        return ycrname;
    }

    public void setYcrname(String ycrname) {
        this.ycrname = ycrname;
    }

    public String getYcrtel() {
        return ycrtel;
    }

    public void setYcrtel(String ycrtel) {
        this.ycrtel = ycrtel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQdaddress() {
        return qdaddress;
    }

    public void setQdaddress(String qdaddress) {
        this.qdaddress = qdaddress;
    }

    public String getQdname() {
        return qdname;
    }

    public void setQdname(String qdname) {
        this.qdname = qdname;
    }

    public String getZdaddress() {
        return zdaddress;
    }

    public void setZdaddress(String zdaddress) {
        this.zdaddress = zdaddress;
    }

    public String getZdname() {
        return zdname;
    }

    public void setZdname(String zdname) {
        this.zdname = zdname;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
