package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmptyOrderBean {

    @SerializedName("TenantId")
    private String tenantId;
    @SerializedName("TenantUserName")
    private String tenantUserName;
    @SerializedName("CompanyId")
    private String companyId;
    @SerializedName("CompanyName")
    private String companyName;
    @SerializedName("DepartmentId")
    private String departmentId;
    @SerializedName("ApplyStaffId")
    private String applyStaffId;
    @SerializedName("ApplyStaffName")
    private String applyStaffName;
    @SerializedName("ApplyStaffPhone")
    private String applyStaffPhone;
    @SerializedName("ApplyUserId")
    private String applyUserId;
    @SerializedName("UseUserId")
    private String useUserId;
    @SerializedName("UseStaffId")
    private String useStaffId;
    @SerializedName("UseStaffName")
    private String useStaffName;
    @SerializedName("UseStaffPhone")
    private String useStaffPhone;
    @SerializedName("UseMode")
    private int useMode;
    @SerializedName("LineDistance")
    private double lineDistance;
    @SerializedName("GpsDistance")
    private double gpsDistance;
    @SerializedName("ProcessId")
    private String processId;
    @SerializedName("MarkRadius")
    private int markRadius;
    @SerializedName("IsUseMapPoint")
    private boolean isUseMapPoint;
    @SerializedName("UseCarTypeId")
    private String useCarTypeId;
    @SerializedName("UseCarTypeName")
    private String useCarTypeName;
    @SerializedName("DriverType")
    private int driverType;
    @SerializedName("DriverTypeFormat")
    private String driverTypeFormat;
    @SerializedName("DriverUserId")
    private String driverUserId;
    @SerializedName("DriverStaffId")
    private String driverStaffId;
    @SerializedName("DriverStaffName")
    private String driverStaffName;
    @SerializedName("DriverStaffPhone")
    private String driverStaffPhone;
    @SerializedName("ReasonTitle")
    private String reasonTitle;
    @SerializedName("ReasonContent")
    private String reasonContent;
    @SerializedName("WillUseDateTimeType")
    private int willUseDateTimeType;
    @SerializedName("WillUseDateTimeTypeFormat")
    private String willUseDateTimeTypeFormat;
    @SerializedName("WillUseStartDateTime")
    private String willUseStartDateTime;
    @SerializedName("WillUseStartDateTimeFormat")
    private String willUseStartDateTimeFormat;
    @SerializedName("WillUseEndDateTime")
    private String willUseEndDateTime;
    @SerializedName("WillUseEndDateTimeFormat")
    private String willUseEndDateTimeFormat;
    @SerializedName("CarStyleId")
    private String carStyleId;
    @SerializedName("CarStyleName")
    private String carStyleName;
    @SerializedName("CarPlateTypeId")
    private String carPlateTypeId;
    @SerializedName("CarPlateTypeName")
    private String carPlateTypeName;
    @SerializedName("WillUseCarPlate")
    private String willUseCarPlate;
    @SerializedName("WillUseCarSeatNum")
    private int willUseCarSeatNum;
    @SerializedName("LeaderUserId")
    private String leaderUserId;
    @SerializedName("LeaderStaffId")
    private String leaderStaffId;
    @SerializedName("LeaderStaffName")
    private String leaderStaffName;
    @SerializedName("LeaderStaffPhone")
    private String leaderStaffPhone;
    @SerializedName("PassengerNum")
    private int passengerNum;
    @SerializedName("OrderStateModel")
    private Object orderStateModel;
    @SerializedName("OrderState")
    private int orderState;
    @SerializedName("OrderStateFormat")
    private String orderStateFormat;
    @SerializedName("EscapeOrderState")
    private int escapeOrderState;
    @SerializedName("EscapeOrderStateFormat")
    private String escapeOrderStateFormat;
    @SerializedName("UseCarDuration")
    private int useCarDuration;
    @SerializedName("UseCarMileage")
    private int useCarMileage;
    @SerializedName("UseCarFee")
    private int useCarFee;
    @SerializedName("OpenBillState")
    private int openBillState;
    @SerializedName("Remark")
    private String remark;
    @SerializedName("CreateTime")
    private String createTime;
    @SerializedName("CreateTimeFormat")
    private String createTimeFormat;
    @SerializedName("CreateBy")
    private String createBy;
    @SerializedName("CreateByUserName")
    private String createByUserName;
    @SerializedName("UpdateTime")
    private String updateTime;
    @SerializedName("UpdateTimeFormat")
    private String updateTimeFormat;
    @SerializedName("UpdateBy")
    private String updateBy;
    @SerializedName("UpdateByUserName")
    private String updateByUserName;
    @SerializedName("CarFlow_OrderId")
    private String carFlow_OrderId;
    @SerializedName("CarStyle_List")
    private List<CarStyleListBean> carStyle_List;
    @SerializedName("CarPlateType_List")
    private List<CarPlateTypeListBean> carPlateType_List;
    @SerializedName("WillUseDateTimeType_List")
    private List<WillUseDateTimeTypeListBean> willUseDateTimeType_List;
    @SerializedName("CompanyUseCarType_List")
    private List<CompanyUseCarTypeListBean> companyUseCarType_List;
    @SerializedName("DriverType_List_10")
    private List<DriverTypeList10Bean> driverType_List_10;
    @SerializedName("DriverType_List_20")
    private List<DriverTypeList20Bean> driverType_List_20;
    @SerializedName("UseMode_List")
    private List<UseModeListBean> useMode_List;
    @SerializedName("Order_Mark_List")
    private List<OrderMarkListBean> order_Mark_List;
    @SerializedName("Order_Mark_List_Format")
    private String order_Mark_List_Format;
    @SerializedName("Order_Passenger_List")
    private List<OrderPassengerListBean> order_Passenger_List;
    @SerializedName("Order_Car_List")
    private List<OrderCarList> order_Car_List;
    @SerializedName("Dispatch_Grab_List")
    private List<DispatchGrabList> Dispatch_Grab_List;
    @SerializedName("Dispatch_Model")
    private DispatchModel Dispatch_Model;
    @SerializedName("Order_Passenger_List_Format")
    private String order_Passenger_List_Format;
    @SerializedName("CheckType")
    private int checkType;
    @SerializedName("CheckUserId")
    private String checkUserId;
    @SerializedName("CheckStaffId")
    private String checkStaffId;
    @SerializedName("CheckLevel")
    private int checkLevel;
    @SerializedName("CheckState")
    private int checkState;
    @SerializedName("IsHasMark")
    private boolean IsHasMark;

    public boolean isHasMark() {
        return IsHasMark;
    }

    public void setHasMark(boolean hasMark) {
        IsHasMark = hasMark;
    }

    public boolean isUseMapPoint() {
        return isUseMapPoint;
    }

    public void setUseMapPoint(boolean useMapPoint) {
        isUseMapPoint = useMapPoint;
    }

    public DispatchModel getDispatch_Model() {
        return Dispatch_Model;
    }

    public void setDispatch_Model(DispatchModel dispatch_Model) {
        Dispatch_Model = dispatch_Model;
    }

    public List<DispatchGrabList> getDispatch_Grab_List() {
        return Dispatch_Grab_List;
    }

    public void setDispatch_Grab_List(List<DispatchGrabList> dispatch_Grab_List) {
        Dispatch_Grab_List = dispatch_Grab_List;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantUserName() {
        return tenantUserName;
    }

    public void setTenantUserName(String tenantUserName) {
        this.tenantUserName = tenantUserName;
    }

    public String getCompanyId() {
        return companyId;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getApplyStaffId() {
        return applyStaffId;
    }

    public void setApplyStaffId(String applyStaffId) {
        this.applyStaffId = applyStaffId;
    }

    public String getApplyStaffName() {
        return applyStaffName;
    }

    public void setApplyStaffName(String applyStaffName) {
        this.applyStaffName = applyStaffName;
    }

    public String getApplyStaffPhone() {
        return applyStaffPhone;
    }

    public void setApplyStaffPhone(String applyStaffPhone) {
        this.applyStaffPhone = applyStaffPhone;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getUseUserId() {
        return useUserId;
    }

    public void setUseUserId(String useUserId) {
        this.useUserId = useUserId;
    }

    public String getUseStaffId() {
        return useStaffId;
    }

    public void setUseStaffId(String useStaffId) {
        this.useStaffId = useStaffId;
    }

    public String getUseStaffName() {
        return useStaffName;
    }

    public void setUseStaffName(String useStaffName) {
        this.useStaffName = useStaffName;
    }

    public String getUseStaffPhone() {
        return useStaffPhone==null?"暂无联系电话":useStaffPhone;
    }

    public void setUseStaffPhone(String useStaffPhone) {
        this.useStaffPhone = useStaffPhone;
    }

    public int getUseMode() {
        return useMode;
    }

    public void setUseMode(int useMode) {
        this.useMode = useMode;
    }

    public double getLineDistance() {
        return lineDistance;
    }

    public void setLineDistance(double lineDistance) {
        this.lineDistance = lineDistance;
    }

    public double getGpsDistance() {
        return gpsDistance;
    }

    public void setGpsDistance(double gpsDistance) {
        this.gpsDistance = gpsDistance;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getMarkRadius() {
        return markRadius;
    }

    public void setMarkRadius(int markRadius) {
        this.markRadius = markRadius;
    }

    public String getUseCarTypeId() {
        return useCarTypeId;
    }

    public void setUseCarTypeId(String useCarTypeId) {
        this.useCarTypeId = useCarTypeId;
    }

    public String getUseCarTypeName() {
        return useCarTypeName==null?"":useCarTypeName;
    }

    public void setUseCarTypeName(String useCarTypeName) {
        this.useCarTypeName = useCarTypeName;
    }

    public int getDriverType() {
        return driverType;
    }

    public void setDriverType(int driverType) {
        this.driverType = driverType;
    }

    public String getDriverTypeFormat() {
        return driverTypeFormat;
    }

    public void setDriverTypeFormat(String driverTypeFormat) {
        this.driverTypeFormat = driverTypeFormat;
    }

    public String getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(String driverUserId) {
        this.driverUserId = driverUserId;
    }

    public String getDriverStaffId() {
        return driverStaffId;
    }

    public void setDriverStaffId(String driverStaffId) {
        this.driverStaffId = driverStaffId;
    }

    public String getDriverStaffName() {
        return driverStaffName==null?"":driverStaffName;
    }

    public void setDriverStaffName(String driverStaffName) {
        this.driverStaffName = driverStaffName;
    }

    public String getDriverStaffPhone() {
        return driverStaffPhone;
    }

    public void setDriverStaffPhone(String driverStaffPhone) {
        this.driverStaffPhone = driverStaffPhone;
    }

    public String getReasonTitle() {
        return reasonTitle==null?"":reasonTitle;
    }

    public void setReasonTitle(String reasonTitle) {
        this.reasonTitle = reasonTitle;
    }

    public String getReasonContent() {
        return reasonContent==null?"":reasonContent;
    }

    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }

    public int getWillUseDateTimeType() {
        return willUseDateTimeType;
    }

    public void setWillUseDateTimeType(int willUseDateTimeType) {
        this.willUseDateTimeType = willUseDateTimeType;
    }

    public String getWillUseDateTimeTypeFormat() {
        return willUseDateTimeTypeFormat;
    }

    public void setWillUseDateTimeTypeFormat(String willUseDateTimeTypeFormat) {
        this.willUseDateTimeTypeFormat = willUseDateTimeTypeFormat;
    }

    public String getWillUseStartDateTime() {
        return willUseStartDateTime;
    }

    public void setWillUseStartDateTime(String willUseStartDateTime) {
        this.willUseStartDateTime = willUseStartDateTime;
    }

    public String getWillUseStartDateTimeFormat() {
        return willUseStartDateTimeFormat;
    }

    public void setWillUseStartDateTimeFormat(String willUseStartDateTimeFormat) {
        this.willUseStartDateTimeFormat = willUseStartDateTimeFormat;
    }

    public String getWillUseEndDateTime() {
        return willUseEndDateTime;
    }

    public void setWillUseEndDateTime(String willUseEndDateTime) {
        this.willUseEndDateTime = willUseEndDateTime;
    }

    public String getWillUseEndDateTimeFormat() {
        return willUseEndDateTimeFormat;
    }

    public void setWillUseEndDateTimeFormat(String willUseEndDateTimeFormat) {
        this.willUseEndDateTimeFormat = willUseEndDateTimeFormat;
    }

    public String getCarStyleId() {
        return carStyleId;
    }

    public void setCarStyleId(String carStyleId) {
        this.carStyleId = carStyleId;
    }

    public String getCarStyleName() {
        return carStyleName==null?"":carStyleName;
    }

    public void setCarStyleName(String carStyleName) {
        this.carStyleName = carStyleName;
    }

    public String getCarPlateTypeId() {
        return carPlateTypeId;
    }

    public void setCarPlateTypeId(String carPlateTypeId) {
        this.carPlateTypeId = carPlateTypeId;
    }

    public String getCarPlateTypeName() {
        return carPlateTypeName;
    }

    public void setCarPlateTypeName(String carPlateTypeName) {
        this.carPlateTypeName = carPlateTypeName;
    }

    public String getWillUseCarPlate() {
        return willUseCarPlate==null?"":willUseCarPlate;
    }

    public void setWillUseCarPlate(String willUseCarPlate) {
        this.willUseCarPlate = willUseCarPlate;
    }

    public int getWillUseCarSeatNum() {
        return willUseCarSeatNum;
    }

    public void setWillUseCarSeatNum(int willUseCarSeatNum) {
        this.willUseCarSeatNum = willUseCarSeatNum;
    }

    public String getLeaderUserId() {
        return leaderUserId;
    }

    public void setLeaderUserId(String leaderUserId) {
        this.leaderUserId = leaderUserId;
    }

    public String getLeaderStaffId() {
        return leaderStaffId;
    }

    public void setLeaderStaffId(String leaderStaffId) {
        this.leaderStaffId = leaderStaffId;
    }

    public String getLeaderStaffName() {
        return leaderStaffName==null?"":leaderStaffName;
    }

    public void setLeaderStaffName(String leaderStaffName) {
        this.leaderStaffName = leaderStaffName;
    }

    public String getLeaderStaffPhone() {
        return leaderStaffPhone==null?"":leaderStaffPhone;
    }

    public void setLeaderStaffPhone(String leaderStaffPhone) {
        this.leaderStaffPhone = leaderStaffPhone;
    }

    public int getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }

    public Object getOrderStateModel() {
        return orderStateModel;
    }

    public void setOrderStateModel(Object orderStateModel) {
        this.orderStateModel = orderStateModel;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderStateFormat() {
        return orderStateFormat;
    }

    public void setOrderStateFormat(String orderStateFormat) {
        this.orderStateFormat = orderStateFormat;
    }

    public int getEscapeOrderState() {
        return escapeOrderState;
    }

    public void setEscapeOrderState(int escapeOrderState) {
        this.escapeOrderState = escapeOrderState;
    }

    public String getEscapeOrderStateFormat() {
        return escapeOrderStateFormat;
    }

    public void setEscapeOrderStateFormat(String escapeOrderStateFormat) {
        this.escapeOrderStateFormat = escapeOrderStateFormat;
    }

    public int getUseCarDuration() {
        return useCarDuration;
    }

    public void setUseCarDuration(int useCarDuration) {
        this.useCarDuration = useCarDuration;
    }

    public int getUseCarMileage() {
        return useCarMileage;
    }

    public void setUseCarMileage(int useCarMileage) {
        this.useCarMileage = useCarMileage;
    }

    public int getUseCarFee() {
        return useCarFee;
    }

    public void setUseCarFee(int useCarFee) {
        this.useCarFee = useCarFee;
    }

    public int getOpenBillState() {
        return openBillState;
    }

    public void setOpenBillState(int openBillState) {
        this.openBillState = openBillState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByUserName() {
        return createByUserName;
    }

    public void setCreateByUserName(String createByUserName) {
        this.createByUserName = createByUserName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeFormat() {
        return updateTimeFormat;
    }

    public void setUpdateTimeFormat(String updateTimeFormat) {
        this.updateTimeFormat = updateTimeFormat;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByUserName() {
        return updateByUserName;
    }

    public void setUpdateByUserName(String updateByUserName) {
        this.updateByUserName = updateByUserName;
    }

    public String getCarFlow_OrderId() {
        return carFlow_OrderId==null?"":carFlow_OrderId;
    }

    public void setCarFlow_OrderId(String carFlow_OrderId) {
        this.carFlow_OrderId = carFlow_OrderId;
    }

    public List<CarStyleListBean> getCarStyle_List() {
        return carStyle_List;
    }

    public void setCarStyle_List(List<CarStyleListBean> carStyle_List) {
        this.carStyle_List = carStyle_List;
    }

    public List<CarPlateTypeListBean> getCarPlateType_List() {
        return carPlateType_List;
    }

    public void setCarPlateType_List(List<CarPlateTypeListBean> carPlateType_List) {
        this.carPlateType_List = carPlateType_List;
    }

    public List<WillUseDateTimeTypeListBean> getWillUseDateTimeType_List() {
        return willUseDateTimeType_List;
    }

    public void setWillUseDateTimeType_List(List<WillUseDateTimeTypeListBean> willUseDateTimeType_List) {
        this.willUseDateTimeType_List = willUseDateTimeType_List;
    }

    public List<CompanyUseCarTypeListBean> getCompanyUseCarType_List() {
        return companyUseCarType_List;
    }

    public void setCompanyUseCarType_List(List<CompanyUseCarTypeListBean> companyUseCarType_List) {
        this.companyUseCarType_List = companyUseCarType_List;
    }

    public List<DriverTypeList10Bean> getDriverType_List_10() {
        return driverType_List_10;
    }

    public void setDriverType_List_10(List<DriverTypeList10Bean> driverType_List_10) {
        this.driverType_List_10 = driverType_List_10;
    }

    public List<DriverTypeList20Bean> getDriverType_List_20() {
        return driverType_List_20;
    }

    public void setDriverType_List_20(List<DriverTypeList20Bean> driverType_List_20) {
        this.driverType_List_20 = driverType_List_20;
    }

    public List<UseModeListBean> getUseMode_List() {
        return useMode_List;
    }

    public void setUseMode_List(List<UseModeListBean> useMode_List) {
        this.useMode_List = useMode_List;
    }

    public List<OrderMarkListBean> getOrder_Mark_List() {
        return order_Mark_List;
    }

    public void setOrder_Mark_List(List<OrderMarkListBean> order_Mark_List) {
        this.order_Mark_List = order_Mark_List;
    }

    public String getOrder_Mark_List_Format() {
        return order_Mark_List_Format;
    }

    public void setOrder_Mark_List_Format(String order_Mark_List_Format) {
        this.order_Mark_List_Format = order_Mark_List_Format;
    }

    public List<OrderPassengerListBean> getOrder_Passenger_List() {
        return order_Passenger_List;
    }

    public void setOrder_Passenger_List(List<OrderPassengerListBean> order_Passenger_List) {
        this.order_Passenger_List = order_Passenger_List;
    }

    public List<OrderCarList> getOrder_Car_List() {
        return order_Car_List;
    }

    public void setOrder_Car_List(List<OrderCarList> order_Car_List) {
        this.order_Car_List = order_Car_List;
    }

    public String getOrder_Passenger_List_Format() {
        return order_Passenger_List_Format;
    }

    public void setOrder_Passenger_List_Format(String order_Passenger_List_Format) {
        this.order_Passenger_List_Format = order_Passenger_List_Format;
    }

    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(int checkType) {
        this.checkType = checkType;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getCheckStaffId() {
        return checkStaffId;
    }

    public void setCheckStaffId(String checkStaffId) {
        this.checkStaffId = checkStaffId;
    }

    public int getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(int checkLevel) {
        this.checkLevel = checkLevel;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public static class OrderMarkListBean {
        @SerializedName("OrderId")
        private String orderId;
        @SerializedName("NeedMarkTitle")
        private String needMarkTitle;
        @SerializedName("NeedMarkSimpleAddress")
        private String needMarkSimpleAddress;
        @SerializedName("NeedMarkFullAddress")
        private String needMarkFullAddress;
        @SerializedName("NeedMarkLng")
        private double needMarkLng;
        @SerializedName("NeedMarkLat")
        private double needMarkLat;
        @SerializedName("LinkName")
        private String linkName;
        @SerializedName("LinkPhone")
        private String linkPhone;
        @SerializedName("MarkLevel")
        private int markLevel;
        @SerializedName("MarkType")
        private int markType;
        @SerializedName("MarkTypeFormat")
        private String markTypeFormat;
        @SerializedName("RealMarkSimpleAddress")
        private String realMarkSimpleAddress;
        @SerializedName("RealMarkFullAddress")
        private String realMarkFullAddress;
        @SerializedName("RealMarkLng")
        private double realMarkLng;
        @SerializedName("RealMarkLat")
        private double realMarkLat;
        @SerializedName("RealMarkUserId")
        private String realMarkUserId;
        @SerializedName("RealMarkStaffId")
        private String realMarkStaffId;
        @SerializedName("RealMarkTime")
        private String realMarkTime;
        @SerializedName("IsMark")
        private boolean isMark;
        @SerializedName("CreateTime")
        private String createTime;
        @SerializedName("CreateTimeFormat")
        private String createTimeFormat;
        @SerializedName("CreateBy")
        private String createBy;
        @SerializedName("CreateByUserName")
        private String createByUserName;
        @SerializedName("UpdateTime")
        private String updateTime;
        @SerializedName("UpdateTimeFormat")
        private String updateTimeFormat;
        @SerializedName("UpdateBy")
        private String updateBy;
        @SerializedName("UpdateByUserName")
        private String updateByUserName;
        @SerializedName("CarFlow_Order_MarkId")
        private String carFlow_Order_MarkId;
        private boolean isMarkCircleRadius;//当前车的位置是否在打卡范围内
        private double lineDistance;//两点间的直线距离

        public OrderMarkListBean() {
        }

        public OrderMarkListBean(String markTypeFormat) {
            this.markTypeFormat = markTypeFormat;
        }

        public double getLineDistance() {
            return lineDistance;
        }

        public void setLineDistance(double lineDistance) {
            this.lineDistance = lineDistance;
        }

        public boolean isMarkCircleRadius() {
            return isMarkCircleRadius;
        }

        public void setMarkCircleRadius(boolean markCircleRadius) {
            isMarkCircleRadius = markCircleRadius;
        }

        public boolean isMark() {
            return isMark;
        }

        public void setMark(boolean mark) {
            isMark = mark;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getNeedMarkTitle() {
            return needMarkTitle==null?"":needMarkTitle;
        }

        public void setNeedMarkTitle(String needMarkTitle) {
            this.needMarkTitle = needMarkTitle;
        }

        public String getNeedMarkSimpleAddress() {
            return needMarkSimpleAddress==null?"":needMarkSimpleAddress;
        }

        public void setNeedMarkSimpleAddress(String needMarkSimpleAddress) {
            this.needMarkSimpleAddress = needMarkSimpleAddress;
        }

        public String getNeedMarkFullAddress() {
            return needMarkFullAddress==null?"":needMarkFullAddress;
        }

        public void setNeedMarkFullAddress(String needMarkFullAddress) {
            this.needMarkFullAddress = needMarkFullAddress;
        }

        public double getNeedMarkLng() {
            return needMarkLng;
        }

        public void setNeedMarkLng(double needMarkLng) {
            this.needMarkLng = needMarkLng;
        }

        public double getNeedMarkLat() {
            return needMarkLat;
        }

        public void setNeedMarkLat(double needMarkLat) {
            this.needMarkLat = needMarkLat;
        }

        public String getLinkName() {
            return linkName==null?"":linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getLinkPhone() {
            return linkPhone==null?"":linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public int getMarkLevel() {
            return markLevel;
        }

        public void setMarkLevel(int markLevel) {
            this.markLevel = markLevel;
        }

        public int getMarkType() {
            return markType;
        }

        public void setMarkType(int markType) {
            this.markType = markType;
        }

        public String getMarkTypeFormat() {
            return markTypeFormat;
        }

        public void setMarkTypeFormat(String markTypeFormat) {
            this.markTypeFormat = markTypeFormat;
        }

        public String getRealMarkSimpleAddress() {
            return realMarkSimpleAddress;
        }

        public void setRealMarkSimpleAddress(String realMarkSimpleAddress) {
            this.realMarkSimpleAddress = realMarkSimpleAddress;
        }

        public String getRealMarkFullAddress() {
            return realMarkFullAddress==null?"":realMarkFullAddress;
        }

        public void setRealMarkFullAddress(String realMarkFullAddress) {
            this.realMarkFullAddress = realMarkFullAddress;
        }

        public double getRealMarkLng() {
            return realMarkLng;
        }

        public void setRealMarkLng(double realMarkLng) {
            this.realMarkLng = realMarkLng;
        }

        public double getRealMarkLat() {
            return realMarkLat;
        }

        public void setRealMarkLat(double realMarkLat) {
            this.realMarkLat = realMarkLat;
        }

        public String getRealMarkUserId() {
            return realMarkUserId;
        }

        public void setRealMarkUserId(String realMarkUserId) {
            this.realMarkUserId = realMarkUserId;
        }

        public String getRealMarkStaffId() {
            return realMarkStaffId;
        }

        public void setRealMarkStaffId(String realMarkStaffId) {
            this.realMarkStaffId = realMarkStaffId;
        }

        public String getRealMarkTime() {
            return realMarkTime;
        }

        public void setRealMarkTime(String realMarkTime) {
            this.realMarkTime = realMarkTime;
        }

        public boolean isIsMark() {
            return isMark;
        }

        public void setIsMark(boolean isMark) {
            this.isMark = isMark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(String createByUserName) {
            this.createByUserName = createByUserName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(String updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(String updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Order_MarkId() {
            return carFlow_Order_MarkId;
        }

        public void setCarFlow_Order_MarkId(String carFlow_Order_MarkId) {
            this.carFlow_Order_MarkId = carFlow_Order_MarkId;
        }
    }

    public static class CarStyleListBean{
        @SerializedName("Name")
        private String name;
        @SerializedName("Sort")
        private int sort;
        @SerializedName("ImageUrl")
        private String imageUrl;
        @SerializedName("MinSeatNum")
        private int minSeatNum;
        @SerializedName("MaxSeatNum")
        private int maxSeatNum;
        @SerializedName("CreateTime")
        private String createTime;
        @SerializedName("CreateTimeFormat")
        private String createTimeFormat;
        @SerializedName("CreateBy")
        private String createBy;
        @SerializedName("CreateByUserName")
        private String createByUserName;
        @SerializedName("UpdateTime")
        private String updateTime;
        @SerializedName("UpdateTimeFormat")
        private String updateTimeFormat;
        @SerializedName("UpdateBy")
        private String updateBy;
        @SerializedName("UpdateByUserName")
        private String updateByUserName;
        @SerializedName("CarFlow_CarStyleId")
        private String carFlow_CarStyleId;
        @SerializedName("IsBind")
        private boolean isBind;
        @SerializedName("IsSelected")
        private boolean isSelected;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMinSeatNum() {
            return minSeatNum;
        }

        public void setMinSeatNum(int minSeatNum) {
            this.minSeatNum = minSeatNum;
        }

        public int getMaxSeatNum() {
            return maxSeatNum;
        }

        public void setMaxSeatNum(int maxSeatNum) {
            this.maxSeatNum = maxSeatNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(String createByUserName) {
            this.createByUserName = createByUserName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(String updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(String updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_CarStyleId() {
            return carFlow_CarStyleId;
        }

        public void setCarFlow_CarStyleId(String carFlow_CarStyleId) {
            this.carFlow_CarStyleId = carFlow_CarStyleId;
        }

        public boolean isIsBind() {
            return isBind;
        }

        public void setIsBind(boolean isBind) {
            this.isBind = isBind;
        }

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }
    public static class DriverTypeList10Bean{

        @SerializedName("IsSelected")
        private boolean isSelected;
        @SerializedName("Disabled")
        private boolean disabled;
        @SerializedName("ParentId")
        private String parentId;
        @SerializedName("ICon")
        private String iCon;
        @SerializedName("Text")
        private String text;
        @SerializedName("Value")
        private int value;

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getICon() {
            return iCon;
        }

        public void setICon(String iCon) {
            this.iCon = iCon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    public static class DriverTypeList20Bean {

        @SerializedName("IsSelected")
        private boolean isSelected;
        @SerializedName("Disabled")
        private boolean disabled;
        @SerializedName("ParentId")
        private String parentId;
        @SerializedName("ICon")
        private String iCon;
        @SerializedName("Text")
        private String text;
        @SerializedName("Value")
        private int value;

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getICon() {
            return iCon;
        }

        public void setICon(String iCon) {
            this.iCon = iCon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    public static class UseModeListBean{

        @SerializedName("IsSelected")
        private boolean isSelected;
        @SerializedName("Disabled")
        private boolean disabled;
        @SerializedName("ParentId")
        private String parentId;
        @SerializedName("ICon")
        private String iCon;
        @SerializedName("Text")
        private String text;
        @SerializedName("Value")
        private String value;

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public Object getICon() {
            return iCon;
        }

        public void setICon(String iCon) {
            this.iCon = iCon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public static class CompanyUseCarTypeListBean{

        @SerializedName("ProcessId")
        private String processId;
        @SerializedName("IsUseMapPoint")
        private boolean isUseMapPoint;
        @SerializedName("MarkRadius")
        private int markRadius;
        @SerializedName("IsWillCarPlate")
        private boolean isWillCarPlate;
        @SerializedName("Sort")
        private int sort;
        @SerializedName("UseCarTypeName")
        private String useCarTypeName;
        @SerializedName("UseCarTypeId")
        private String useCarTypeId;
        @SerializedName("IsDefault")
        private boolean isDefault;
        @SerializedName("CreateTime")
        private String createTime;
        @SerializedName("CreateTimeFormat")
        private String createTimeFormat;
        @SerializedName("CreateBy")
        private String createBy;
        @SerializedName("CreateByUserName")
        private String createByUserName;
        @SerializedName("UpdateTime")
        private String updateTime;
        @SerializedName("UpdateTimeFormat")
        private String updateTimeFormat;
        @SerializedName("UpdateBy")
        private String updateBy;
        @SerializedName("UpdateByUserName")
        private String updateByUserName;
        @SerializedName("CarFlow_Process_CompanyUseCarTypeId")
        private String carFlow_Process_CompanyUseCarTypeId;

        public String getProcessId() {
            return processId;
        }

        public void setProcessId(String processId) {
            this.processId = processId;
        }

        public boolean isIsUseMapPoint() {
            return isUseMapPoint;
        }

        public void setIsUseMapPoint(boolean isUseMapPoint) {
            this.isUseMapPoint = isUseMapPoint;
        }

        public int getMarkRadius() {
            return markRadius;
        }

        public void setMarkRadius(int markRadius) {
            this.markRadius = markRadius;
        }

        public boolean isIsWillCarPlate() {
            return isWillCarPlate;
        }

        public void setIsWillCarPlate(boolean isWillCarPlate) {
            this.isWillCarPlate = isWillCarPlate;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUseCarTypeName() {
            return useCarTypeName;
        }

        public void setUseCarTypeName(String useCarTypeName) {
            this.useCarTypeName = useCarTypeName;
        }

        public String getUseCarTypeId() {
            return useCarTypeId;
        }

        public void setUseCarTypeId(String useCarTypeId) {
            this.useCarTypeId = useCarTypeId;
        }

        public boolean isIsDefault() {
            return isDefault;
        }

        public void setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(String createByUserName) {
            this.createByUserName = createByUserName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(String updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(String updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Process_CompanyUseCarTypeId() {
            return carFlow_Process_CompanyUseCarTypeId;
        }

        public void setCarFlow_Process_CompanyUseCarTypeId(String carFlow_Process_CompanyUseCarTypeId) {
            this.carFlow_Process_CompanyUseCarTypeId = carFlow_Process_CompanyUseCarTypeId;
        }
    }
    public static class OrderPassengerListBean{
        @SerializedName("OrderId")
        public String orderId;
        @SerializedName("PassengerName")
        public String passengerName;
        @SerializedName("PassengerPhone")
        public String passengerPhone;
        @SerializedName("PositionName")
        public String positionName;
        @SerializedName("CreateTime")
        public String createTime;
        @SerializedName("CreateTimeFormat")
        public String createTimeFormat;
        @SerializedName("CreateBy")
        public String createBy;
        @SerializedName("CreateByUserName")
        public String createByUserName;
        @SerializedName("UpdateTime")
        public String updateTime;
         @SerializedName("UpdateTimeFormat")
        public String updateTimeFormat;
         @SerializedName("UpdateBy")
        public String updateBy;
        @SerializedName("UpdateByUserName")
        public String updateByUserName;
        @SerializedName("CarFlow_Order_PassengerId")
        public String carFlow_Order_PassengerId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public String getPassengerPhone() {
            return passengerPhone;
        }

        public void setPassengerPhone(String passengerPhone) {
            this.passengerPhone = passengerPhone;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(String createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(String createByUserName) {
            this.createByUserName = createByUserName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(String updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(String updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Order_PassengerId() {
            return carFlow_Order_PassengerId;
        }

        public void setCarFlow_Order_PassengerId(String carFlow_Order_PassengerId) {
            this.carFlow_Order_PassengerId = carFlow_Order_PassengerId;
        }
    }
    public static class WillUseDateTimeTypeListBean {

        @SerializedName("IsSelected")
        private boolean isSelected;
        @SerializedName("Disabled")
        private boolean disabled;
        @SerializedName("ParentId")
        private String parentId;
        @SerializedName("ICon")
        private String iCon;
        @SerializedName("Text")
        private String text;
        @SerializedName("Value")
        private int value;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getiCon() {
            return iCon;
        }

        public void setiCon(String iCon) {
            this.iCon = iCon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    public static class DispatchGrabList{

        @SerializedName("OrderId")
        private String orderId;
        @SerializedName("AssignsType")
        private int assignsType;
        @SerializedName("GrabUserCompanyId")
        private String grabUserCompanyId;
        @SerializedName("GrabUserDepartmentId")
        private String grabUserDepartmentId;
        @SerializedName("GrabUserId")
        private String grabUserId;
        @SerializedName("GrabStaffId")
        private String grabStaffId;
        @SerializedName("GrabStaffName")
        private String grabStaffName;
        @SerializedName("GrabCarCompanyId")
        private String grabCarCompanyId;
        @SerializedName("GrabCarDepartmentId")
        private String grabCarDepartmentId;
        @SerializedName("GrabCarId")
        private String grabCarId;
        @SerializedName("GrabCarPlateNumber")
        private String grabCarPlateNumber;
        @SerializedName("GrabCarStyleId")
        private String GrabCarStyleId;
        @SerializedName("GrabCarStyleName")
        private String GrabCarStyleName;
        @SerializedName("GrabTime")
        private String grabTime;
        @SerializedName("IsComplete")
        private boolean isComplete;
        @SerializedName("GrabState")
        private int grabState;
        @SerializedName("CreateTime")
        private Object createTime;
        @SerializedName("CreateTimeFormat")
        private Object createTimeFormat;
        @SerializedName("CreateBy")
        private Object createBy;
        @SerializedName("CreateByUserName")
        private Object createByUserName;
        @SerializedName("UpdateTime")
        private Object updateTime;
        @SerializedName("UpdateTimeFormat")
        private Object updateTimeFormat;
        @SerializedName("UpdateBy")
        private Object updateBy;
        @SerializedName("UpdateByUserName")
        private Object updateByUserName;
        @SerializedName("CarFlow_Dispatch_GrabId")
        private String carFlow_Dispatch_GrabId;

        public String getGrabCarStyleId() {
            return GrabCarStyleId;
        }

        public void setGrabCarStyleId(String grabCarStyleId) {
            GrabCarStyleId = grabCarStyleId;
        }

        public String getGrabCarStyleName() {
            return GrabCarStyleName==null?"":GrabCarStyleName;
        }

        public void setGrabCarStyleName(String grabCarStyleName) {
            GrabCarStyleName = grabCarStyleName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getAssignsType() {
            return assignsType;
        }

        public void setAssignsType(int assignsType) {
            this.assignsType = assignsType;
        }

        public String getGrabUserCompanyId() {
            return grabUserCompanyId;
        }

        public void setGrabUserCompanyId(String grabUserCompanyId) {
            this.grabUserCompanyId = grabUserCompanyId;
        }

        public String getGrabUserDepartmentId() {
            return grabUserDepartmentId;
        }

        public void setGrabUserDepartmentId(String grabUserDepartmentId) {
            this.grabUserDepartmentId = grabUserDepartmentId;
        }

        public String getGrabUserId() {
            return grabUserId;
        }

        public void setGrabUserId(String grabUserId) {
            this.grabUserId = grabUserId;
        }

        public String getGrabStaffId() {
            return grabStaffId;
        }

        public void setGrabStaffId(String grabStaffId) {
            this.grabStaffId = grabStaffId;
        }

        public String getGrabStaffName() {
            return grabStaffName==null?"":grabStaffName;
        }

        public void setGrabStaffName(String grabStaffName) {
            this.grabStaffName = grabStaffName;
        }

        public String getGrabCarCompanyId() {
            return grabCarCompanyId;
        }

        public void setGrabCarCompanyId(String grabCarCompanyId) {
            this.grabCarCompanyId = grabCarCompanyId;
        }

        public String getGrabCarDepartmentId() {
            return grabCarDepartmentId;
        }

        public void setGrabCarDepartmentId(String grabCarDepartmentId) {
            this.grabCarDepartmentId = grabCarDepartmentId;
        }

        public String getGrabCarId() {
            return grabCarId;
        }

        public void setGrabCarId(String grabCarId) {
            this.grabCarId = grabCarId;
        }

        public String getGrabCarPlateNumber() {
            return grabCarPlateNumber==null?"":grabCarPlateNumber;
        }

        public void setGrabCarPlateNumber(String grabCarPlateNumber) {
            this.grabCarPlateNumber = grabCarPlateNumber;
        }

        public String getGrabTime() {
            return grabTime;
        }

        public void setGrabTime(String grabTime) {
            this.grabTime = grabTime;
        }

        public boolean isIsComplete() {
            return isComplete;
        }

        public void setIsComplete(boolean isComplete) {
            this.isComplete = isComplete;
        }

        public int getGrabState() {
            return grabState;
        }

        public void setGrabState(int grabState) {
            this.grabState = grabState;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(Object createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(Object createByUserName) {
            this.createByUserName = createByUserName;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(Object updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(Object updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Dispatch_GrabId() {
            return carFlow_Dispatch_GrabId;
        }

        public void setCarFlow_Dispatch_GrabId(String carFlow_Dispatch_GrabId) {
            this.carFlow_Dispatch_GrabId = carFlow_Dispatch_GrabId;
        }
    }
    public static class DispatchModel{

        @SerializedName("OrderId")
        private String orderId;
        @SerializedName("AssignsType")
        private int assignsType;
        @SerializedName("GrabStartTime")
        private String grabStartTime;
        @SerializedName("GrabEndTime")
        private Object grabEndTime;
        @SerializedName("DispatchState")
        private int dispatchState;
        @SerializedName("DispatchStateFormat")
        private String dispatchStateFormat;
        @SerializedName("CreateTime")
        private Object createTime;
        @SerializedName("CreateTimeFormat")
        private Object createTimeFormat;
        @SerializedName("CreateBy")
        private Object createBy;
        @SerializedName("CreateByUserName")
        private Object createByUserName;
        @SerializedName("UpdateTime")
        private Object updateTime;
        @SerializedName("UpdateTimeFormat")
        private Object updateTimeFormat;
        @SerializedName("UpdateBy")
        private Object updateBy;
        @SerializedName("UpdateByUserName")
        private Object updateByUserName;
        @SerializedName("CarFlow_DispatchId")
        private String carFlow_DispatchId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getAssignsType() {
            return assignsType;
        }

        public void setAssignsType(int assignsType) {
            this.assignsType = assignsType;
        }

        public String getGrabStartTime() {
            return grabStartTime;
        }

        public void setGrabStartTime(String grabStartTime) {
            this.grabStartTime = grabStartTime;
        }

        public Object getGrabEndTime() {
            return grabEndTime;
        }

        public void setGrabEndTime(Object grabEndTime) {
            this.grabEndTime = grabEndTime;
        }

        public int getDispatchState() {
            return dispatchState;
        }

        public void setDispatchState(int dispatchState) {
            this.dispatchState = dispatchState;
        }

        public String getDispatchStateFormat() {
            return dispatchStateFormat;
        }

        public void setDispatchStateFormat(String dispatchStateFormat) {
            this.dispatchStateFormat = dispatchStateFormat;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(Object createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(Object createByUserName) {
            this.createByUserName = createByUserName;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(Object updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(Object updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_DispatchId() {
            return carFlow_DispatchId;
        }

        public void setCarFlow_DispatchId(String carFlow_DispatchId) {
            this.carFlow_DispatchId = carFlow_DispatchId;
        }
    }
    public static class OrderCarList{

        @SerializedName("OrderId")
        private String orderId;
        @SerializedName("CarId")
        private String carId;
        @SerializedName("DeviceId")
        private String deviceId;
        @SerializedName("CarPlateNumber")
        private String carPlateNumber;
        @SerializedName("CarStyleId")
        private String carStyleId;
        @SerializedName("CarStyleName")
        private String carStyleName;
        @SerializedName("DriverStaffId")
        private String driverStaffId;
        @SerializedName("DriverStaffName")
        private String driverStaffName;
        @SerializedName("DriverUserId")
        private String driverUserId;
        @SerializedName("TenantId")
        private String tenantId;
        @SerializedName("TenantUserName")
        private Object tenantUserName;
        @SerializedName("CompanyId")
        private String companyId;
        @SerializedName("CompanyName")
        private Object companyName;
        @SerializedName("StartNum")
        private Object startNum;
        @SerializedName("StartNumImg")
        private Object startNumImg;
        @SerializedName("EndNum")
        private Object endNum;
        @SerializedName("EndNumImg")
        private Object endNumImg;
        @SerializedName("StartGPSNum")
        private Object startGPSNum;
        @SerializedName("EndGPSNum")
        private double endGPSNum;
        @SerializedName("StartTime")
        private Object startTime;
        @SerializedName("EndTime")
        private Object endTime;
        @SerializedName("ReturnTime")
        private Object returnTime;
        @SerializedName("ReturnCarReason")
        private Object returnCarReason;
        @SerializedName("ReturnCarSimpleAddress")
        private Object returnCarSimpleAddress;
        @SerializedName("ReturnCarFullAddress")
        private Object returnCarFullAddress;
        @SerializedName("ReturnCarLng")
        private Object returnCarLng;
        @SerializedName("ReturnCarLat")
        private Object returnCarLat;
        @SerializedName("CreateTime")
        private Object createTime;
        @SerializedName("CreateTimeFormat")
        private Object createTimeFormat;
        @SerializedName("CreateBy")
        private Object createBy;
        @SerializedName("CreateByUserName")
        private Object createByUserName;
        @SerializedName("UpdateTime")
        private Object updateTime;
        @SerializedName("UpdateTimeFormat")
        private Object updateTimeFormat;
        @SerializedName("UpdateBy")
        private Object updateBy;
        @SerializedName("UpdateByUserName")
        private Object updateByUserName;
        @SerializedName("CarFlow_Order_CarId")
        private String carFlow_Order_CarId;

        public String getDeviceId() {
            return deviceId==null?"":deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getCarPlateNumber() {
            return carPlateNumber;
        }

        public void setCarPlateNumber(String carPlateNumber) {
            this.carPlateNumber = carPlateNumber;
        }

        public String getCarStyleId() {
            return carStyleId;
        }

        public void setCarStyleId(String carStyleId) {
            this.carStyleId = carStyleId;
        }

        public String getCarStyleName() {
            return carStyleName;
        }

        public void setCarStyleName(String carStyleName) {
            this.carStyleName = carStyleName;
        }

        public String getDriverStaffId() {
            return driverStaffId;
        }

        public void setDriverStaffId(String driverStaffId) {
            this.driverStaffId = driverStaffId;
        }

        public String getDriverStaffName() {
            return driverStaffName;
        }

        public void setDriverStaffName(String driverStaffName) {
            this.driverStaffName = driverStaffName;
        }

        public String getDriverUserId() {
            return driverUserId;
        }

        public void setDriverUserId(String driverUserId) {
            this.driverUserId = driverUserId;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public Object getTenantUserName() {
            return tenantUserName;
        }

        public void setTenantUserName(Object tenantUserName) {
            this.tenantUserName = tenantUserName;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getStartNum() {
            return startNum;
        }

        public void setStartNum(Object startNum) {
            this.startNum = startNum;
        }

        public Object getStartNumImg() {
            return startNumImg;
        }

        public void setStartNumImg(Object startNumImg) {
            this.startNumImg = startNumImg;
        }

        public Object getEndNum() {
            return endNum;
        }

        public void setEndNum(Object endNum) {
            this.endNum = endNum;
        }

        public Object getEndNumImg() {
            return endNumImg;
        }

        public void setEndNumImg(Object endNumImg) {
            this.endNumImg = endNumImg;
        }

        public Object getStartGPSNum() {
            return startGPSNum;
        }

        public void setStartGPSNum(Object startGPSNum) {
            this.startGPSNum = startGPSNum;
        }

        public double getEndGPSNum() {
            return endGPSNum;
        }

        public void setEndGPSNum(double endGPSNum) {
            this.endGPSNum = endGPSNum;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getReturnTime() {
            return returnTime;
        }

        public void setReturnTime(Object returnTime) {
            this.returnTime = returnTime;
        }

        public Object getReturnCarReason() {
            return returnCarReason;
        }

        public void setReturnCarReason(Object returnCarReason) {
            this.returnCarReason = returnCarReason;
        }

        public Object getReturnCarSimpleAddress() {
            return returnCarSimpleAddress;
        }

        public void setReturnCarSimpleAddress(Object returnCarSimpleAddress) {
            this.returnCarSimpleAddress = returnCarSimpleAddress;
        }

        public Object getReturnCarFullAddress() {
            return returnCarFullAddress;
        }

        public void setReturnCarFullAddress(Object returnCarFullAddress) {
            this.returnCarFullAddress = returnCarFullAddress;
        }

        public Object getReturnCarLng() {
            return returnCarLng;
        }

        public void setReturnCarLng(Object returnCarLng) {
            this.returnCarLng = returnCarLng;
        }

        public Object getReturnCarLat() {
            return returnCarLat;
        }

        public void setReturnCarLat(Object returnCarLat) {
            this.returnCarLat = returnCarLat;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateTimeFormat() {
            return createTimeFormat;
        }

        public void setCreateTimeFormat(Object createTimeFormat) {
            this.createTimeFormat = createTimeFormat;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateByUserName() {
            return createByUserName;
        }

        public void setCreateByUserName(Object createByUserName) {
            this.createByUserName = createByUserName;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdateTimeFormat() {
            return updateTimeFormat;
        }

        public void setUpdateTimeFormat(Object updateTimeFormat) {
            this.updateTimeFormat = updateTimeFormat;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateByUserName() {
            return updateByUserName;
        }

        public void setUpdateByUserName(Object updateByUserName) {
            this.updateByUserName = updateByUserName;
        }

        public String getCarFlow_Order_CarId() {
            return carFlow_Order_CarId;
        }

        public void setCarFlow_Order_CarId(String carFlow_Order_CarId) {
            this.carFlow_Order_CarId = carFlow_Order_CarId;
        }
    }
}
