package com.ttce.vehiclemanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/7/8.
 */

public class TravelListBean implements Serializable {

    private double Mileage;//	number($double)总里程

    private String StartTime;//	string有轨迹数据的开始时间

    private String EndTime;//	string有轨迹数据的结束时间

    private List<TravelItemBean> Pois;

    private List<TravelItemBean> Parking;

    public double getMileage() {
        return Mileage;
    }

    public void setMileage(double mileage) {
        Mileage = mileage;
    }

    public String getStartTime() {
        return StartTime == null ? "" : StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime == null ? "" : EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public List<TravelItemBean> getPois() {
        if (Pois == null) {
            return new ArrayList<>();
        }
        return Pois;
    }

    public void setPois(List<TravelItemBean> pois) {
        Pois = pois;
    }

    public List<TravelItemBean> getParking() {
        return Parking;
    }

    public void setParking(List<TravelItemBean> parking) {
        Parking = parking;
    }
}
