package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class TravelItemBean implements Serializable {
    /**
     * Lat : 37.88013
     * Lng : 112.486913
     * Speed : 0.0
     * Mileage_Interval : 0.0
     * Mileage_Gps : 0.0
     * Angle : 0
     * AngleFormat : 正北
     * GpsTime : 2021-11-22 00:01:46
     * GpsTimeFormat : 2021-11-22 00:01:46
     * ServiceTime : 2021-11-22 00:01:48
     * ServiceTimeFormat : 2021-11-22 00:01:48
     * Status : 0
     * StatusFormat : null
     * Acc : 0
     * AccFormat : null
     * OilQuantity : 0L
     * Temperature : 1.0
     * TemperatureFormat : 1℃
     */

    private double Lat;
    private double Lng;
    private double Speed;
    private double Mileage_Interval;
    private double Mileage_Gps;
    private int Angle;
    private String AngleFormat;
    private String GpsTime;
    private String GpsTimeFormat;
    private String ServiceTime;
    private String ServiceTimeFormat;
    private int Status;
    private Object StatusFormat;
    private int Acc;
    private Object AccFormat;
    private String OilQuantity;
    private double Temperature;
    private String TemperatureFormat;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double Lng) {
        this.Lng = Lng;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }

    public double getMileage_Interval() {
        return Mileage_Interval;
    }

    public void setMileage_Interval(double Mileage_Interval) {
        this.Mileage_Interval = Mileage_Interval;
    }

    public double getMileage_Gps() {
        return Mileage_Gps;
    }

    public void setMileage_Gps(double Mileage_Gps) {
        this.Mileage_Gps = Mileage_Gps;
    }

    public int getAngle() {
        return Angle;
    }

    public void setAngle(int Angle) {
        this.Angle = Angle;
    }

    public String getAngleFormat() {
        return AngleFormat;
    }

    public void setAngleFormat(String AngleFormat) {
        this.AngleFormat = AngleFormat;
    }

    public String getGpsTime() {
        return GpsTime;
    }

    public void setGpsTime(String GpsTime) {
        this.GpsTime = GpsTime;
    }

    public String getGpsTimeFormat() {
        return GpsTimeFormat;
    }

    public void setGpsTimeFormat(String GpsTimeFormat) {
        this.GpsTimeFormat = GpsTimeFormat;
    }

    public String getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(String ServiceTime) {
        this.ServiceTime = ServiceTime;
    }

    public String getServiceTimeFormat() {
        return ServiceTimeFormat;
    }

    public void setServiceTimeFormat(String ServiceTimeFormat) {
        this.ServiceTimeFormat = ServiceTimeFormat;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Object getStatusFormat() {
        return StatusFormat;
    }

    public void setStatusFormat(Object StatusFormat) {
        this.StatusFormat = StatusFormat;
    }

    public int getAcc() {
        return Acc;
    }

    public void setAcc(int Acc) {
        this.Acc = Acc;
    }

    public Object getAccFormat() {
        return AccFormat;
    }

    public void setAccFormat(Object AccFormat) {
        this.AccFormat = AccFormat;
    }

    public String getOilQuantity() {
        return OilQuantity;
    }

    public void setOilQuantity(String OilQuantity) {
        this.OilQuantity = OilQuantity;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double Temperature) {
        this.Temperature = Temperature;
    }

    public String getTemperatureFormat() {
        return TemperatureFormat;
    }

    public void setTemperatureFormat(String TemperatureFormat) {
        this.TemperatureFormat = TemperatureFormat;
    }

//    /**
//     * Lat : 37.8233511
//     * Lng : 112.5012533
//     * Speed : 0.0
//     * Mileage_Interval : 32.8
//     * Mileage_Gps : 0.0
//     * Direction : 284
//     * GpsTime : 2021-11-08 14:58:56
//     * GpsTimeFormat : 2021-11-08 14:58:56
//     * ServiceTime : 2021-11-08 14:58:58
//     * ServiceTimeFormat : 2021-11-08 14:58:58
//     * Status : 0
//     * StatusFormat : null
//     * Acc : 0
//     * AccFormat : null
//     */
//
//    private double Lat;
//    private double Lng;
//    private double Speed;
//    private double Mileage_Interval;
//    private double Mileage_Gps;
//    private int Angle;
//    private String AngleFormat;
//    private String GpsTime;
//    private String GpsTimeFormat;
//    private String ServiceTime;
//    private String ServiceTimeFormat;
//    private int Status;
//    private Object StatusFormat;
//    private int Acc;
//    private Object AccFormat;
//    private double total;
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
//
//    public int getAngle() {
//        return Angle;
//    }
//
//    public void setAngle(int angle) {
//        Angle = angle;
//    }
//
//    public String getAngleFormat() {
//        return AngleFormat;
//    }
//
//    public void setAngleFormat(String angleFormat) {
//        AngleFormat = angleFormat;
//    }
//
//    public double getLat() {
//        return Lat;
//    }
//
//    public void setLat(double Lat) {
//        this.Lat = Lat;
//    }
//
//    public double getLng() {
//        return Lng;
//    }
//
//    public void setLng(double Lng) {
//        this.Lng = Lng;
//    }
//
//    public double getSpeed() {
//        return Speed;
//    }
//
//    public void setSpeed(double Speed) {
//        this.Speed = Speed;
//    }
//
//    public double getMileage_Interval() {
//        return Mileage_Interval;
//    }
//
//    public void setMileage_Interval(double Mileage_Interval) {
//        this.Mileage_Interval = Mileage_Interval;
//    }
//
//    public double getMileage_Gps() {
//        return Mileage_Gps;
//    }
//
//    public void setMileage_Gps(double Mileage_Gps) {
//        this.Mileage_Gps = Mileage_Gps;
//    }
//
//    public String getGpsTime() {
//        return GpsTime;
//    }
//
//    public void setGpsTime(String GpsTime) {
//        this.GpsTime = GpsTime;
//    }
//
//    public String getGpsTimeFormat() {
//        return GpsTimeFormat;
//    }
//
//    public void setGpsTimeFormat(String GpsTimeFormat) {
//        this.GpsTimeFormat = GpsTimeFormat;
//    }
//
//    public String getServiceTime() {
//        return ServiceTime;
//    }
//
//    public void setServiceTime(String ServiceTime) {
//        this.ServiceTime = ServiceTime;
//    }
//
//    public String getServiceTimeFormat() {
//        return ServiceTimeFormat;
//    }
//
//    public void setServiceTimeFormat(String ServiceTimeFormat) {
//        this.ServiceTimeFormat = ServiceTimeFormat;
//    }
//
//    public int getStatus() {
//        return Status;
//    }
//
//    public void setStatus(int Status) {
//        this.Status = Status;
//    }
//
//    public Object getStatusFormat() {
//        return StatusFormat;
//    }
//
//    public void setStatusFormat(Object StatusFormat) {
//        this.StatusFormat = StatusFormat;
//    }
//
//    public int getAcc() {
//        return Acc;
//    }
//
//    public void setAcc(int Acc) {
//        this.Acc = Acc;
//    }
//
//    public Object getAccFormat() {
//        return AccFormat;
//    }
//
//    public void setAccFormat(Object AccFormat) {
//        this.AccFormat = AccFormat;
//    }


}
