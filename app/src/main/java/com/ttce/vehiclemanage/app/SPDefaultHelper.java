package com.ttce.vehiclemanage.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hk on 2019/7/2
 */

public class SPDefaultHelper {

    public static final String LAST_FRAGMENT_TAG = "last_fragment_tag";
    public static final String USER = "user_data";

    public static final String SERVER_VERSION = "version_info";
    public static final String DOWNLOAD_REFERENCE = "download_reference";
    public static final String APK_STATUS = "apk_status";
    public static final String CAR_DATAS = "carDatas";
    public static final String ISFIRST = "isFirst";
    public static final String ISCLOSEACCOUNT = "iscloseaccount";
    public static final String CHECKPERMISSION = "check_permission";


    public static final String USER_REMMBRE = "user_rember";
    public static final String USER_ID = "user_id";
    public static final String USER_PWD = "user_pwd";

    public static final String SEARCH_RECORD = "search_record";

    public static final String USER_ISSTAFF = "user_isstaf";

    private static SharedPreferences preferences;

    public static SharedPreferences getPreferences() {
        if (preferences == null) {
            preferences = AppApplication.getAppContext().getSharedPreferences("session", Context.MODE_APPEND);
        }
        return preferences;
    }

    public static void remove(String key) {
        getPreferences().edit().remove(key).commit();
    }

    public static void saveString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        return getPreferences().getString(key, "");
    }


    public static void saveLong(String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return getPreferences().getLong(key, -1);
    }

    public static void saveInt(String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public static int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    public static void saveBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public static void putString(Context ctx, String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }
}
