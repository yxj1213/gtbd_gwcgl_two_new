package com.ttce.vehiclemanage.bean;

public class IsOrderingBean {
    public boolean IsOrdering;
    public String CarFlow_OrderId;

    public boolean isOrdering() {
        return IsOrdering;
    }

    public void setOrdering(boolean ordering) {
        IsOrdering = ordering;
    }

    public String getCarFlow_OrderId() {
        return CarFlow_OrderId;
    }

    public void setCarFlow_OrderId(String carFlow_OrderId) {
        CarFlow_OrderId = carFlow_OrderId;
    }
}
