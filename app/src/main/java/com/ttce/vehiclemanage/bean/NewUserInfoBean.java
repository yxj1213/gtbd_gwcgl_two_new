package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class NewUserInfoBean {


    @SerializedName("OfficeDateFormat")
    private String officeDateFormat;
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("DeaprtmentId")
    private String deaprtmentId;
    @SerializedName("DeaprtmentName")
    private String deaprtmentName;
    @SerializedName("PositionId")
    private String positionId;
    @SerializedName("PositionName")
    private String positionName;
    @SerializedName("UserPhone")
    private String userPhone;
    @SerializedName("IdCard")
    private String idCard;
    @SerializedName("UserRealName")
    private String userRealName;
    @SerializedName("EmergencycontactName")
    private String emergencycontactName;
    @SerializedName("EmergencycontactPhone")
    private String emergencycontactPhone;
    @SerializedName("OfficeDate")
    private String officeDate;
    @SerializedName("XianZhu")
    private String xianZhu;
    @SerializedName("IsDriver")
    private int isDriver;
    @SerializedName("CreateTime")
    private String createTime;
    @SerializedName("CreateUser")
    private String createUser;
    @SerializedName("UpdateTime")
    private String updateTime;
    @SerializedName("UpdateUser")
    private String updateUser;
    @SerializedName("DelTime")
    private String delTime;
    @SerializedName("DelUser")
    private String delUser;
    @SerializedName("IsDel")
    private int isDel;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("ReimbursementRatio")
    private double reimbursementRatio;
    @SerializedName("ObjectId")
    private String objectId;
    @SerializedName("Source")
    private int source;
    @SerializedName("State")
    private int state;
    @SerializedName("CheckUserId")
    private String checkUserId;
    @SerializedName("CheckTime")
    private String checkTime;
    @SerializedName("IsTask")
    private int isTask;
    @SerializedName("PositionStatus")
    private int positionStatus;
    @SerializedName("ID")
    private String iD;
    @SerializedName("Code")
    private String Code;

    public String getCode() {
        return Code==null?"":Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getOfficeDateFormat() {
        return officeDateFormat==null?"":officeDateFormat;
    }

    public void setOfficeDateFormat(String officeDateFormat) {
        this.officeDateFormat = officeDateFormat;
    }

    public String getCompanyId() {
        return companyId==null?"":companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeaprtmentId() {
        return deaprtmentId==null?"":deaprtmentId;
    }

    public void setDeaprtmentId(String deaprtmentId) {
        this.deaprtmentId = deaprtmentId;
    }

    public String getDeaprtmentName() {
        return deaprtmentName==null?"":deaprtmentName;
    }

    public void setDeaprtmentName(String deaprtmentName) {
        this.deaprtmentName = deaprtmentName;
    }

    public String getPositionId() {
        return positionId==null?"":positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName==null?"":positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUserPhone() {
        return userPhone==null?"":userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getEmergencycontactName() {
        return emergencycontactName==null?"":emergencycontactName;
    }

    public void setEmergencycontactName(String emergencycontactName) {
        this.emergencycontactName = emergencycontactName;
    }

    public String getEmergencycontactPhone() {
        return emergencycontactPhone==null?"":emergencycontactPhone;
    }

    public void setEmergencycontactPhone(String emergencycontactPhone) {
        this.emergencycontactPhone = emergencycontactPhone;
    }

    public String getOfficeDate() {
        return officeDate;
    }

    public void setOfficeDate(String officeDate) {
        this.officeDate = officeDate;
    }

    public String getXianZhu() {
        return xianZhu==null?"":xianZhu;
    }

    public void setXianZhu(String xianZhu) {
        this.xianZhu = xianZhu;
    }

    public int getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(int isDriver) {
        this.isDriver = isDriver;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getDelUser() {
        return delUser;
    }

    public void setDelUser(String delUser) {
        this.delUser = delUser;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getReimbursementRatio() {
        return reimbursementRatio;
    }

    public void setReimbursementRatio(double reimbursementRatio) {
        this.reimbursementRatio = reimbursementRatio;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public int getIsTask() {
        return isTask;
    }

    public void setIsTask(int isTask) {
        this.isTask = isTask;
    }

    public int getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(int positionStatus) {
        this.positionStatus = positionStatus;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
}
