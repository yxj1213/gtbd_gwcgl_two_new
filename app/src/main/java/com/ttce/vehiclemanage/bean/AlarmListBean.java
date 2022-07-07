package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/5.
 */

public class AlarmListBean implements Serializable {

    private String DeviceId;//	string设备id

    private String PlateNumber;// string车牌号

    private String Name;//string 报警类型

    private String AlarmTimeStr;//string报警时间

    private String CarTypeStr;// string车辆类型

    private String CarColorStr;//string车牌颜色

    private int IsHandled;//integer($int32)是否处理未处理：0 已处理：1

    private String IsHandledStr;//string是否处理

    private double Lat;// number($double)纬度

    private double Lng;//number($double)经度

    public String getDeviceId() {
        return DeviceId == null ? "" : DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getPlateNumber() {
        return PlateNumber == null ? "" : PlateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    public String getName() {
        return Name == null ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlarmTimeStr() {
        return AlarmTimeStr == null ? "" : AlarmTimeStr;
    }

    public void setAlarmTimeStr(String alarmTimeStr) {
        AlarmTimeStr = alarmTimeStr;
    }

    public String getCarTypeStr() {
        return CarTypeStr == null ? "" : CarTypeStr;
    }

    public void setCarTypeStr(String carTypeStr) {
        CarTypeStr = carTypeStr;
    }

    public String getCarColorStr() {
        return CarColorStr == null ? "" : CarColorStr;
    }

    public void setCarColorStr(String carColorStr) {
        CarColorStr = carColorStr;
    }

    public int getIsHandled() {
        return IsHandled;
    }

    public void setIsHandled(int isHandled) {
        IsHandled = isHandled;
    }

    public String getIsHandledStr() {
        return IsHandledStr == null ? "" : IsHandledStr;
    }

    public void setIsHandledStr(String isHandledStr) {
        IsHandledStr = isHandledStr;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }
}
