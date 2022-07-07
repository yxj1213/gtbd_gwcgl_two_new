package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangeCompanyBean implements Serializable {
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("CreateUser")
    private String createUser;
    @SerializedName("IsCreateDepartment")
    private boolean IsCreateDepartment;

    public boolean isCreateDepartment() {
        return IsCreateDepartment;
    }

    public void setCreateDepartment(boolean createDepartment) {
        IsCreateDepartment = createDepartment;
    }

    public String getCompanyId() {
        return companyId==null?"":companyId;
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

    public String getCreateUser() {
        return createUser==null?"":createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * Text : 北斗平台
     * Value : 21080300-0000-0000-0000-000000000001
     */

}
