package com.ttce.vehiclemanage.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginImageVerificationBean implements Serializable {

    @SerializedName("IsVerification")
    private boolean isVerification;
    @SerializedName("VerificationId")
    private String verificationId;
    @SerializedName("VerificationmaxImgBase64url")
    private String verificationmaxImgBase64url;
    @SerializedName("VerificationminImgBase64url")
    private String verificationminImgBase64url;

    public boolean isIsVerification() {
        return isVerification;
    }

    public void setIsVerification(boolean isVerification) {
        this.isVerification = isVerification;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getVerificationmaxImgBase64url() {
        return verificationmaxImgBase64url;
    }

    public void setVerificationmaxImgBase64url(String verificationmaxImgBase64url) {
        this.verificationmaxImgBase64url = verificationmaxImgBase64url;
    }

    public String getVerificationminImgBase64url() {
        return verificationminImgBase64url;
    }

    public void setVerificationminImgBase64url(String verificationminImgBase64url) {
        this.verificationminImgBase64url = verificationminImgBase64url;
    }
}
