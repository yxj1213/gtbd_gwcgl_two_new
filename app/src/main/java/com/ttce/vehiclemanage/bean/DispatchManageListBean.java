package com.ttce.vehiclemanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DispatchManageListBean implements Serializable {

    /**
     * IsShowDispatch : 1
     * UseCarId : 1057
     * UseDriver : 5e25140a421aa933ddfc9f16
     * GroupStatus : 2
     * GroupType : 0
     * ApplyId : 132239827500746924
     * CreateTime : 2020-01-20T16:32:30
     * CreateTimeStr : 2020-01-20 16:32:30
     * UseCarState : 600
     * UseCarStateStr : 完成
     * CompanyId : e16ccc0a-253b-4db3-846e-c7391d43bd38
     * CompanyName : 用车公司A
     * FleetId : 44428245-573c-4a5e-8adb-43e4f87326aa
     * FleetName : AAA
     * UserId : 1f69b362-cd5d-4eb9-9fe7-d113409bf4c6
     * UserPhone : 123
     * DriverName : sj
     * OrderType : 0
     * OrderTypeStr : 审核用车
     * CarType : 0
     * CarTypeStr : 公车公用
     * DriverType : 1
     * DriverTypeStr : 自驾
     * Reasons : 23232
     * WillUseDate : 2020-01-20T00:00:00
     * WillUseDateStr : 2020-01-20 00:00:00
     * WillReturnDate : 2020-01-21T00:00:00
     * WillReturnDateStr : 2020-01-21 00:00:00
     * WillMileage : 0
     * Marks : [{"MarkId":0,"Name":"3","Level":1,"AreaName":"a","MarkTime":"2020-01-20T17:17:58","MarkTimeStr":"2020-01-20 17:17:58"},{"MarkId":0,"Name":"1","Level":2,"AreaName":"b","MarkTime":"2020-01-20T17:18:36","MarkTimeStr":"2020-01-20 17:18:36"}]
     * WillCarColor : 黑色
     * WillCarBrand : 奥迪
     * WillCarType : 轿车
     * WillCarNum : null
     * WillCarSeatNum : 7座
     * WillDriverUserId : null
     * WillDriver : 司机2号
     * WillDriverTel : 15878784526
     * GroupLeader : 丽丽了
     * GroupLeaderTel : 丽丽了
     * PepNum : 5
     * PepTogether : 丽莉
     * Reimbursement : 1
     * ReimbursementStr : 申请人
     * ReimburType : 0
     * ReimburTypeStr : 不报销
     */
    private int CompanyType;//公司类型 1租车 2借调
    private int CarAff;// 0 自有车辆 1 外调车辆
    private int IsShowDispatch;//0不展示 1展示
    private String UseCarId;
    private String UseDriver;
    private int GroupStatus;//状态：1待指派 2已指派 3已退回
    private int GroupType;//状态类别：0本地 1第三方
    private String ApplyId;
    private String CreateTime;
    private String CreateTimeStr;
    private int UseCarState;
    private String UseCarStateStr;
    private String CompanyId;
    private String CompanyName;
    private String FleetId;
    private String FleetName;
    private String UserId;
    private String UserPhone;
    private String DriverName;
    private int OrderType;
    private String OrderTypeStr;
    private int CarType;// 0公车公用 1私车公用
    private String CarTypeStr;
    private int DriverType;// 驾车方式 0自驾 1代驾
    private String DriverTypeStr;
    private String Reasons;
    private String WillUseDate;
    private String WillUseDateStr;
    private String WillReturnDate;
    private String WillReturnDateStr;
    private double WillMileage;
    private String WillCarColor;
    private String WillCarBrand;
    private String WillCarType;
    private String WillCarNum;
    private String WillCarSeatNum;
    private String WillDriverUserId;
    private String WillDriver;
    private String WillDriverTel;
    private String GroupLeader;
    private String GroupLeaderTel;
    private String PepNum;
    private String PepTogether;
    private int Reimbursement;
    private String ReimbursementStr;
    private int ReimburType;
    private String ReimburTypeStr;
    private List<MarksBean> Marks;

    public int getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(int companyType) {
        CompanyType = companyType;
    }

    public int getCarAff() {
        return CarAff;
    }

    public void setCarAff(int carAff) {
        CarAff = carAff;
    }

    public int getIsShowDispatch() {
        return IsShowDispatch;
    }

    public void setIsShowDispatch(int isShowDispatch) {
        IsShowDispatch = isShowDispatch;
    }

    public String getUseCarId() {
        return UseCarId == null ? "" : UseCarId;
    }

    public void setUseCarId(String useCarId) {
        UseCarId = useCarId;
    }

    public String getUseDriver() {
        return UseDriver == null ? "" : UseDriver;
    }

    public void setUseDriver(String useDriver) {
        UseDriver = useDriver;
    }

    public int getGroupStatus() {
        return GroupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        GroupStatus = groupStatus;
    }

    public int getGroupType() {
        return GroupType;
    }

    public void setGroupType(int groupType) {
        GroupType = groupType;
    }

    public String getApplyId() {
        return ApplyId == null ? "" : ApplyId;
    }

    public void setApplyId(String applyId) {
        ApplyId = applyId;
    }

    public String getCreateTime() {
        return CreateTime == null ? "" : CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateTimeStr() {
        return CreateTimeStr == null ? "" : CreateTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        CreateTimeStr = createTimeStr;
    }

    public int getUseCarState() {
        return UseCarState;
    }

    public void setUseCarState(int useCarState) {
        UseCarState = useCarState;
    }

    public String getUseCarStateStr() {
        return UseCarStateStr == null ? "" : UseCarStateStr;
    }

    public void setUseCarStateStr(String useCarStateStr) {
        UseCarStateStr = useCarStateStr;
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

    public String getFleetId() {
        return FleetId == null ? "" : FleetId;
    }

    public void setFleetId(String fleetId) {
        FleetId = fleetId;
    }

    public String getFleetName() {
        return FleetName == null ? "" : FleetName;
    }

    public void setFleetName(String fleetName) {
        FleetName = fleetName;
    }

    public String getUserId() {
        return UserId == null ? "" : UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserPhone() {
        return UserPhone == null ? "" : UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getDriverName() {
        return DriverName == null ? "" : DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public int getOrderType() {
        return OrderType;
    }

    public void setOrderType(int orderType) {
        OrderType = orderType;
    }

    public String getOrderTypeStr() {
        return OrderTypeStr == null ? "" : OrderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        OrderTypeStr = orderTypeStr;
    }

    public int getCarType() {
        return CarType;
    }

    public void setCarType(int carType) {
        CarType = carType;
    }

    public String getCarTypeStr() {
        return CarTypeStr == null ? "" : CarTypeStr;
    }

    public void setCarTypeStr(String carTypeStr) {
        CarTypeStr = carTypeStr;
    }

    public int getDriverType() {
        return DriverType;
    }

    public void setDriverType(int driverType) {
        DriverType = driverType;
    }

    public String getDriverTypeStr() {
        return DriverTypeStr == null ? "" : DriverTypeStr;
    }

    public void setDriverTypeStr(String driverTypeStr) {
        DriverTypeStr = driverTypeStr;
    }

    public String getReasons() {
        return Reasons == null ? "" : Reasons;
    }

    public void setReasons(String reasons) {
        Reasons = reasons;
    }

    public String getWillUseDate() {
        return WillUseDate == null ? "" : WillUseDate;
    }

    public void setWillUseDate(String willUseDate) {
        WillUseDate = willUseDate;
    }

    public String getWillUseDateStr() {
        return WillUseDateStr == null ? "" : WillUseDateStr;
    }

    public void setWillUseDateStr(String willUseDateStr) {
        WillUseDateStr = willUseDateStr;
    }

    public String getWillReturnDate() {
        return WillReturnDate == null ? "" : WillReturnDate;
    }

    public void setWillReturnDate(String willReturnDate) {
        WillReturnDate = willReturnDate;
    }

    public String getWillReturnDateStr() {
        return WillReturnDateStr == null ? "" : WillReturnDateStr;
    }

    public void setWillReturnDateStr(String willReturnDateStr) {
        WillReturnDateStr = willReturnDateStr;
    }

    public double getWillMileage() {
        return WillMileage;
    }

    public void setWillMileage(double willMileage) {
        WillMileage = willMileage;
    }

    public String getWillCarColor() {
        return WillCarColor == null ? "" : WillCarColor;
    }

    public void setWillCarColor(String willCarColor) {
        WillCarColor = willCarColor;
    }

    public String getWillCarBrand() {
        return WillCarBrand == null ? "" : WillCarBrand;
    }

    public void setWillCarBrand(String willCarBrand) {
        WillCarBrand = willCarBrand;
    }

    public String getWillCarType() {
        return WillCarType == null ? "" : WillCarType;
    }

    public void setWillCarType(String willCarType) {
        WillCarType = willCarType;
    }

    public String getWillCarNum() {
        return WillCarNum == null ? "" : WillCarNum;
    }

    public void setWillCarNum(String willCarNum) {
        WillCarNum = willCarNum;
    }

    public String getWillCarSeatNum() {
        return WillCarSeatNum == null ? "" : WillCarSeatNum;
    }

    public void setWillCarSeatNum(String willCarSeatNum) {
        WillCarSeatNum = willCarSeatNum;
    }

    public String getWillDriverUserId() {
        return WillDriverUserId == null ? "" : WillDriverUserId;
    }

    public void setWillDriverUserId(String willDriverUserId) {
        WillDriverUserId = willDriverUserId;
    }

    public String getWillDriver() {
        return WillDriver == null ? "" : WillDriver;
    }

    public void setWillDriver(String willDriver) {
        WillDriver = willDriver;
    }

    public String getWillDriverTel() {
        return WillDriverTel == null ? "" : WillDriverTel;
    }

    public void setWillDriverTel(String willDriverTel) {
        WillDriverTel = willDriverTel;
    }

    public String getGroupLeader() {
        return GroupLeader == null ? "" : GroupLeader;
    }

    public void setGroupLeader(String groupLeader) {
        GroupLeader = groupLeader;
    }

    public String getGroupLeaderTel() {
        return GroupLeaderTel == null ? "" : GroupLeaderTel;
    }

    public void setGroupLeaderTel(String groupLeaderTel) {
        GroupLeaderTel = groupLeaderTel;
    }

    public String getPepNum() {
        return PepNum == null ? "" : PepNum;
    }

    public void setPepNum(String pepNum) {
        PepNum = pepNum;
    }

    public String getPepTogether() {
        return PepTogether == null ? "" : PepTogether;
    }

    public void setPepTogether(String pepTogether) {
        PepTogether = pepTogether;
    }

    public int getReimbursement() {
        return Reimbursement;
    }

    public void setReimbursement(int reimbursement) {
        Reimbursement = reimbursement;
    }

    public String getReimbursementStr() {
        return ReimbursementStr == null ? "" : ReimbursementStr;
    }

    public void setReimbursementStr(String reimbursementStr) {
        ReimbursementStr = reimbursementStr;
    }

    public int getReimburType() {
        return ReimburType;
    }

    public void setReimburType(int reimburType) {
        ReimburType = reimburType;
    }

    public String getReimburTypeStr() {
        return ReimburTypeStr == null ? "" : ReimburTypeStr;
    }

    public void setReimburTypeStr(String reimburTypeStr) {
        ReimburTypeStr = reimburTypeStr;
    }

    public List<MarksBean> getMarks() {
        if (Marks == null) {
            return new ArrayList<>();
        }
        return Marks;
    }

    public void setMarks(List<MarksBean> marks) {
        Marks = marks;
    }

}
