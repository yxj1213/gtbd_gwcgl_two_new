package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class UserInfoBean implements Serializable {

    private String UserId;//string
    private String UserName;// string
    private String UserNikeName;// string
    private String CompanyId;// string
    private String CompanyName;//string单位名称

    private String DepartmentName;
    private String Sex;
    private String BirthDay;
    private String UserRealName;

    private String UserPhone;//string
    private String UserEmail;// string
    private String HeadPic;// string头像

    public String getUserId() {
        return UserId == null ? "" : UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName == null ? "" : UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNikeName() {
        return UserNikeName == null ? "" : UserNikeName;
    }

    public void setUserNikeName(String userNikeName) {
        UserNikeName = userNikeName;
    }

    public String getCompanyId() {
        return CompanyId == null ? "" : CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName == null ? "" : CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getUserPhone() {
        return UserPhone == null ? "" : UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserEmail() {
        return UserEmail == null ? "" : UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getHeadPic() {
        return HeadPic == null ? "" : HeadPic;
    }

    public void setHeadPic(String headPic) {
        HeadPic = headPic;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getUserRealName() {
        return UserRealName==null?"":UserRealName;
    }

    public void setUserRealName(String userRealName) {
        UserRealName = userRealName;
    }
}
