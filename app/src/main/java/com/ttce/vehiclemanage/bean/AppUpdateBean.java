package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

public class AppUpdateBean implements Serializable {


    /**
     * AppVersionName : 1.0.1
     * AppVersionCode : 1
     * AppVersionDescription : 新版本
     * IsForceUpdating : true
     * AppVersionUrl : 11
     * AppVersionStatus : true
     */

    private String AppVersionName;
    private int AppVersionCode;
    private String AppVersionDescription;
    private int IsForceUpdating;
    private String AppVersionUrl;
    private int AppVersionStatus;

    public String getAppVersionName() {
        return AppVersionName;
    }

    public void setAppVersionName(String AppVersionName) {
        this.AppVersionName = AppVersionName;
    }

    public int getAppVersionCode() {
        return AppVersionCode;
    }

    public void setAppVersionCode(int AppVersionCode) {
        this.AppVersionCode = AppVersionCode;
    }

    public String getAppVersionDescription() {
        return AppVersionDescription;
    }

    public void setAppVersionDescription(String AppVersionDescription) {
        this.AppVersionDescription = AppVersionDescription;
    }

    public int isIsForceUpdating() {
        return IsForceUpdating;
    }

    public void setIsForceUpdating(int IsForceUpdating) {
        this.IsForceUpdating = IsForceUpdating;
    }

    public String getAppVersionUrl() {
        return AppVersionUrl;
    }

    public void setAppVersionUrl(String AppVersionUrl) {
        this.AppVersionUrl = AppVersionUrl;
    }

    public int isAppVersionStatus() {
        return AppVersionStatus;
    }

    public void setAppVersionStatus(int AppVersionStatus) {
        this.AppVersionStatus = AppVersionStatus;
    }
}
