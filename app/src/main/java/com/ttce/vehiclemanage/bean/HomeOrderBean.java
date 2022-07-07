package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class HomeOrderBean {

    @SerializedName("TotalCount")
    private int totalCount;
    @SerializedName("AssignCount")
    private int assignCount;
    @SerializedName("UseCarCount")
    private int useCarCount;
    @SerializedName("CompletedCount")
    private int completedCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(int assignCount) {
        this.assignCount = assignCount;
    }

    public int getUseCarCount() {
        return useCarCount;
    }

    public void setUseCarCount(int useCarCount) {
        this.useCarCount = useCarCount;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }
}
