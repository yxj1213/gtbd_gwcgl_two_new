package com.ttce.vehiclemanage.bean;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

public class TuJingDiBean implements Serializable {
    private String title;
    private String title2;
    private LatLng latLng;
    private String dzpz;
    private String xm;
    private String lxdh;
    private String type;
    private boolean isDaKa=false;

    public TuJingDiBean(String title, LatLng latLng) {
        this.title = title;
        this.latLng = latLng;
    }


    public TuJingDiBean(String title, String title2, LatLng latLng, String dzpz, String xm, String lxdh) {
        this.title = title;
        this.title2 = title2;
        this.latLng = latLng;
        this.dzpz = dzpz;
        this.xm = xm;
        this.lxdh = lxdh;
    }
    public TuJingDiBean(String title, String title2, LatLng latLng, String dzpz, String xm, String lxdh,boolean isDaKa) {
        this.title = title;
        this.title2 = title2;
        this.latLng = latLng;
        this.dzpz = dzpz;
        this.xm = xm;
        this.lxdh = lxdh;
        this.isDaKa = isDaKa;
    }
    public TuJingDiBean(String title, String title2, LatLng latLng, String dzpz, String xm, String lxdh,boolean isDaKa,String type) {
        this.title = title;
        this.title2 = title2;
        this.latLng = latLng;
        this.dzpz = dzpz;
        this.xm = xm;
        this.lxdh = lxdh;
        this.isDaKa = isDaKa;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDaKa() {
        return isDaKa;
    }

    public void setDaKa(boolean daKa) {
        isDaKa = daKa;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getDzpz() {
        return dzpz;
    }

    public void setDzpz(String dzpz) {
        this.dzpz = dzpz;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
}
