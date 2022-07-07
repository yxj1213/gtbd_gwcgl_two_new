package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class VerificationBean implements Serializable {


    /**
     * IsVerification : true
     * VerificationId : string
     * VerificationImgUrl : string
     */

    private boolean IsVerification;
    private String VerificationId;
    private String VerificationImgUrl;

    public boolean isIsVerification() {
        return IsVerification;
    }

    public void setIsVerification(boolean IsVerification) {
        this.IsVerification = IsVerification;
    }

    public String getVerificationId() {
        return VerificationId;
    }

    public void setVerificationId(String VerificationId) {
        this.VerificationId = VerificationId;
    }

    public String getVerificationImgUrl() {
        return VerificationImgUrl;
    }

    public void setVerificationImgUrl(String VerificationImgUrl) {
        this.VerificationImgUrl = VerificationImgUrl;
    }
}
