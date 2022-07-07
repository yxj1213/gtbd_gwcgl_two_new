package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class IsHasWorkBeanchBean {

    @SerializedName("IsHasPrivilege")
    private boolean isHasPrivilege;

    public boolean isIsHasPrivilege() {
        return isHasPrivilege;
    }

    public void setIsHasPrivilege(boolean isHasPrivilege) {
        this.isHasPrivilege = isHasPrivilege;
    }
}
