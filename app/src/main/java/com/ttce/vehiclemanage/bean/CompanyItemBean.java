package com.ttce.vehiclemanage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hk on 2019/7/29.
 */

public class CompanyItemBean implements Serializable {
    /**
     * CarCondition : null
     * ProtocolId : null
     * LevelNum : 0
     * Duration : 0
     * Status : 0
     * StatusStr : null
     * FuWuQiXian : null
     * CarId : 00000000-0000-0000-0000-000000000000
     * IsTransfer : 0
     * IsOilSensor : 0
     * IsOilSensorFormat : 未安装
     * IsTemperatureSensor : 0
     * IsTemperatureSensorFormat : 未安装
     * Oil_1 : 0
     * PositioningStatus : null
     * PositioningStatusFormat : 未定位
     * HeartbeatTime : null
     * GSM : null
     * GSMFormat : null
     * HeartbeatTimeFormat : null
     * HeartbeatOnlyTime : null
     * HeartbeatOnlyTimeFormat : null
     * OilQuantity : null
     * OilPercent : null
     * CarDisplayColor : 0
     * CarDisplayColorFormat : null
     * CarDisplayColorFormat1 : jc_gray.png
     * PlateDisplayColorFormat : null
     * OiltankMax : 0
     * OrderByCarNumber : null
     * TaskStartTime : 0001-01-01 00:00:00
     * StartLat : 0
     * StartLng : 0
     * StartMileage : 0
     * TaskEndTime : 0001-01-01 00:00:00
     * TaskEndTimeFormat : 0001-01-01 12:00:00
     * EndMileageFormat : 0
     * StartMileageFormat : 0
     * TaskStartTimeFormat : 0001-01-01 12:00:00
     * EndLat : 0
     * EndLng : 0
     * EndMileage : 0
     * TaskStatus : 0
     * TaskStatusFormat :
     * TaskStaffName : null
     * Address : null
     * Way : null
     * Info : null
     * CreateBy : 00000000-0000-0000-0000-000000000000
     * TaskRemark : null
     * Children : [{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
     * id : 21080300-0000-0000-0000-000000000001
     * AlarmType : 0
     * AlarmTypeFormat : null
     * Angle : 0
     * AngleFormat : 正北
     * Mileage_Gps : 0
     * Mileage_GpsFormat : null
     * Mileage_Instrument : 0
     * Mileage_InstrumentFormat : null
     * terminalNumber : null
     * StaffName : null
     * inum : null
     * Lat : 0
     * Lng : 0
     * Mileage_Day : 0
     * Mileage_DayFormat : null
     * Mileage_Month : 0
     * Mileage_MonthFormat : null
     * CarNumber : 北斗平台
     * ConsumePower : 0
     * phone : null
     * pid : null
     * CarStatus : 0
     * CarStatusFormat : null
     * OnLine : 0
     * Temperature : 0
     * TemperatureFormat : null
     * Mileage_Interval : 0
     * DeviceNumber : null
     * OnLineFormat : null
     * Satellite : 0
     * shop : null
     * Speed : 0
     * GpsTime : null
     * GpsTimeFormat : null
     * ServiceTimeFormat : null
     * ServiceTime : null
     * type : 2
     * CarType : null
     * Acc : 0
     * AccFormat : 熄火
     * IsHasOil : 0
     * IsHasCarTask : false
     * CarTask : null
     */
    private Object CarCondition;
    private String ProtocolId;
    private int LevelNum;
    private int Duration;
    private int Status;
    private Object StatusStr;
    private String FuWuQiXian;
    private String FuWuQiXianFormat;
    private String CarId;
    private int IsTransfer;
    private int IsOilSensor;
    private String IsOilSensorFormat;
    private int IsTemperatureSensor;
    private String IsTemperatureSensorFormat;
    private int Oil_1;
    private int PositioningStatus;
    private String PositioningStatusFormat;
    private String HeartbeatTime;
    private int GSM;
    private String GSMFormat;
    private String HeartbeatTimeFormat;
    private String HeartbeatOnlyTime;
    private String HeartbeatOnlyTimeFormat;
    private String OilQuantity;
    private String OilPercent;
    private int CarDisplayColor;
    private String CarDisplayColorFormat;
    private String CarDisplayColorFormat1;
    private String PlateDisplayColorFormat;
    private int OiltankMax;
    private String OrderByCarNumber;
    private String TaskStartTime;
    private int StartLat;
    private int StartLng;
    private int StartMileage;
    private String TaskEndTime;
    private String TaskEndTimeFormat;
    private String EndMileageFormat;
    private String StartMileageFormat;
    private String TaskStartTimeFormat;
    private int EndLat;
    private int EndLng;
    private int EndMileage;
    private int TaskStatus;
    private String TaskStatusFormat;
    private Object TaskStaffName;
    private Object Address;
    private Object Way;
    private Object Info;
    private String CreateBy;
    private Object TaskRemark;
    private int AlarmType;
    private Object AlarmTypeFormat;
    private int Angle;
    private String AngleFormat;
    private double Mileage_Gps;
    private String Mileage_GpsFormat;
    private double Mileage_Instrument;
    private String Mileage_InstrumentFormat;
    private String terminalNumber;
    private Object StaffName;
    private String id;
    private Object inum;
    private double Lat;
    private double Lng;
    private double Mileage_Day;
    private String Mileage_DayFormat;
    private double Mileage_Month;
    private String Mileage_MonthFormat;
    private String CarNumber;
    private int ConsumePower;
    private Object phone;
    private String pid;
    private int CarStatus;
    private String CarStatusFormat;
    private int OnLine;
    private int Temperature;
    private String TemperatureFormat;
    private int Mileage_Interval;
    private Object DeviceNumber;
    private String DeviceId;
    private String OnLineFormat;
    private int Satellite;
    private Object shop;
    private int Speed;
    private String GpsTime;
    private String GpsTimeFormat;
    private String ServiceTimeFormat;
    private String ServiceTime;
    private int type;
    private String CarType;
    private int Acc;
    private String AccFormat;
    private int IsHasOil;
    private boolean IsHasCarTask;
    private Object CarTask;
    private List<CompanyItemBean> Children;
    private int IsPass;

    public String getFuWuQiXianFormat() {
        return FuWuQiXianFormat;
    }

    public void setFuWuQiXianFormat(String fuWuQiXianFormat) {
        FuWuQiXianFormat = fuWuQiXianFormat;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public boolean isHasCarTask() {
        return IsHasCarTask;
    }

    public void setHasCarTask(boolean hasCarTask) {
        IsHasCarTask = hasCarTask;
    }

    public int getIsPass() {
        return IsPass;
    }

    public void setIsPass(int isPass) {
        IsPass = isPass;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyItemBean that = (CompanyItemBean) o;

        if (CarId != that.CarId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return CarNumber != null ? CarNumber.equals(that.CarNumber) : that.CarNumber == null;
    }

//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + CarId;
//        result = 31 * result + (CarNumber != null ? CarNumber.hashCode() : 0);
//        return result;
//    }

    public Object getCarCondition() {
        return CarCondition;
    }

    public void setCarCondition(Object CarCondition) {
        this.CarCondition = CarCondition;
    }

    public Object getProtocolId() {
        return ProtocolId;
    }

    public void setProtocolId(String ProtocolId) {
        this.ProtocolId = ProtocolId;
    }

    public int getLevelNum() {
        return LevelNum;
    }

    public void setLevelNum(int LevelNum) {
        this.LevelNum = LevelNum;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Object getStatusStr() {
        return StatusStr;
    }

    public void setStatusStr(Object StatusStr) {
        this.StatusStr = StatusStr;
    }

    public Object getFuWuQiXian() {
        return FuWuQiXian;
    }

    public void setFuWuQiXian(String FuWuQiXian) {
        this.FuWuQiXian = FuWuQiXian;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String CarId) {
        this.CarId = CarId;
    }

    public int getIsTransfer() {
        return IsTransfer;
    }

    public void setIsTransfer(int IsTransfer) {
        this.IsTransfer = IsTransfer;
    }

    public int getIsOilSensor() {
        return IsOilSensor;
    }

    public void setIsOilSensor(int IsOilSensor) {
        this.IsOilSensor = IsOilSensor;
    }

    public String getIsOilSensorFormat() {
        return IsOilSensorFormat;
    }

    public void setIsOilSensorFormat(String IsOilSensorFormat) {
        this.IsOilSensorFormat = IsOilSensorFormat;
    }

    public int getIsTemperatureSensor() {
        return IsTemperatureSensor;
    }

    public void setIsTemperatureSensor(int IsTemperatureSensor) {
        this.IsTemperatureSensor = IsTemperatureSensor;
    }

    public String getIsTemperatureSensorFormat() {
        return IsTemperatureSensorFormat;
    }

    public void setIsTemperatureSensorFormat(String IsTemperatureSensorFormat) {
        this.IsTemperatureSensorFormat = IsTemperatureSensorFormat;
    }

    public int getOil_1() {
        return Oil_1;
    }

    public void setOil_1(int Oil_1) {
        this.Oil_1 = Oil_1;
    }

    public Object getPositioningStatus() {
        return PositioningStatus;
    }

    public void setPositioningStatus(int PositioningStatus) {
        this.PositioningStatus = PositioningStatus;
    }

    public String getPositioningStatusFormat() {
        return PositioningStatusFormat;
    }

    public void setPositioningStatusFormat(String PositioningStatusFormat) {
        this.PositioningStatusFormat = PositioningStatusFormat;
    }

    public Object getHeartbeatTime() {
        return HeartbeatTime;
    }

    public void setHeartbeatTime(String HeartbeatTime) {
        this.HeartbeatTime = HeartbeatTime;
    }

    public Object getGSM() {
        return GSM;
    }

    public void setGSM(int GSM) {
        this.GSM = GSM;
    }

    public Object getGSMFormat() {
        return GSMFormat;
    }

    public void setGSMFormat(String GSMFormat) {
        this.GSMFormat = GSMFormat;
    }

    public Object getHeartbeatTimeFormat() {
        return HeartbeatTimeFormat;
    }

    public void setHeartbeatTimeFormat(String HeartbeatTimeFormat) {
        this.HeartbeatTimeFormat = HeartbeatTimeFormat;
    }

    public Object getHeartbeatOnlyTime() {
        return HeartbeatOnlyTime;
    }

    public void setHeartbeatOnlyTime(String HeartbeatOnlyTime) {
        this.HeartbeatOnlyTime = HeartbeatOnlyTime;
    }

    public Object getHeartbeatOnlyTimeFormat() {
        return HeartbeatOnlyTimeFormat;
    }

    public void setHeartbeatOnlyTimeFormat(String HeartbeatOnlyTimeFormat) {
        this.HeartbeatOnlyTimeFormat = HeartbeatOnlyTimeFormat;
    }

    public Object getOilQuantity() {
        return OilQuantity;
    }

    public void setOilQuantity(String OilQuantity) {
        this.OilQuantity = OilQuantity;
    }

    public Object getOilPercent() {
        return OilPercent;
    }

    public void setOilPercent(String OilPercent) {
        this.OilPercent = OilPercent;
    }

    public int getCarDisplayColor() {
        return CarDisplayColor;
    }

    public void setCarDisplayColor(int CarDisplayColor) {
        this.CarDisplayColor = CarDisplayColor;
    }

    public String getCarDisplayColorFormat() {
        return CarDisplayColorFormat;
    }

    public void setCarDisplayColorFormat(String CarDisplayColorFormat) {
        this.CarDisplayColorFormat = CarDisplayColorFormat;
    }

    public String getCarDisplayColorFormat1() {
        return CarDisplayColorFormat1;
    }

    public void setCarDisplayColorFormat1(String CarDisplayColorFormat1) {
        this.CarDisplayColorFormat1 = CarDisplayColorFormat1;
    }

    public String getPlateDisplayColorFormat() {
        return PlateDisplayColorFormat;
    }

    public void setPlateDisplayColorFormat(String PlateDisplayColorFormat) {
        this.PlateDisplayColorFormat = PlateDisplayColorFormat;
    }

    public int getOiltankMax() {
        return OiltankMax;
    }

    public void setOiltankMax(int OiltankMax) {
        this.OiltankMax = OiltankMax;
    }

    public Object getOrderByCarNumber() {
        return OrderByCarNumber;
    }

    public void setOrderByCarNumber(String OrderByCarNumber) {
        this.OrderByCarNumber = OrderByCarNumber;
    }

    public String getTaskStartTime() {
        return TaskStartTime;
    }

    public void setTaskStartTime(String TaskStartTime) {
        this.TaskStartTime = TaskStartTime;
    }

    public int getStartLat() {
        return StartLat;
    }

    public void setStartLat(int StartLat) {
        this.StartLat = StartLat;
    }

    public int getStartLng() {
        return StartLng;
    }

    public void setStartLng(int StartLng) {
        this.StartLng = StartLng;
    }

    public int getStartMileage() {
        return StartMileage;
    }

    public void setStartMileage(int StartMileage) {
        this.StartMileage = StartMileage;
    }

    public String getTaskEndTime() {
        return TaskEndTime;
    }

    public void setTaskEndTime(String TaskEndTime) {
        this.TaskEndTime = TaskEndTime;
    }

    public String getTaskEndTimeFormat() {
        return TaskEndTimeFormat;
    }

    public void setTaskEndTimeFormat(String TaskEndTimeFormat) {
        this.TaskEndTimeFormat = TaskEndTimeFormat;
    }

    public String getEndMileageFormat() {
        return EndMileageFormat;
    }

    public void setEndMileageFormat(String EndMileageFormat) {
        this.EndMileageFormat = EndMileageFormat;
    }

    public String getStartMileageFormat() {
        return StartMileageFormat;
    }

    public void setStartMileageFormat(String StartMileageFormat) {
        this.StartMileageFormat = StartMileageFormat;
    }

    public String getTaskStartTimeFormat() {
        return TaskStartTimeFormat;
    }

    public void setTaskStartTimeFormat(String TaskStartTimeFormat) {
        this.TaskStartTimeFormat = TaskStartTimeFormat;
    }

    public int getEndLat() {
        return EndLat;
    }

    public void setEndLat(int EndLat) {
        this.EndLat = EndLat;
    }

    public int getEndLng() {
        return EndLng;
    }

    public void setEndLng(int EndLng) {
        this.EndLng = EndLng;
    }

    public int getEndMileage() {
        return EndMileage;
    }

    public void setEndMileage(int EndMileage) {
        this.EndMileage = EndMileage;
    }

    public int getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(int TaskStatus) {
        this.TaskStatus = TaskStatus;
    }

    public String getTaskStatusFormat() {
        return TaskStatusFormat;
    }

    public void setTaskStatusFormat(String TaskStatusFormat) {
        this.TaskStatusFormat = TaskStatusFormat;
    }

    public Object getTaskStaffName() {
        return TaskStaffName;
    }

    public void setTaskStaffName(Object TaskStaffName) {
        this.TaskStaffName = TaskStaffName;
    }

    public Object getAddress() {
        return Address;
    }

    public void setAddress(Object Address) {
        this.Address = Address;
    }

    public Object getWay() {
        return Way;
    }

    public void setWay(Object Way) {
        this.Way = Way;
    }

    public Object getInfo() {
        return Info;
    }

    public void setInfo(Object Info) {
        this.Info = Info;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String CreateBy) {
        this.CreateBy = CreateBy;
    }

    public Object getTaskRemark() {
        return TaskRemark;
    }

    public void setTaskRemark(Object TaskRemark) {
        this.TaskRemark = TaskRemark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAlarmType() {
        return AlarmType;
    }

    public void setAlarmType(int AlarmType) {
        this.AlarmType = AlarmType;
    }

    public Object getAlarmTypeFormat() {
        return AlarmTypeFormat;
    }

    public void setAlarmTypeFormat(Object AlarmTypeFormat) {
        this.AlarmTypeFormat = AlarmTypeFormat;
    }

    public int getAngle() {
        return Angle;
    }

    public void setAngle(int Angle) {
        this.Angle = Angle;
    }

    public String getAngleFormat() {
        return AngleFormat;
    }

    public void setAngleFormat(String AngleFormat) {
        this.AngleFormat = AngleFormat;
    }

    public double getMileage_Gps() {
        return Mileage_Gps;
    }

    public void setMileage_Gps(double Mileage_Gps) {
        this.Mileage_Gps = Mileage_Gps;
    }

    public Object getMileage_GpsFormat() {
        return Mileage_GpsFormat;
    }

    public void setMileage_GpsFormat(String Mileage_GpsFormat) {
        this.Mileage_GpsFormat = Mileage_GpsFormat;
    }

    public double getMileage_Instrument() {
        return Mileage_Instrument;
    }

    public void setMileage_Instrument(double Mileage_Instrument) {
        this.Mileage_Instrument = Mileage_Instrument;
    }

    public Object getMileage_InstrumentFormat() {
        return Mileage_InstrumentFormat;
    }

    public void setMileage_InstrumentFormat(String Mileage_InstrumentFormat) {
        this.Mileage_InstrumentFormat = Mileage_InstrumentFormat;
    }

    public Object getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Object getStaffName() {
        return StaffName;
    }

    public void setStaffName(Object StaffName) {
        this.StaffName = StaffName;
    }

    public Object getInum() {
        return inum;
    }

    public void setInum(Object inum) {
        this.inum = inum;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double Lng) {
        this.Lng = Lng;
    }

    public double getMileage_Day() {
        return Mileage_Day;
    }

    public void setMileage_Day(double Mileage_Day) {
        this.Mileage_Day = Mileage_Day;
    }

    public Object getMileage_DayFormat() {
        return Mileage_DayFormat;
    }

    public void setMileage_DayFormat(String Mileage_DayFormat) {
        this.Mileage_DayFormat = Mileage_DayFormat;
    }

    public double getMileage_Month() {
        return Mileage_Month;
    }

    public void setMileage_Month(double Mileage_Month) {
        this.Mileage_Month = Mileage_Month;
    }

    public Object getMileage_MonthFormat() {
        return Mileage_MonthFormat;
    }

    public void setMileage_MonthFormat(String Mileage_MonthFormat) {
        this.Mileage_MonthFormat = Mileage_MonthFormat;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String CarNumber) {
        this.CarNumber = CarNumber;
    }

    public int getConsumePower() {
        return ConsumePower;
    }

    public void setConsumePower(int ConsumePower) {
        this.ConsumePower = ConsumePower;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCarStatus() {
        return CarStatus;
    }

    public void setCarStatus(int CarStatus) {
        this.CarStatus = CarStatus;
    }

    public Object getCarStatusFormat() {
        return CarStatusFormat;
    }

    public void setCarStatusFormat(String CarStatusFormat) {
        this.CarStatusFormat = CarStatusFormat;
    }

    public int getOnLine() {
        return OnLine;
    }

    public void setOnLine(int OnLine) {
        this.OnLine = OnLine;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int Temperature) {
        this.Temperature = Temperature;
    }

    public Object getTemperatureFormat() {
        return TemperatureFormat;
    }

    public void setTemperatureFormat(String TemperatureFormat) {
        this.TemperatureFormat = TemperatureFormat;
    }

    public int getMileage_Interval() {
        return Mileage_Interval;
    }

    public void setMileage_Interval(int Mileage_Interval) {
        this.Mileage_Interval = Mileage_Interval;
    }

    public Object getDeviceNumber() {
        return DeviceNumber;
    }

    public void setDeviceNumber(Object DeviceNumber) {
        this.DeviceNumber = DeviceNumber;
    }

    public Object getOnLineFormat() {
        return OnLineFormat;
    }

    public void setOnLineFormat(String OnLineFormat) {
        this.OnLineFormat = OnLineFormat;
    }

    public int getSatellite() {
        return Satellite;
    }

    public void setSatellite(int Satellite) {
        this.Satellite = Satellite;
    }

    public Object getShop() {
        return shop;
    }

    public void setShop(Object shop) {
        this.shop = shop;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }

    public Object getGpsTime() {
        return GpsTime;
    }

    public void setGpsTime(String GpsTime) {
        this.GpsTime = GpsTime;
    }

    public Object getGpsTimeFormat() {
        return GpsTimeFormat;
    }

    public void setGpsTimeFormat(String GpsTimeFormat) {
        this.GpsTimeFormat = GpsTimeFormat;
    }

    public Object getServiceTimeFormat() {
        return ServiceTimeFormat;
    }

    public void setServiceTimeFormat(String ServiceTimeFormat) {
        this.ServiceTimeFormat = ServiceTimeFormat;
    }

    public Object getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(String ServiceTime) {
        this.ServiceTime = ServiceTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getCarType() {
        return CarType;
    }

    public void setCarType(String CarType) {
        this.CarType = CarType;
    }

    public int getAcc() {
        return Acc;
    }

    public void setAcc(int Acc) {
        this.Acc = Acc;
    }

    public String getAccFormat() {
        return AccFormat;
    }

    public void setAccFormat(String AccFormat) {
        this.AccFormat = AccFormat;
    }

    public int getIsHasOil() {
        return IsHasOil;
    }

    public void setIsHasOil(int IsHasOil) {
        this.IsHasOil = IsHasOil;
    }

    public boolean isIsHasCarTask() {
        return IsHasCarTask;
    }

    public void setIsHasCarTask(boolean IsHasCarTask) {
        this.IsHasCarTask = IsHasCarTask;
    }

    public Object getCarTask() {
        return CarTask;
    }

    public void setCarTask(Object CarTask) {
        this.CarTask = CarTask;
    }

    public List<CompanyItemBean> getChildren() {
        if (Children == null) {
            return new ArrayList<>();
        }
        return Children;
    }

    public void setChildren(List<CompanyItemBean> children) {
        Children = children;
    }

}
