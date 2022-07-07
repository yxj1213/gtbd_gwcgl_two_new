package com.ttce.vehiclemanage.app;

import android.content.Context;
import android.util.Log;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.ttce.vehiclemanage.BuildConfig;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;

/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        AppUtils.syncIsDebug(getApplicationContext());
//        CrashHandler.getInstance().init(this);
        boolean isFirst=SPDefaultHelper.getBoolean(this,SPDefaultHelper.ISFIRST,true);
        if(isFirst==false){
            OtherInitialization();
        }
    }

    public void  OtherInitialization(){
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        MobSDK.submitPolicyGrantResult(true,null);
        CrashReport.initCrashReport(BaseApplication.getAppContext(), "ad7e1d0945", false); //bugly
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //SDKInitializer.setCoordType(CoordType.GCJ02);//声明全局坐标体系为火星坐标系  百度地图
        initEngineManager();
    }
    public void initEngineManager() {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(BaseApplication.getAppContext());
        }
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        // 默认本地个性化地图初始化方法
        SDKInitializer.setAgreePrivacy(this, true);
        try {
            SDKInitializer.initialize(this);
        } catch (BaiduMapSDKException e) {

        }
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        mBMapManager.init(new MyGeneralListener());
    }
    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetPermissionState(int iError) {
            // 非零值表示key验证未通过
            if (iError != 0) {
                // 授权Key错误：
                Log.d("百度地图相关信息：","请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: " + iError);
            } else {
                Log.d("百度地图相关信息：","key认证成功");
            }
        }
    }
    public BMapManager mBMapManager = null;
}
