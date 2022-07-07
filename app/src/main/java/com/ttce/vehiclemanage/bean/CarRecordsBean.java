package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CarRecordsBean {

    @SerializedName("OrderId")
    private String orderId;
    @SerializedName("OrderState")
    private int orderState;
    @SerializedName("EscapeOrderStateFormat")
    private String escapeOrderStateFormat;
    @SerializedName("LabelTitle")
    private String labelTitle;
    @SerializedName("RealName")
    private String realName;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("CheckState")
    private int checkState;
    @SerializedName("CheckReason")
    private String checkReason;
    @SerializedName("Icon")
    private int icon;
    @SerializedName("OrderModule_1000")
    private boolean orderModule_1000;
    @SerializedName("OrderModule_2000")
    private boolean orderModule_2000;
    @SerializedName("OrderModule_3000")
    private boolean orderModule_3000;
    @SerializedName("OrderModule_4000")
    private boolean orderModule_4000;
    @SerializedName("CreateTime")
    private String createTime;
    @SerializedName("CreateTimeFormat")
    private String createTimeFormat;
    @SerializedName("CreateBy")
    private String createBy;
    @SerializedName("CreateByUserName")
    private String createByUserName;
    @SerializedName("UpdateTime")
    private Object updateTime;
    @SerializedName("UpdateTimeFormat")
    private String updateTimeFormat;
    @SerializedName("UpdateBy")
    private String updateBy;
    @SerializedName("UpdateByUserName")
    private String updateByUserName;
    @SerializedName("CarFlow_Order_State_LogId")
    private String carFlow_Order_State_LogId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getEscapeOrderStateFormat() {
        return escapeOrderStateFormat;
    }

    public void setEscapeOrderStateFormat(String escapeOrderStateFormat) {
        this.escapeOrderStateFormat = escapeOrderStateFormat;
    }

    public String getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isOrderModule_1000() {
        return orderModule_1000;
    }

    public void setOrderModule_1000(boolean orderModule_1000) {
        this.orderModule_1000 = orderModule_1000;
    }

    public boolean isOrderModule_2000() {
        return orderModule_2000;
    }

    public void setOrderModule_2000(boolean orderModule_2000) {
        this.orderModule_2000 = orderModule_2000;
    }

    public boolean isOrderModule_3000() {
        return orderModule_3000;
    }

    public void setOrderModule_3000(boolean orderModule_3000) {
        this.orderModule_3000 = orderModule_3000;
    }

    public boolean isOrderModule_4000() {
        return orderModule_4000;
    }

    public void setOrderModule_4000(boolean orderModule_4000) {
        this.orderModule_4000 = orderModule_4000;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByUserName() {
        return updateByUserName;
    }

    public void setUpdateByUserName(String updateByUserName) {
        this.updateByUserName = updateByUserName;
    }

    public String getCarFlow_Order_State_LogId() {
        return carFlow_Order_State_LogId;
    }

    public void setCarFlow_Order_State_LogId(String carFlow_Order_State_LogId) {
        this.carFlow_Order_State_LogId = carFlow_Order_State_LogId;
    }
}
