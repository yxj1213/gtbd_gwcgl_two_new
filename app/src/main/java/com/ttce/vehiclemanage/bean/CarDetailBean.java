package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class CarDetailBean implements Serializable {

    private String DeviceId;
    private String CarNub;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getCarNub() {
        return CarNub;
    }

    public void setCarNub(String carNub) {
        CarNub = carNub;
    }

}
