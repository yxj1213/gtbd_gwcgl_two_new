package com.ttce.vehiclemanage.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * des:
 * Created by hk
 */
public class LoginBean implements Serializable {
    @SerializedName("RealCheck")
    private int realCheck;
    @SerializedName("Token")
    private String token;
    @SerializedName("CommonDevice")
    private int commonDevice;
    @SerializedName("UserPhone")
    private String userPhone;
    @SerializedName("IsNewDevice")
    private int isNewDevice;
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("DepartMentId")
    private String departMentId;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("StaffId")
    private String staffId;
    @SerializedName("IdCard")
    private String idCard;
    @SerializedName("UserRealName")
    private String userRealName;

    public LoginBean() {
    }


    public LoginBean(int realCheck, String token, String userPhone, String companyId, String companyName, String departMentId, String userId, String staffId, String userRealName, String idCardFormat) {
        this.realCheck = realCheck;
        this.token = token;
        this.userPhone = userPhone;
        this.companyId = companyId;
        this.companyName = companyName;
        this.departMentId = departMentId;
        this.userId = userId;
        this.staffId = staffId;
        this.idCard = idCardFormat;
        this.userRealName = userRealName;
    }

    public int getRealCheck() {
        return realCheck;
    }

    public void setRealCheck(int realCheck) {
        this.realCheck = realCheck;
    }

    public String getToken() {
        return token==null?"":token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCommonDevice() {
        return commonDevice;
    }

    public void setCommonDevice(int commonDevice) {
        this.commonDevice = commonDevice;
    }

    public String getUserPhone() {
        return userPhone==null?"":userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getIsNewDevice() {
        return isNewDevice;
    }

    public void setIsNewDevice(int isNewDevice) {
        this.isNewDevice = isNewDevice;
    }

    public String getCompanyId() {
        return companyId==null?"":companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName==null?"":companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartMentId() {
        return departMentId==null?"":departMentId;
    }

    public void setDepartMentId(String departMentId) {
        this.departMentId = departMentId;
    }

    public String getUserId() {
        return userId==null?"":userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStaffId() {
        return staffId==null?"":staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getIdCard() {
        return idCard==null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserRealName() {
        return userRealName==null?"":userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }


//    /**
//     * RealCheck : 1
//     * Token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIyMTExMTgwOC0yMjE2LTE0NjMtOTEyOC00MzAwNjkxNzMwMzAiLCJEYXRlQ3JlYXRlZCI6IjExLzIzLzIwMjEgNjozNDozNiBBTSIsIm5iZiI6MTYzNzY0OTI3NiwiZXhwIjoxNjY5MTg1Mjc2LCJpc3MiOiJTaW1wbGVTZXJ2ZXIifQ.DPtI_Nwcg00OgjeHewe96LbpwRAO_dgUvAW70qjhKnQ
//     * CommonDevice : 0
//     * BindPhone :
//     * IsNewDevice : 1
//     */
//
//    private int RealCheck;
//    private String Token;
//    private int CommonDevice;
//    private String BindPhone;
//    private int IsNewDevice;
//    private String CompanyId;
//    private String CompanyName;
//    private String UserId;
//    private String StaffId;
//    private String UserRealName;
//    private String IdCardFormat;
//
//    public String getUserRealName() {
//        return UserRealName==null?"":UserRealName;
//    }
//
//    public void setUserRealName(String userRealName) {
//        UserRealName = userRealName;
//    }
//
//    public String getIdCardFormat() {
//        return IdCardFormat==null?"":IdCardFormat;
//    }
//
//    public void setIdCardFormat(String idCardFormat) {
//        IdCardFormat = idCardFormat;
//    }
//
//    public LoginBean() {
//    }
//
//    public LoginBean(int realCheck, String bindPhone, String companyId, String companyName, String userId, String staffId, String userRealName, String idCardFormat) {
//        RealCheck = realCheck;
//        BindPhone = bindPhone;
//        CompanyId = companyId;
//        CompanyName = companyName;
//        UserId = userId;
//        StaffId = staffId;
//        UserRealName = userRealName;
//        IdCardFormat = idCardFormat;
//    }
//
//    public String getStaffId() {
//        return StaffId;
//    }
//
//    public void setStaffId(String staffId) {
//        StaffId = staffId;
//    }
//
//    public String getUserId() {
//        return UserId;
//    }
//
//    public void setUserId(String userId) {
//        UserId = userId;
//    }
//
//    public String getCompanyId() {
//        return CompanyId==null?"":CompanyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        CompanyId = companyId;
//    }
//
//    public String getCompanyName() {
//        return CompanyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        CompanyName = companyName;
//    }
//
//    public int getRealCheck() {
//        return RealCheck;
//    }
//
//    public void setRealCheck(int RealCheck) {
//        this.RealCheck = RealCheck;
//    }
//
//    public String getToken() {
//        return Token;
//    }
//
//    public void setToken(String Token) {
//        this.Token = Token;
//    }
//
//    public int getCommonDevice() {
//        return CommonDevice;
//    }
//
//    public void setCommonDevice(int CommonDevice) {
//        this.CommonDevice = CommonDevice;
//    }
//
//    public String getBindPhone() {
//        return BindPhone==null?"":BindPhone;
//    }
//
//    public void setBindPhone(String BindPhone) {
//        this.BindPhone = BindPhone;
//    }
//
//    public int getIsNewDevice() {
//        return IsNewDevice;
//    }
//
//    public void setIsNewDevice(int IsNewDevice) {
//        this.IsNewDevice = IsNewDevice;
//    }
}
