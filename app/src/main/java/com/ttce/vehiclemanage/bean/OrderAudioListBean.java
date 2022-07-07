package com.ttce.vehiclemanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderAudioListBean implements Serializable {

    /**
     * CheckStatus : 0
     * CheckStatusStr : string
     * CheckList : [{"CheckId":0,"CheckUserId":"string","CheckUserName":"string","CheckStatus":0,"CheckStatusStr":"string","CheckReason":"string","CheckTime":"2020-02-17T01:36:03.917Z","CheckTimeStr":"string"}]
     * ApplyId : string
     * CreateTime : 2020-02-17T01:36:03.917Z
     * CreateTimeStr : string
     * UseCarState : 0
     * UseCarStateStr : string
     * CompanyId : string
     * CompanyName : string
     * FleetId : string
     * FleetName : string
     * UserId : string
     * UserPhone : string
     * DriverName : string
     * OrderType : 0
     * OrderTypeStr : string
     * CarType : 0
     * CarTypeStr : string
     * DriverType : 0
     * DriverTypeStr : string
     * Reasons : string
     * WillUseDate : 2020-02-17T01:36:03.917Z
     * WillUseDateStr : string
     * WillReturnDate : 2020-02-17T01:36:03.917Z
     * WillReturnDateStr : string
     * WillMileage : 0
     * Marks : [{"MarkId":0,"Name":"string","Level":0,"AreaName":"string","MarkTime":"2020-02-17T01:36:03.917Z","MarkTimeStr":"string"}]
     * WillCarColor : string
     * WillCarBrand : string
     * WillCarType : string
     * WillCarNum : string
     * WillCarSeatNum : string
     * WillDriverUserId : string
     * WillDriver : string
     * WillDriverTel : string
     * GroupLeader : string
     * GroupLeaderTel : string
     * PepNum : string
     * PepTogether : string
     * Reimbursement : 0
     * ReimbursementStr : string
     * ReimburType : 0
     * ReimburTypeStr : string
     */

    private int CheckStatus;//0:待审核 1：审核通过 2：审核不通过
    private String CheckStatusStr;
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
    private int CarType;
    private String CarTypeStr;
    private int DriverType;
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
    private List<CheckListBean> CheckList;
    private List<MarksBean> Marks;

    public int getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(int checkStatus) {
        CheckStatus = checkStatus;
    }

    public String getCheckStatusStr() {
        return CheckStatusStr == null ? "" : CheckStatusStr;
    }

    public void setCheckStatusStr(String checkStatusStr) {
        CheckStatusStr = checkStatusStr;
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

    public List<CheckListBean> getCheckList() {
        if (CheckList == null) {
            return new ArrayList<>();
        }
        return CheckList;
    }

    public void setCheckList(List<CheckListBean> checkList) {
        CheckList = checkList;
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

    public static class CheckListBean implements Serializable {
        /**
         * CheckId : 0
         * CheckUserId : string
         * CheckUserName : string
         * CheckStatus : 0
         * CheckStatusStr : string
         * CheckReason : string
         * CheckTime : 2020-02-17T01:36:03.917Z
         * CheckTimeStr : string
         */

        private String CheckId;
        private String CheckUserId;
        private String CheckUserName;
        private int CheckStatus;//0:待审核 1：审核通过 2：审核不通过
        private String CheckStatusStr;
        private String CheckReason;
        private String CheckTime;
        private String CheckTimeStr;

        public String getCheckId() {
            return CheckId;
        }

        public void setCheckId(String checkId) {
            CheckId = checkId;
        }

        public String getCheckUserId() {
            return CheckUserId == null ? "" : CheckUserId;
        }

        public void setCheckUserId(String checkUserId) {
            CheckUserId = checkUserId;
        }

        public String getCheckUserName() {
            return CheckUserName == null ? "" : CheckUserName;
        }

        public void setCheckUserName(String checkUserName) {
            CheckUserName = checkUserName;
        }

        public int getCheckStatus() {
            return CheckStatus;
        }

        public void setCheckStatus(int checkStatus) {
            CheckStatus = checkStatus;
        }

        public String getCheckStatusStr() {
            return CheckStatusStr == null ? "" : CheckStatusStr;
        }

        public void setCheckStatusStr(String checkStatusStr) {
            CheckStatusStr = checkStatusStr;
        }

        public String getCheckReason() {
            return CheckReason == null ? "" : CheckReason;
        }

        public void setCheckReason(String checkReason) {
            CheckReason = checkReason;
        }

        public String getCheckTime() {
            return CheckTime == null ? "" : CheckTime;
        }

        public void setCheckTime(String checkTime) {
            CheckTime = checkTime;
        }

        public String getCheckTimeStr() {
            return CheckTimeStr == null ? "" : CheckTimeStr;
        }

        public void setCheckTimeStr(String checkTimeStr) {
            CheckTimeStr = checkTimeStr;
        }
    }

}
