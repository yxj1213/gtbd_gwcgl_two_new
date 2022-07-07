package com.ttce.vehiclemanage.bean;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.utils.CarStateFactory;
import com.ttce.vehiclemanage.utils.clustering.ClusterItem;

import java.io.Serializable;

import static com.ttce.vehiclemanage.utils.BDMapUtils.convert;

/**
 * Created by hk on 2019/7/3.
 */

public class MonitorResponseBean implements Serializable, ClusterItem {
    /**
     * CarCondition : null
     * ProtocolId : 71015fc5-f4b9-4b16-8654-30d1a100f39e
     * LevelNum : 0
     * Duration : 0
     * Status : 0
     * StatusStr : null
     * FuWuQiXian : 2024-01-01 00:00:00
     * CarId : 21101410-5725-1082-4228-658015826730
     * IsTransfer : 0
     * IsOilSensor : 0
     * IsOilSensorFormat : 未安装
     * IsTemperatureSensor : 0
     * IsTemperatureSensorFormat : 未安装
     * Oil_1 : 0
     * PositioningStatus : 1
     * PositioningStatusFormat : 已定位
     * HeartbeatTime : 2021-11-10 09:59:59
     * GSM : 4
     * GSMFormat : 信号强
     * HeartbeatTimeFormat : 2021-11-10 09:59:59
     * HeartbeatOnlyTime : 2021-11-10 09:59:59
     * HeartbeatOnlyTimeFormat : 2021-11-10 09:59:59
     * OilQuantity : 0L
     * OilPercent : 0%
     * CarDisplayColor : 50
     * CarDisplayColorFormat : green
     * CarDisplayColorFormat1 : jc_green.png
     * PlateDisplayColorFormat : #2ac42a
     * OiltankMax : 0
     * OrderByCarNumber : 晋AHW361
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
     * Children : null
     * AlarmType : 0
     * AlarmTypeFormat : null
     * Angle : 35
     * AngleFormat : 东北
     * Mileage_Gps : 1905083.9
     * Mileage_GpsFormat : 1905.1
     * Mileage_Instrument : 1905083.9
     * Mileage_InstrumentFormat : 1905.1
     * terminalNumber : 120202115975
     * StaffName : null
     * id : 120202115975
     * inum : null
     * Lat : 37.8536578
     * Lng : 112.5857956
     * Mileage_Day : 16795.3
     * Mileage_DayFormat : 16.8
     * Mileage_Month : 755759.7
     * Mileage_MonthFormat : 755.8
     * CarNumber : 晋AHW361
     * ConsumePower : 0
     * phone : null
     * pid : 21101215-2139-7572-9136-637070009550
     * CarStatus : 52
     * CarStatusFormat : 静止熄火12分
     * OnLine : 1
     * Temperature : 255
     * TemperatureFormat : --
     * Mileage_Interval : 0
     * DeviceNumber : null
     * OnLineFormat : OnLine
     * Satellite : 14
     * shop : null
     * Speed : 0
     * GpsTime : 2021-11-10 09:49:59
     * GpsTimeFormat : 2021-11-10 09:49:59
     * ServiceTimeFormat : 2021-11-10 09:49:59
     * ServiceTime : 2021-11-10 09:49:59
     * type : 0
     * CarType : 其他
     * Acc : 0
     * AccFormat : 熄火
     * IsHasOil : 0
     * IsHasCarTask : false
     * CarTask : null
     */

    private String CarCondition;
    private String ProtocolId;
    private int LevelNum;
    private int Duration;
    private int Status;
    private String StatusStr;
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
    private String TaskStaffName;
    private String Address;
    private Object Way;
    private Object Info;
    private String CreateBy;
    private Object TaskRemark;
    private Object Children;
    private int AlarmType;
    private Object AlarmTypeFormat;
    private int Angle;
    private String AngleFormat;
    private double Mileage_Gps;
    private String Mileage_GpsFormat;
    private double Mileage_Instrument;
    private String Mileage_InstrumentFormat;
    private String terminalNumber;
    private String StaffName;
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
    private String phone;
    private String pid;
    private int CarStatus;
    private String CarStatusFormat;
    private int OnLine;
    private int Temperature;
    private String TemperatureFormat;
    private int Mileage_Interval;
    private String DeviceNumber;
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
    private String CarTask;
    private String StartlocationDescribe ;
    private double StartlocationLat;
    private double StartlocationLng;
    private BitmapDescriptor bitmap;
    private boolean isShowInfowindow;

    public boolean isShowInfowindow() {
        return isShowInfowindow;
    }

    public void setShowInfowindow(boolean showInfowindow) {
        isShowInfowindow = showInfowindow;
    }

    public BitmapDescriptor getBitmap() {
        return bitmap;
    }

    public void setBitmap(BitmapDescriptor bitmap) {
        this.bitmap = bitmap;
    }

    public String getStartlocationDescribe() {
        return StartlocationDescribe;
    }

    public void setStartlocationDescribe(String startlocationDescribe) {
        StartlocationDescribe = startlocationDescribe;
    }

    public double getStartlocationLat() {
        return StartlocationLat;
    }

    public void setStartlocationLat(double startlocationLat) {
        StartlocationLat = startlocationLat;
    }

    public double getStartlocationLng() {
        return StartlocationLng;
    }

    public void setStartlocationLng(double startlocationLng) {
        StartlocationLng = startlocationLng;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;//地址相等
        }

        if (obj == null) {
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if (obj instanceof MonitorResponseBean) {
            MonitorResponseBean other = (MonitorResponseBean) obj;
            //需要比较的字段相等，则这两个对象相等
            if (equalsStr(this.CarNumber, other.CarNumber)) {
                return true;
            }
        }

        return false;
    }

    private boolean equalsStr(String str1, String str2) {
        if (TextUtils.isEmpty(str1) && TextUtils.isEmpty(str2)) {
            return true;
        }
        if (!TextUtils.isEmpty(str1) && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (CarNumber == null ? 0 : CarNumber.hashCode());
        return result;
    }


    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getFuWuQiXianFormat() {
        return FuWuQiXianFormat;
    }

    public void setFuWuQiXianFormat(String fuWuQiXianFormat) {
        FuWuQiXianFormat = fuWuQiXianFormat;
    }

    public String getCarCondition() {
        return CarCondition;
    }

    public void setCarCondition(String CarCondition) {
        this.CarCondition = CarCondition;
    }

    public String getProtocolId() {
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

    public String getStatusStr() {
        return StatusStr;
    }

    public void setStatusStr(String StatusStr) {
        this.StatusStr = StatusStr;
    }

    public String getFuWuQiXian() {
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

    public int getPositioningStatus() {
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

    public String getHeartbeatTime() {
        return HeartbeatTime;
    }

    public void setHeartbeatTime(String HeartbeatTime) {
        this.HeartbeatTime = HeartbeatTime;
    }

    public int getGSM() {
        return GSM;
    }

    public void setGSM(int GSM) {
        this.GSM = GSM;
    }

    public String getGSMFormat() {
        return GSMFormat;
    }

    public void setGSMFormat(String GSMFormat) {
        this.GSMFormat = GSMFormat;
    }

    public String getHeartbeatTimeFormat() {
        return HeartbeatTimeFormat;
    }

    public void setHeartbeatTimeFormat(String HeartbeatTimeFormat) {
        this.HeartbeatTimeFormat = HeartbeatTimeFormat;
    }

    public String getHeartbeatOnlyTime() {
        return HeartbeatOnlyTime;
    }

    public void setHeartbeatOnlyTime(String HeartbeatOnlyTime) {
        this.HeartbeatOnlyTime = HeartbeatOnlyTime;
    }

    public String getHeartbeatOnlyTimeFormat() {
        return HeartbeatOnlyTimeFormat;
    }

    public void setHeartbeatOnlyTimeFormat(String HeartbeatOnlyTimeFormat) {
        this.HeartbeatOnlyTimeFormat = HeartbeatOnlyTimeFormat;
    }

    public String getOilQuantity() {
        return OilQuantity;
    }

    public void setOilQuantity(String OilQuantity) {
        this.OilQuantity = OilQuantity;
    }

    public String getOilPercent() {
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

    public String getOrderByCarNumber() {
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

    public String getTaskStaffName() {
        return TaskStaffName;
    }

    public void setTaskStaffName(String TaskStaffName) {
        this.TaskStaffName = TaskStaffName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
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

    public Object getChildren() {
        return Children;
    }

    public void setChildren(Object Children) {
        this.Children = Children;
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

    public String getMileage_GpsFormat() {
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

    public String getMileage_InstrumentFormat() {
        return Mileage_InstrumentFormat;
    }

    public void setMileage_InstrumentFormat(String Mileage_InstrumentFormat) {
        this.Mileage_InstrumentFormat = Mileage_InstrumentFormat;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMileage_DayFormat() {
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

    public String getMileage_MonthFormat() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getCarStatusFormat() {
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

    public String getTemperatureFormat() {
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

    public String getDeviceNumber() {
        return DeviceNumber;
    }

    public void setDeviceNumber(String DeviceNumber) {
        this.DeviceNumber = DeviceNumber;
    }

    public String getOnLineFormat() {
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

    public String getGpsTime() {
        return GpsTime;
    }

    public void setGpsTime(String GpsTime) {
        this.GpsTime = GpsTime;
    }

    public String getGpsTimeFormat() {
        return GpsTimeFormat;
    }

    public void setGpsTimeFormat(String GpsTimeFormat) {
        this.GpsTimeFormat = GpsTimeFormat;
    }

    public String getServiceTimeFormat() {
        return ServiceTimeFormat;
    }

    public void setServiceTimeFormat(String ServiceTimeFormat) {
        this.ServiceTimeFormat = ServiceTimeFormat;
    }

    public String getServiceTime() {
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

    public String getCarType() {
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

    public String getCarTask() {
        return CarTask;
    }

    public void setCarTask(String CarTask) {
        this.CarTask = CarTask;
    }

    @Override
    public LatLng getPosition() {
        return convert(new LatLng(getLat(),getLng()));
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(CarStateFactory.getCarColorByStatus(getCarDisplayColorFormat()));
        return  bitmap;
    }
    @Override
    public int getAngles() {
        return getAngle();
    }

    @Override
    public String getCarNumbers() {
        return getCarNumber();
    }

    @Override
    public int getCarColor() {
        int color= CarStateFactory.getCarTextColorByStatus(getCarDisplayColorFormat());
        return color;
    }

}
