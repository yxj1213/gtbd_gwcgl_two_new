package com.ttce.vehiclemanage.bean;


public class SetBean {

    private String number;
    private String type;

    public SetBean(String number) {
        this.number = number;
    }

    public SetBean(String number, String pages) {
        this.number = number;
        this.type = pages;
    }

    public String getPages() {
        return type;
    }

    public void setPages(String pages) {
        this.type = pages;
    }

    public String getNumber() {
        return number == null ? "" : number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
