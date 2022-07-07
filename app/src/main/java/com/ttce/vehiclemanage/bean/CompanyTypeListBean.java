package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CompanyTypeListBean {

    @SerializedName("Name")
    private String name;
    @SerializedName("Sort")
    private int sort;
    @SerializedName("CreateTime")
    private Object createTime;
    @SerializedName("CreateTimeFormat")
    private String createTimeFormat;
    @SerializedName("CreateBy")
    private Object createBy;
    @SerializedName("CreateByUserName")
    private String createByUserName;
    @SerializedName("UpdateTime")
    private Object updateTime;
    @SerializedName("UpdateTimeFormat")
    private String updateTimeFormat;
    @SerializedName("UpdateBy")
    private Object updateBy;
    @SerializedName("UpdateByUserName")
    private String updateByUserName;
    @SerializedName("Business_CompanyTypeId")
    private String business_CompanyTypeId;

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

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateByUserName() {
        return createByUserName;
    }

    public void setCreateByUserName(String createByUserName) {
        this.createByUserName = createByUserName;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeFormat() {
        return updateTimeFormat;
    }

    public void setUpdateTimeFormat(String updateTimeFormat) {
        this.updateTimeFormat = updateTimeFormat;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByUserName() {
        return updateByUserName;
    }

    public void setUpdateByUserName(String updateByUserName) {
        this.updateByUserName = updateByUserName;
    }

    public String getBusiness_CompanyTypeId() {
        return business_CompanyTypeId;
    }

    public void setBusiness_CompanyTypeId(String business_CompanyTypeId) {
        this.business_CompanyTypeId = business_CompanyTypeId;
    }
}
