package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangeCompanySaveReturnBean implements Serializable {
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("StaffId")
    private String staffId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
