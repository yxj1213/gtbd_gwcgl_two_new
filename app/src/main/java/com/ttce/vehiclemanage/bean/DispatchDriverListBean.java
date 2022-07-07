package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class DispatchDriverListBean {

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
    @SerializedName("Phone")
    private String Phone;
    @SerializedName("IsDriver")
    private int isDriver;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public DispatchDriverListBean(String name, String companyId, String departmentId, String userId, String staffId) {
        this.name = name;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.staffId = staffId;
        this.userId = userId;
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
}
