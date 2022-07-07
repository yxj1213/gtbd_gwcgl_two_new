package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class ExRegistFirstStepBean implements Serializable {

    private String Phone;//	string
    private String StepToken;// string

    public String getPhone() {
        return Phone == null ? "" : Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStepToken() {
        return StepToken == null ? "" : StepToken;
    }

    public void setStepToken(String stepToken) {
        StepToken = stepToken;
    }
}
