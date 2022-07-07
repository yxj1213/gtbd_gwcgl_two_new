package com.ttce.vehiclemanage.api;

import android.util.Log;

import com.ttce.vehiclemanage.utils.AppPreferenceSetting;

public class ApiConstants {


    public static String NETEAST_HOST = "http://"+AppPreferenceSetting.getLastServerIP()+":"+AppPreferenceSetting.getLastServerPort()+"/";//"http://202.99.212.205:43967/";//"http://192.168.31.222:43967/";
    public static boolean HOST_CHANGE=true;
    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.BASE_HOST:
                if(AppPreferenceSetting.getSelectIP()!=null&&!AppPreferenceSetting.getSelectIP().equals("")&&AppPreferenceSetting.getSelectIP().equals("v4_ym")){
                    host="http://"+AppPreferenceSetting.getLastServerDomainName();
                }else{
                    host = NETEAST_HOST;
                }
                break;
            default:
                host="";
                break;
        }
        return host;
    }
}
