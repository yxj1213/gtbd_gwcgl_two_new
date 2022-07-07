package com.ttce.vehiclemanage.bean;

public class SaveLoginIPBean {
    public String type;
    public String v4Port;
    public String v4Ip;
    public String v4YuMing;

    public String getType() {
        return type==null?"":type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getV4Port() {
        return v4Port==null?"":v4Port;
    }

    public void setV4Port(String v4Port) {
        this.v4Port = v4Port;
    }

    public String getV4Ip() {
        return v4Ip==null?"":v4Ip;
    }

    public void setV4Ip(String v4Ip) {
        this.v4Ip = v4Ip;
    }

    public String getV4YuMing() {
        return v4YuMing==null?"":v4YuMing;
    }

    public void setV4YuMing(String v4YuMing) {
        this.v4YuMing = v4YuMing;
    }
}
