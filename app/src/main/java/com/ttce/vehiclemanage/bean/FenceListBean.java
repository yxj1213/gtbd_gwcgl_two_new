package com.ttce.vehiclemanage.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hk on 2019/7/8.
 */

public class FenceListBean implements Serializable {

    private String Id;//	integer($int32)围栏id

    private String Name;// string围栏名称

    private int Radius;// integer($int32)圆形围栏半径

    private double Lat;// number($double)圆形围栏中心点纬度

    private double Lng;// number($double)圆形围栏中心点经度

    private Integer[] AlarmTypes;//	[入围栏报警：2，出围栏报警：4]

    private List<String> Devices;

    private String Address;

    private List<CarDetailBean> CarDetails;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name == null ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
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

    public Integer[] getAlarmTypes() {
        return AlarmTypes;
    }

    public void setAlarmTypes(Integer[] alarmTypes) {
        AlarmTypes = alarmTypes;
    }

    public String getAddress() {
        return Address == null ? "" : Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<String> getDevices() {
        return Devices;
    }

    public void setDevices(List<String> devices) {
        Devices = devices;
    }

    public List<CarDetailBean> getCarDetails() {
        return CarDetails;
    }

    public void setCarDetails(List<CarDetailBean> carDetails) {
        CarDetails = carDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FenceListBean that = (FenceListBean) o;

        return Id == that.Id;
    }

//    @Override
//    public int hashCode() {
//        return Id;
//    }
}
