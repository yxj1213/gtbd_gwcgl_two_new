package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/5.
 */

public class InstructDetailBean implements Serializable {

    /**
     * ID : 08d992d8-785c-47f8-82c6-6f92b191c205
     * DeviceId : 21101217-0615-5168-9136-637013277577
     * PlateNumber : 晋D52946
     * Name : 点名
     * SendTimeFormat : 2021-10-19 16:14:28
     * Status : 0
     * StatusFormat : 发送失败
     * OrginName : PC端标识
     * UserName : root
     * SendType : null
     */

    private String ID;
    private String DeviceId;
    private String PlateNumber;
    private String Name;
    private String SendTimeFormat;
    private int Status;
    private String StatusFormat;
    private String OrginName;
    private String UserName;
    private Object SendType;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSendTimeFormat() {
        return SendTimeFormat;
    }

    public void setSendTimeFormat(String SendTimeFormat) {
        this.SendTimeFormat = SendTimeFormat;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusFormat() {
        return StatusFormat;
    }

    public void setStatusFormat(String StatusFormat) {
        this.StatusFormat = StatusFormat;
    }

    public String getOrginName() {
        return OrginName;
    }

    public void setOrginName(String OrginName) {
        this.OrginName = OrginName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Object getSendType() {
        return SendType;
    }

    public void setSendType(Object SendType) {
        this.SendType = SendType;
    }
}
