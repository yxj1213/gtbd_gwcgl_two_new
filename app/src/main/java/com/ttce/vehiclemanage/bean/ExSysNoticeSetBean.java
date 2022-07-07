package com.ttce.vehiclemanage.bean;

import java.io.Serializable;

/**
 * Created by hk on 2019/7/8.
 */

public class ExSysNoticeSetBean implements Serializable {

    private String Id;//	string

    private int GaoJingJieShou;//integer($int32)

    private int ZhenDong;//  integer($int32)

    private int LiXian;//  integer($int32)

    private int ChuWeiXingMangQu;//  integer($int32)

    private int JinWeiXingMangQu;//  integer($int32)

    private int DianChiDiDian;//  integer($int32)

    private int DuanDian;//  integer($int32)

    private int DiDian;//  integer($int32)

    private int PiLao;// integer($int32)

    private int ACC;//  integer($int32)

    private int ChaoSu;//  integer($int32)

    private int ShiRuWeiLan;//  integer($int32)

    private int ShiChuWeiLan;//  integer($int32)

    private int WeiYi;//  integer($int32)

    private String CreateUser;//  string

    private String CreateTime;// string($date-time)

    private String CreateTimeFormat;// string

    public String getId() {
        return Id == null ? "" : Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getGaoJingJieShou() {
        return GaoJingJieShou;
    }

    public void setGaoJingJieShou(int gaoJingJieShou) {
        GaoJingJieShou = gaoJingJieShou;
    }

    public int getZhenDong() {
        return ZhenDong;
    }

    public void setZhenDong(int zhenDong) {
        ZhenDong = zhenDong;
    }

    public int getLiXian() {
        return LiXian;
    }

    public void setLiXian(int liXian) {
        LiXian = liXian;
    }

    public int getChuWeiXingMangQu() {
        return ChuWeiXingMangQu;
    }

    public void setChuWeiXingMangQu(int chuWeiXingMangQu) {
        ChuWeiXingMangQu = chuWeiXingMangQu;
    }

    public int getJinWeiXingMangQu() {
        return JinWeiXingMangQu;
    }

    public void setJinWeiXingMangQu(int jinWeiXingMangQu) {
        JinWeiXingMangQu = jinWeiXingMangQu;
    }

    public int getDianChiDiDian() {
        return DianChiDiDian;
    }

    public void setDianChiDiDian(int dianChiDiDian) {
        DianChiDiDian = dianChiDiDian;
    }

    public int getDuanDian() {
        return DuanDian;
    }

    public void setDuanDian(int duanDian) {
        DuanDian = duanDian;
    }

    public int getDiDian() {
        return DiDian;
    }

    public void setDiDian(int diDian) {
        DiDian = diDian;
    }

    public int getPiLao() {
        return PiLao;
    }

    public void setPiLao(int piLao) {
        PiLao = piLao;
    }

    public int getACC() {
        return ACC;
    }

    public void setACC(int ACC) {
        this.ACC = ACC;
    }

    public int getChaoSu() {
        return ChaoSu;
    }

    public void setChaoSu(int chaoSu) {
        ChaoSu = chaoSu;
    }

    public int getShiRuWeiLan() {
        return ShiRuWeiLan;
    }

    public void setShiRuWeiLan(int shiRuWeiLan) {
        ShiRuWeiLan = shiRuWeiLan;
    }

    public int getShiChuWeiLan() {
        return ShiChuWeiLan;
    }

    public void setShiChuWeiLan(int shiChuWeiLan) {
        ShiChuWeiLan = shiChuWeiLan;
    }

    public int getWeiYi() {
        return WeiYi;
    }

    public void setWeiYi(int weiYi) {
        WeiYi = weiYi;
    }

    public String getCreateUser() {
        return CreateUser == null ? "" : CreateUser;
    }

    public void setCreateUser(String createUser) {
        CreateUser = createUser;
    }

    public String getCreateTime() {
        return CreateTime == null ? "" : CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreateTimeFormat() {
        return CreateTimeFormat == null ? "" : CreateTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        CreateTimeFormat = createTimeFormat;
    }
}
