package com.ttce.vehiclemanage.bean;

import com.google.gson.annotations.SerializedName;

public class CarPositionBean {


    @SerializedName("CarId")
    private String carId;
    @SerializedName("PlateNumber")
    private String plateNumber;
    @SerializedName("IsUsable")
    private boolean isUsable;
    @SerializedName("CompanyId")
    private String CompanyId;
    @SerializedName("DepartmentId")
    private String DepartmentId;

    public CarPositionBean(String carId, String plateNumber, String companyId, String departmentId) {
        this.carId = carId;
        this.plateNumber = plateNumber;
        CompanyId = companyId;
        DepartmentId = departmentId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }
}
