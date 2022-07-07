package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class ExVerificationBean implements Serializable {

    private String VerificationId;//	string

    private String VerificationCode;//string

    public String getVerificationId() {
        return VerificationId == null ? "" : VerificationId;
    }

    public void setVerificationId(String verificationId) {
        VerificationId = verificationId;
    }

    public String getVerificationCode() {
        return VerificationCode == null ? "" : VerificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
    }
}
