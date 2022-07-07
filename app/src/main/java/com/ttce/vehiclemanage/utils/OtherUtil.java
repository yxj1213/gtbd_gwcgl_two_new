package com.ttce.vehiclemanage.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.util.ArrayList;
import java.util.List;

public class OtherUtil {
    public static String setName(String s){
        if(s.equals("")){
            return "无真实姓名";
        }
        StringBuffer smallname=new StringBuffer(s);
        String s1="*";
        for (int i = 1; i < smallname.length()-1; i++) {
            s1= s1 + "*";
        }
        smallname.replace(1,s.length(),s1);
        return smallname.toString();
    }
    public static String setIp(String s){
        if(s.equals("")){
            return "";
        }
        StringBuffer smallname=new StringBuffer(s);
        String s1="*";
        for (int i = 0; i < smallname.length()-1; i++) {
            s1= s1 + "*";
        }
        smallname.replace(0,s.length(),s1);
        return smallname.toString();
    }
    public static String setNumber(String s){
        if(s.length()!=11){
            return "手机号格式错误";
        }
        s = s.replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{2})", "*"); //这里*只要一个，因为会替代多次，每次一个。
        return s;
    }

    public static String setIdCard(String s){
        if(s.length()!=18){
            return "身份证格式错误";
        }
        s = s.replaceAll("(?<=[\\d]{1})\\d(?=[\\d]{1})", "*"); //这里*只要一个，因为会替代多次，每次一个。
        return s;
    }

    /**
     * 保存List
     * @param datalist
     */
    public static<T>  String setDataList(List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return "";

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        return strJson;
    }

    /**
     * 获取List
     * @return
     */
    public static List<MonitorResponseBean> getDataList(String strJson) {
        List<MonitorResponseBean> datalist=new ArrayList<MonitorResponseBean>();
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<MonitorResponseBean>>() {
        }.getType());
        return datalist;

    }
}

