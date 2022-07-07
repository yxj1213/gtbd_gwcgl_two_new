package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class BaoXianBean implements Serializable {
    private String MoneyType;

    private double MoneyNum;

    public String getMoneyType() {
        return MoneyType == null ? "" : MoneyType;
    }

    public void setMoneyType(String moneyType) {
        MoneyType = moneyType;
    }

    public double getMoneyNum() {
        return MoneyNum;
    }

    public void setMoneyNum(double moneyNum) {
        MoneyNum = moneyNum;
    }
}
