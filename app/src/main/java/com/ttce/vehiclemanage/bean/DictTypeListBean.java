package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class DictTypeListBean {

    @SerializedName("DicFlag")
    private int dicFlag;
    @SerializedName("DicType")
    private int dicType;
    @SerializedName("Name")
    private String name;
    @SerializedName("Sort")
    private int sort;
    @SerializedName("ParentId")
    private String parentId;
    @SerializedName("CreatTime")
    private String creatTime;
    @SerializedName("CreatUserId")
    private String creatUserId;
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("IsDel")
    private int isDel;
    @SerializedName("ID")
    private String iD;

    public int getDicFlag() {
        return dicFlag;
    }

    public void setDicFlag(int dicFlag) {
        this.dicFlag = dicFlag;
    }

    public int getDicType() {
        return dicType;
    }

    public void setDicType(int dicType) {
        this.dicType = dicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatUserId() {
        return creatUserId;
    }

    public void setCreatUserId(String creatUserId) {
        this.creatUserId = creatUserId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
}
