package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/5.
 */

public class EquipmentDetailBean implements Serializable {


    /**
     * CompanyId : 21101211-4056-4872-9136-637025905990
     * CompanyName : 晋兴能源
     * DepartMentId : 21101215-2353-7572-9136-637070009950
     * DepartMentName : 晋兴车队
     * PlateNumber : 苏DE800S
     * DevieNumber : 120202108079
     * OilQuantity : 0L
     * OilPercent : 0%
     * Temperature : 255.0
     * TemperatureFormat : --
     * GpsTime : 2021-11-19 18:55:35
     * GpsTimeFormat : 2021-11-19 18:55:35
     * ServiceTimeFormat : 2021-11-19 18:55:35
     * ServiceTime : 2021-11-19 18:55:35
     * Speed : 0.0
     * Lat : 37.8525111
     * Lng : 112.4504889
     * IsOilSensor : 0
     * IsOilSensorFormat : 未安装
     * IsTemperatureSensor : 0
     * IsTemperatureSensorFormat : 未安装
     * FuWuQiXian : 2024-01-01 00:00:00
     * CarStatus : 52
     * CarStatusFormat : 静止熄火21天4时37分
     */

    private String CompanyId;
    private String CompanyName;
    private String DepartMentId;
    private String DepartMentName;
    private String PlateNumber;
    private String DevieNumber;
    private String OilQuantity;
    private String OilPercent;
    private double Temperature;
    private String TemperatureFormat;
    private String GpsTime;
    private String GpsTimeFormat;
    private String ServiceTimeFormat;
    private String ServiceTime;
    private double Speed;
    private double Lat;
    private double Lng;
    private int IsOilSensor;
    private String IsOilSensorFormat;
    private int IsTemperatureSensor;
    private String IsTemperatureSensorFormat;
    private String FuWuQiXian;
    private int CarStatus;
    private String CarStatusFormat;

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getDepartMentId() {
        return DepartMentId;
    }

    public void setDepartMentId(String DepartMentId) {
        this.DepartMentId = DepartMentId;
    }

    public String getDepartMentName() {
        return DepartMentName;
    }

    public void setDepartMentName(String DepartMentName) {
        this.DepartMentName = DepartMentName;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public String getDevieNumber() {
        return DevieNumber;
    }

    public void setDevieNumber(String DevieNumber) {
        this.DevieNumber = DevieNumber;
    }

    public String getOilQuantity() {
        return OilQuantity;
    }

    public void setOilQuantity(String OilQuantity) {
        this.OilQuantity = OilQuantity;
    }

    public String getOilPercent() {
        return OilPercent;
    }

    public void setOilPercent(String OilPercent) {
        this.OilPercent = OilPercent;
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

    public String getServiceTimeFormat() {
        return ServiceTimeFormat;
    }

    public void setServiceTimeFormat(String ServiceTimeFormat) {
        this.ServiceTimeFormat = ServiceTimeFormat;
    }

    public String getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(String ServiceTime) {
        this.ServiceTime = ServiceTime;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double Speed) {
        this.Speed = Speed;
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

    public int getIsOilSensor() {
        return IsOilSensor;
    }

    public void setIsOilSensor(int IsOilSensor) {
        this.IsOilSensor = IsOilSensor;
    }

    public String getIsOilSensorFormat() {
        return IsOilSensorFormat;
    }

    public void setIsOilSensorFormat(String IsOilSensorFormat) {
        this.IsOilSensorFormat = IsOilSensorFormat;
    }

    public int getIsTemperatureSensor() {
        return IsTemperatureSensor;
    }

    public void setIsTemperatureSensor(int IsTemperatureSensor) {
        this.IsTemperatureSensor = IsTemperatureSensor;
    }

    public String getIsTemperatureSensorFormat() {
        return IsTemperatureSensorFormat;
    }

    public void setIsTemperatureSensorFormat(String IsTemperatureSensorFormat) {
        this.IsTemperatureSensorFormat = IsTemperatureSensorFormat;
    }

    public String getFuWuQiXian() {
        return FuWuQiXian;
    }

    public void setFuWuQiXian(String FuWuQiXian) {
        this.FuWuQiXian = FuWuQiXian;
    }

    public int getCarStatus() {
        return CarStatus;
    }

    public void setCarStatus(int CarStatus) {
        this.CarStatus = CarStatus;
    }

    public String getCarStatusFormat() {
        return CarStatusFormat;
    }

    public void setCarStatusFormat(String CarStatusFormat) {
        this.CarStatusFormat = CarStatusFormat;
    }
}
