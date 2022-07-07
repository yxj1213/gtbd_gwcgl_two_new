package com.ttce.vehiclemanage.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.ttce.vehiclemanage.app.AppApplication;

public class AppPreferenceSetting {
    private static final int[] MODES = new int[]{
            Activity.MODE_PRIVATE,//默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中，可以使用Activity.MODE_APPEND
            Activity.MODE_WORLD_READABLE,//表示当前文件可以被其他应用读取，
            Activity.MODE_WORLD_WRITEABLE,//表示当前文件可以被其他应用写入；
            //如果希望文件被其他应用读和写，可以传入:Activity.MODE_WORLD_READABLE+Activity.MODE_WORLD_WRITEABLE
            Activity.MODE_APPEND//该模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件
    };

    private static final String APP_SETTINGS = "ttce_settings";
    //告警接收
    private static final String ALARM_MSG_RECEIVE = "alarm_msg_receive";

    private static final String LAST_MONITOR_CAR = "last_monitor_car";

    private static final String LAST_SERVER_IP = "last_server_cip";
    private static final String LAST_SERVER_PORT = "last_server_port";
    private static final String SELECT_IP = "select_ip";
    private static final String LAST_SERVER_DOMAIN_NAME= "last_server_domain_name";

    public static void setAlarmMsgReceive(boolean receive) {
        setBooleanValue(ALARM_MSG_RECEIVE, receive);
    }

    public static boolean getAlarmMsgReceive() {
        return getBooleanValue(ALARM_MSG_RECEIVE);
    }

    public static void setLastMonitorCar(String car) {
        setStringValue(LAST_MONITOR_CAR, car);
    }

    public static String getLastMonitorCarMark() {
        return getStringValue(LAST_MONITOR_CAR);
    }

    public static void setLastServerIP(String ip) {
        setStringValue(LAST_SERVER_IP, ip);
    }
    public static String getLastServerIP() {
        return getStringValue(LAST_SERVER_IP);
    }
    public static void setSelectIP(String ip) {
        setStringValue(SELECT_IP, ip);
    }
    public static String getSelectIP() {
        return getStringValue(SELECT_IP);
    }

    public static void setLastServerPort(String port) {
        setStringValue(LAST_SERVER_PORT, port);
    }

    public static void setLastServerDomainName(String domain_name) {
        setStringValue(LAST_SERVER_DOMAIN_NAME, domain_name);
    }
    public static String getLastServerPort() {
        return getStringValue(LAST_SERVER_PORT);
    }
    public static String getLastServerDomainName() {
        return getStringValue(LAST_SERVER_DOMAIN_NAME);
    }
    //--------------------------------------------------------------------------------------

    public static void clear() {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(
                APP_SETTINGS, MODES[0]);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    private static void setStringValue(String key, String value) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(
                APP_SETTINGS, MODES[0]);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getStringValue(String key) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(
                APP_SETTINGS, MODES[0]);
        String value = settings.getString(key, null);
        return value;
    }

    public static void setBooleanValue(String key, boolean value) {
        SharedPreferences settings = AppApplication.getAppContext()
                .getSharedPreferences(APP_SETTINGS, MODES[0]);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanValue(String key) {
        SharedPreferences settings = AppApplication.getAppContext()
                .getSharedPreferences(APP_SETTINGS, MODES[0]);
        return settings.getBoolean(key, false);
    }

    private static void setIntValue(String key, int value) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(
                APP_SETTINGS, MODES[0]);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static int getIntValue(String key) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(
                APP_SETTINGS, MODES[0]);
        int value = settings.getInt(key, 0);
        return value;
    }

    public static void setLongValue(String key, long value) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(APP_SETTINGS, MODES[0]);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLongValue(String key) {
        SharedPreferences settings = AppApplication.getAppContext().getSharedPreferences(APP_SETTINGS, MODES[0]);
        return settings.getLong(key, -1);
    }
}
