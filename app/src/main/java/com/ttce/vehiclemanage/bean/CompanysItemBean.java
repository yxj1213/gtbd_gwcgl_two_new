package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class CompanysItemBean implements Serializable {
    private String Id;//	string

    private String Name;// string

    private String PId;// string

    private int Type;// integer($int32)

    private int Status;// integer($int32)

    private String StatusStr;//string

    private String VIDExpireTime;//string($date-time)VID到期日期

    private boolean IsBind;//boolean是否已绑定到某围栏（围栏id已知的情况下有效）true:已绑定false:未绑定

    public String getId() {
        return Id == null ? "" : Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name == null ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPId() {
        return PId == null ? "" : PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getStatusStr() {
        return StatusStr == null ? "" : StatusStr;
    }

    public void setStatusStr(String statusStr) {
        StatusStr = statusStr;
    }

    public String getVIDExpireTime() {
        return VIDExpireTime == null ? "" : VIDExpireTime;
    }

    public void setVIDExpireTime(String VIDExpireTime) {
        this.VIDExpireTime = VIDExpireTime;
    }

    public boolean isBind() {
        return IsBind;
    }

    public void setBind(boolean bind) {
        IsBind = bind;
    }
}
