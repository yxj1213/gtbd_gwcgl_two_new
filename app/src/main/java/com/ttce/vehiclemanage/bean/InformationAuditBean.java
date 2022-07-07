package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class InformationAuditBean {

    @SerializedName("ID")
    private String iD;
    @SerializedName("Name")
    private String name;
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private Object companyName;
    @SerializedName("DepartmentId")
    private String departmentId;
    @SerializedName("CreateTime")
    private String createTime;
    @SerializedName("StaffId")
    private String staffId;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("IsDriver")
    private int isDriver;
    @SerializedName("State")
    private int state;
    @SerializedName("CheckTime")
    private String checkTime;
    @SerializedName("UpdateTime")
    private String UpdateTime;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("CountItems")
    private String CountItems;
    @SerializedName("IdCard")
    private String IdCard;

    public String getUpdateTime() {
        return UpdateTime==null?"":UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(int isDriver) {
        this.isDriver = isDriver;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getPhone() {
        return phone==null?"":phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountItems() {
        return CountItems;
    }

    public void setCountItems(String countItems) {
        this.CountItems = CountItems;
    }

    public String getIdCard() {
        return IdCard==null?"":IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }
}
