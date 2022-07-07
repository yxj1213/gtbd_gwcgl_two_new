package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class IdCareBean {

    @SerializedName("IdCard")
    private String idCard;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
