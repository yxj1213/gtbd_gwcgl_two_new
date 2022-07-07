package com.ttce.vehiclemanage.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class LocationUtils implements BDLocationListener {

    private LocationClient locationClient = null;
    private LocationListener listener;

    public LocationUtils(Activity context, LocationListener listener){
        if (listener == null) {
            return;
        }

        this.listener = listener;
        // 获取的都是百度地图经纬度坐标
        try {
            /**
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            locationClient = new LocationClient(context, getLocationClientOption());
            locationClient.registerLocationListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 开始定位
    public void startLocation() {
        if (isStarted()) {
            stopLocation();
        }

        locationClient.start();
        listener.detecting();
    }

    // 结束定位
    public void stopLocation() {
        locationClient.stop();
    }

    private boolean isStarted() {
        return locationClient.isStarted();
    }

    private LocationClientOption getLocationClientOption() {
        /**
         * 定位SDK是否同意隐私政策接口
         * false不同意：不支持定位，SDK抛出异常
         * true同意：支持定位功能
         */
        LocationClient.setAgreePrivacy(true);
        LocationClientOption option = new LocationClientOption();
        // 设置定位模式，可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationMode.Hight_Accuracy);
        // 可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        // 可选，默认false,设置是否使用gps
        //option.setOpenGps(true);
        // 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(0);
        // 可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        // 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        //option.setLocationNotify(true);
        // 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(true);
        // 超时时间
        // option.setTimeOut(12 * 1000);

        return option;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null) {
            listener.failed();
            stopLocation();
            return;
        }

        listener.succeed(bdLocation);
        stopLocation();
    }

    public static boolean isOpen(Context context) {
        if (context == null) {
            return false;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(Context
                .LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    public static void toSetting(Activity activity, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, requestCode);
    }

    public static String getAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            return "";
        }
        return address.replace("中国", "");
    }

    public interface LocationListener {
        void detecting();

        void succeed(BDLocation location);

        void failed();
    }
}
