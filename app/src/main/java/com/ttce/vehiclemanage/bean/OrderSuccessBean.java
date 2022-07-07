package com.ttce.vehiclemanage.bean;

public class OrderSuccessBean {
    private String CarFlow_OrderId;

    public String getCarFlow_OrderId() {
        return CarFlow_OrderId==null?"":CarFlow_OrderId;
    }

    public void setCarFlow_OrderId(String carFlow_OrderId) {
        CarFlow_OrderId = carFlow_OrderId;
    }
}
