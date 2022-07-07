package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CarLatLngBean {
    @SerializedName("Lat")
    private double lat;
    @SerializedName("Lng")
    private double lng;
    @SerializedName("DeviceId")
    private String deviceId;
    @SerializedName("DeviceNumber")
    private String deviceNumber;
    @SerializedName("CarId")
    private String carId;
    @SerializedName("PlateNumber")
    private String plateNumber;
   @SerializedName("IsValid")
    private boolean IsValid;

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
