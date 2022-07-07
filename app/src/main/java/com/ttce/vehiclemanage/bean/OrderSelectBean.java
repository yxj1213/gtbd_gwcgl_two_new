package com.ttce.vehiclemanage.bean;

public class OrderSelectBean {
    private String startTime;
    private String endTime;
    private String carNumber;
    private String nameOrPhone;
    private String orderId;
    private String type;
    private String typeclass;

    public OrderSelectBean(String startTime, String endTime, String carNumber, String nameOrPhone, String orderId,String type,String typeclass) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.carNumber = carNumber;
        this.nameOrPhone = nameOrPhone;
        this.orderId = orderId;
        this.type = type;
        this.typeclass = typeclass;
    }

    public OrderSelectBean(String startTime, String endTime, String carNumber,String typeclass) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.carNumber = carNumber;
        this.typeclass = typeclass;
    }

    public String getTypeclass() {
        return typeclass==null?"3":typeclass;
    }

    public void setTypeclass(String typeclass) {
        this.typeclass = typeclass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime==null?"":startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime==null?"":endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getNameOrPhone() {
        return nameOrPhone;
    }

    public void setNameOrPhone(String nameOrPhone) {
        this.nameOrPhone = nameOrPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
