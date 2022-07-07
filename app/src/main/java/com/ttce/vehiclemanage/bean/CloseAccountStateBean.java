package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CloseAccountStateBean {

    @SerializedName("CloseStatus")
    private int closeStatus;
    @SerializedName("ApplyTime")
    private String applyTime;
    @SerializedName("ApplyReason")
    private String applyReason;
    @SerializedName("DealTime")
    private String dealTime;
    @SerializedName("DealInfo")
    private String dealInfo;
    @SerializedName("CompleteTime")
    private String completeTime;
    @SerializedName("IsApply")
    private boolean isApply;

    public int getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(int closeStatus) {
        this.closeStatus = closeStatus;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealInfo() {
        return dealInfo;
    }

    public void setDealInfo(String dealInfo) {
        this.dealInfo = dealInfo;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public boolean isIsApply() {
        return isApply;
    }

    public void setIsApply(boolean isApply) {
        this.isApply = isApply;
    }
}
