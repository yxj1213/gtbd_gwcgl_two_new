package com.ttce.vehiclemanage.ui.main.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.lbsapi.panoramaview.PanoramaViewListener;
import com.baidu.mapapi.SDKInitializer;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.LoginException;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.daynightmodeutils.ChangeModeController;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.bean.TabEntity;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.main.activity.home.CreateBusinessActivity;
import com.ttce.vehiclemanage.ui.main.adapter.home.BusinessBeanAdapter;
import com.ttce.vehiclemanage.ui.main.fragment.HomePageFragment;
import com.ttce.vehiclemanage.ui.main.fragment.MyFragment;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.ui.main.fragment.VehicleMonitoringFragment;
import com.ttce.vehiclemanage.ui.main.fragment.WorkBeanchFragment;
import com.ttce.vehiclemanage.ui.mine.activity.NewPersonDetailActivity;
import com.ttce.vehiclemanage.ui.usermanage.activity.LoginActivity;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.ui.usermanage.model.MainModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.MainPresenter;
import com.ttce.vehiclemanage.utils.AppUtils;
import com.ttce.vehiclemanage.utils.DeviceUtils;
import com.ttce.vehiclemanage.utils.NoPerson_NoCreatebusinessAlertDialogUtils;
import com.ttce.vehiclemanage.utils.OtherUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.hugeterry.updatefun.UpdateFunGO;

/**
 * Created by hk on 2019/6/18.
 */

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View,NoPerson_NoCreatebusinessAlertDialogUtils.DialogDataListener, OnButtonClickListener, OnDownloadListener {
    private final String TAG = "MainActivity";
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    @Bind(R.id.fl_panorama)
    FrameLayout fl_panorama;
    @Bind(R.id.iv_full)
    ImageView iv_full;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_qymc)
    TextView tv_qymc;
    @Bind(R.id.list_view)
    ListView list_view;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    // 是否全屏
    private boolean isFullScreen = false;



    private String[] mTitles = {"首页","车辆监控", "我要用车","工作台", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.icon_bottom_one, R.mipmap.icon_bottom_two, R.mipmap.icon_bottom_three, R.mipmap.icon_bottom_four, R.mipmap.icon_bottom_five};
    private int[] mIconSelectIds = {
            R.mipmap.icon_bottom_one_sel, R.mipmap.icon_bottom_two_sel, R.mipmap.icon_bottom_three_sel, R.mipmap.icon_bottom_four_sel, R.mipmap.icon_bottom_five_sel};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomePageFragment homePageFragment;
    private VehicleMonitoringFragment vehicleMonitoringFragment;
    private MyNeedCarFragment myNeedCarFragment;
    private WorkBeanchFragment workBeanchFragment;
    private MyFragment myFragment;
    private static int tabLayoutHeight;
    private String curFragmentTag;
    private List<MonitorResponseBean> carDatas;

    /**
     * 入口
     *
     * @param activity
     */
    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.fade_in,
//                com.jaydenxiao.common.R.anim.fade_out);
    }

    /**
     * 展示全景地图
     *
     * @param lng
     * @param lat
     */
    PanoramaView panoramaView = null;
    public void showVRMap(double lng, double lat) {
        fl_panorama.setVisibility(View.VISIBLE);
        if (fl_panorama.getChildCount() <= 1) {
            panoramaView = new PanoramaView(this);
            panoramaView.setPanoramaImageLevel(PanoramaView.ImageDefinition.ImageDefinitionMiddle);
            fl_panorama.addView(panoramaView, 0);
        } else {
            panoramaView = (PanoramaView) fl_panorama.getChildAt(0);
        }
        // 测试回调函数,需要注意的是回调函数要在setPanorama()之前调用，否则回调函数可能执行异常
        panoramaView.setPanoramaViewListener(new PanoramaViewListener() {

            @Override
            public void onLoadPanoramaBegin() {
                Log.d("11111111111111","onLoadPanoramaStart...");
            }

            @Override
            public void onLoadPanoramaEnd(String json) {
                Log.d("11111111111111","onLoadPanoramaEnd : " + json);
            }

            @Override
            public void onLoadPanoramaError(String error) {
                Log.d("11111111111111","onLoadPanoramaError : " + error);
            }

            @Override
            public void onDescriptionLoadEnd(String json) {

            }

            @Override
            public void onMessage(String msgName, int msgType) {

            }

            @Override
            public void onCustomMarkerClick(String key) {

            }

            @Override
            public void onMoveStart() {

            }

            @Override
            public void onMoveEnd() {

            }
        });
        panoramaView.setPanoramaImageLevel(PanoramaView.ImageDefinition.ImageDefinitionMiddle);
        panoramaView.setPanorama(lng, lat,PanoramaView.COORDTYPE_BD09LL);

    }

    @OnClick({R.id.tv_other_business,R.id.tv_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_other_business:
                break;
            case R.id.tv_create:
                CreateBusinessActivity.goToPage(this);
                break;
        }
    }
    List<ChangeCompanyBean> businessBeanList;
    private int mselectPosition = -1;//用于记录用户选择的变量
    private ChangeCompanyBean selectBrand ;//用于记录用户选择的对象
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void openDrawerLayout() {
        mPresenter.getUserInfo();
        mPresenter.getChangeCompany();
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        MainActivity.this.getWindow().setStatusBarColor(MainActivity.this.getResources().getColor(R.color.main_left_bg_color));
        // 按钮按下，将抽屉打开
        mDrawerLayout.openDrawer(Gravity.LEFT);
        // 监听抽屉的滑动事件
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float leftScale = 0.5f + slideOffset * 0.5f;
                mMenu.setAlpha(leftScale);
                mMenu.setScaleX(leftScale);
                mMenu.setScaleY(leftScale);
                mContent.setPivotX(0);
                mContent.setTranslationX(mMenu.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                MainActivity.this.getWindow().setStatusBarColor(MainActivity.this.getResources().getColor(R.color.app_main_colors));
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * 展示全景地图
     *
     */
    public void updateVRMap(double lng, double lat) {
        if (fl_panorama.getChildCount() > 1 && fl_panorama.getChildAt(0) instanceof PanoramaView) {
            ((PanoramaView) fl_panorama.getChildAt(0)).setPanorama(lng, lat);
        }
    }

    /**
     * 隐藏全景地图
     */
    public void hideVRMap() {
        fl_panorama.setVisibility(View.GONE);
        if (fl_panorama.getChildCount() > 1) {
            fl_panorama.removeViewAt(0);
        }
    }

    @OnClick(R.id.iv_full)
    void onVRFullScreen(View view) {
        if (isFullScreen) {
            iv_full.setImageResource(R.mipmap.icon_full_screen);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fl_panorama.getLayoutParams();
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            params.height = DeviceUtils.dip2px(this, 250f);
            fl_panorama.setLayoutParams(params);
            fl_panorama.setLayoutParams(params);
            vehicleMonitoringFragment.showPanel();
        } else {
            iv_full.setImageResource(R.mipmap.icon_exit_full);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fl_panorama.getLayoutParams();
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
            fl_panorama.setLayoutParams(params);
            vehicleMonitoringFragment.hidePanel();
        }
        isFullScreen = !isFullScreen;
    }

    @OnClick(R.id.fl_panorama)
    public void vrClick(View view) {
        // TODO: 2019/10/12 nothing
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        //如果你想通过Dialog来进行下载，可以如下设置
    //    UpdateKey.DialogOrNotification=UpdateKey.WITH_DIALOG;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        UpdateFunGO.init(this);

//        initPush(); //TODO 友盟推送代码


        //1是实名 2是未实名
        if (UserManager.getLoginBean()!=null) {
            if(UserManager.getLoginBean().getRealCheck()!=1){
                NoPerson_NoCreatebusinessAlertDialogUtils.showAlertDialog(this,"个人未实名",this);
            }
        }

    }



//    private void initPush() {
//        //获取消息推送代理示例
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
//                LogUtils.loge("注册成功：deviceToken：-------->  " + deviceToken);
//                mPushAgent.setAlias("UserManager.getLoginBean().getUserId()", "vehiclemanage", new UTrack.ICallBack() {
//                    @Override
//                    public void onMessage(boolean isSuccess, String message) {
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                LogUtils.loge("注册失败：-------->  " + "s:" + s + ",s1:" + s1);
//            }
//        });
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        if(null!= savedInstanceState) {
            savedInstanceState=null;
        }
        //切换daynight模式要立即变色的页面
        ChangeModeController.getInstance().init(this, R.attr.class);
        super.onCreate(savedInstanceState);

        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);

        //初始化菜单
        initTab();
        //初始化frament
        initFragment(savedInstanceState);
        if(tabLayout!=null){
            tabLayout.measure(0, 0);
            tabLayoutHeight = tabLayout.getMeasuredHeight();
        }
        EventBus.getDefault().register(this);

    }

    private SDKReceiver mReceiver;
    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                ToastUtil.showToast("key 验证出错! 错误码 :" + intent.getIntExtra
                        (SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, 0)
                        +  " ; 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                //"key 验证成功! 功能可以正常使用"
            } else if (action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                ToastUtil.showToast("网络出错");
            }
        }
    }



    /**
     * 初始化tab
     */
    private void initTab() {
        mTabEntities=new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        if(tabLayout!=null){

            tabLayout.setTabData(mTabEntities);
            tabLayout.setCurrentTab(0);
            tabLayout.notifyDataSetChanged();
            LogUtils.logd("主页菜单position===" +"rrrdd---"+tabLayout.getCurrentTab()+"---");
            //点击监听
            tabLayout.setSelected(true);
            tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    LogUtils.logd("主页菜单position===" + position+"rrr---"+tabLayout.getCurrentTab()+"---");
                    hideVRMap();
                    SwitchTo(position);
                }


                @Override
                public void onTabReselect(int position) {
                    LogUtils.logd("主页菜单position===" + position+"rrr--重新-"+tabLayout.getCurrentTab()+"---");
                }

            });
        }
//        startUpdate3();
//        tabLayout.showMsg(0, 55);
//        tabLayout.setMsgMargin(0, -20, 5);
    }

    /**
     * 初始化碎片
     */
    int   currentTabPosition = 0;
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {

            homePageFragment = (HomePageFragment) getSupportFragmentManager().findFragmentByTag("homePageFragment");
            vehicleMonitoringFragment = (VehicleMonitoringFragment) getSupportFragmentManager().findFragmentByTag("vehicleMonitoringFragment");
            myNeedCarFragment = (MyNeedCarFragment) getSupportFragmentManager().findFragmentByTag("myNeedCarFragment");
            workBeanchFragment = (WorkBeanchFragment) getSupportFragmentManager().findFragmentByTag("workBeanchFragment");
            myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("myFragment");

        } else {
            homePageFragment = new HomePageFragment();
            vehicleMonitoringFragment = new VehicleMonitoringFragment();
            myNeedCarFragment = new MyNeedCarFragment();
            workBeanchFragment = new WorkBeanchFragment();
            myFragment = new MyFragment();

            transaction.add(R.id.fl_body, homePageFragment, "homePageFragment");
            transaction.add(R.id.fl_body, vehicleMonitoringFragment, "vehicleMonitoringFragment");
            transaction.add(R.id.fl_body, myNeedCarFragment, "myNeedCarFragment");
            transaction.add(R.id.fl_body, workBeanchFragment, "workBeanchFragment");
            transaction.add(R.id.fl_body, myFragment, "myFragment");
        }
        transaction.commit();

        SwitchTo(currentTabPosition);
    }

    /**
     * 切换
     */
    public void SwitchTo(int position) {
        if(tabLayout!=null){
            tabLayout.setCurrentTab(position);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                curFragmentTag = "homePageFragment";
                homePageFragment.setShow(true);

                myNeedCarFragment.setShow(false);
                vehicleMonitoringFragment.setShow(false);
                workBeanchFragment.setShow(false);
                myFragment.setShow(false);
                transaction.show(homePageFragment);
                transaction.hide(vehicleMonitoringFragment);
                transaction.hide(myNeedCarFragment);
                transaction.hide(workBeanchFragment);
                transaction.hide(myFragment);
                transaction.commitAllowingStateLoss();


                break;
            //车辆监控
            case 1:
                curFragmentTag = "vehicleMonitoringFragment";
                vehicleMonitoringFragment.setShow(true);

                homePageFragment.setShow(false);
                myNeedCarFragment.setShow(false);
                workBeanchFragment.setShow(false);
                myFragment.setShow(false);
                transaction.hide(homePageFragment);
                transaction.show(vehicleMonitoringFragment);
                transaction.hide(myNeedCarFragment);
                transaction.hide(workBeanchFragment);
                transaction.hide(myFragment);
                transaction.commitAllowingStateLoss();
                break;
            //我要用车
            case 2:
                curFragmentTag = "myNeedCarFragment";
                myNeedCarFragment.setShow(true);

                homePageFragment.setShow(false);
                vehicleMonitoringFragment.setShow(false);
                workBeanchFragment.setShow(false);
                myFragment.setShow(false);
                transaction.hide(homePageFragment);
                transaction.hide(vehicleMonitoringFragment);
                transaction.show(myNeedCarFragment);
                transaction.hide(workBeanchFragment);
                transaction.hide(myFragment);
                transaction.commitAllowingStateLoss();
                break;
            //工作台
            case 3:
                curFragmentTag = "workBeanchFragment";
                workBeanchFragment.setShow(true);

                homePageFragment.setShow(false);
                vehicleMonitoringFragment.setShow(false);
                myNeedCarFragment.setShow(false);
                myFragment.setShow(false);
                transaction.hide(homePageFragment);
                transaction.hide(vehicleMonitoringFragment);
                transaction.hide(myNeedCarFragment);
                transaction.show(workBeanchFragment);
                transaction.hide(myFragment);
                transaction.commitAllowingStateLoss();
                break;
            //我的
            case 4:
                curFragmentTag = "myFragment";
                myFragment.setShow(true);

                homePageFragment.setShow(false);
                vehicleMonitoringFragment.setShow(false);
                myNeedCarFragment.setShow(false);
                workBeanchFragment.setShow(false);
                transaction.hide(homePageFragment);
                transaction.hide(vehicleMonitoringFragment);
                transaction.hide(myNeedCarFragment);
                transaction.hide(workBeanchFragment);
                transaction.show(myFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    /**
     * 菜单显示隐藏动画
     *
     * @param showOrHide
     */
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (!showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                tabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
    }




    /**
     * 监听全屏视频时返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
//            moveTaskToBack(false);
            hideVRMap();
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finishAffinity();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mPresenter!=null){
            mPresenter.getAppUpdate();
        }
        UpdateFunGO.onResume(this);
        if (fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).onResume();
        }
//        mPresenter.getChangeCompany();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }
    boolean isConfigChange=false;
    @Override
    protected void onDestroy() {
        //当修改了系统的样式后，会走此方法
        if (!isConfigChange&&UserManager.getLoginBean()!=null&&!UserManager.getLoginBean().getToken().equals("")) {
//            restartApp(MainActivity.this);
            MainActivity.goToPage(this);
        }
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ChangeModeController.onDestory();
        if (fl_panorama != null && fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).destroy();
        }
        // 取消监听 SDK 广播
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment f = getSupportFragmentManager().findFragmentByTag(curFragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
        f.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 设置车俩的数量
     *
     * @param number
     */
    public void setCarNumber(int number) {
        tabLayout.showMsg(0, number);
        if(number>0){
            if(number>=1000){
                tabLayout.getMsgView(0).setText("999+");
            }else{
                tabLayout.getMsgView(0).setText(String.valueOf(number));
            }
            tabLayout.setMsgMargin(0, -20, 0);
        }else{
            tabLayout.getMsgView(0).setVisibility(View.GONE);
        }
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        stopProgressDialog();
        if(userInfoBean.getUserRealName().equals("")){
            tv_name.setText(userInfoBean.getUserNikeName());
        }else{
            tv_name.setText(OtherUtil.setName(userInfoBean.getUserRealName()));
        }

        if(userInfoBean.getCompanyName().equals("")){
            tv_qymc.setVisibility(View.GONE);
        }else{
            tv_qymc.setVisibility(View.VISIBLE);
            tv_qymc.setText(userInfoBean.getCompanyName());
        }
        GlideUtils.displayHead(this,ivHead,userInfoBean.getHeadPic(),R.mipmap.icon_head,R.mipmap.icon_head);
    }

    @Override
    public void getChangeCompanys(List<ChangeCompanyBean> list) {
        this.businessBeanList=list;
        mselectPosition=-1;
        if(list!=null&&list.size()>=1){
            for (int i=0; i<businessBeanList.size(); i++){
                if(UserManager.getLoginBean().getCompanyId().equals(businessBeanList.get(i).getCompanyId())){
                    mselectPosition=i;
                    selectBrand=businessBeanList.get(i);
                }
            }
        }
        myAdapter = new BusinessBeanAdapter(MainActivity.this,this, businessBeanList,mselectPosition);
        list_view.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new BusinessBeanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChangeCompanyBean changeCompanyBean, int selectPosition) {
                if(selectPosition!=myAdapter.getSelectPosition()){
                    startProgressDialog();
                    selectBrand=changeCompanyBean;
                    mselectPosition=selectPosition;
                    mPresenter.getSaveChangeCompany(selectBrand.getCompanyId());
                }else{
                    ToastUtil.showToast("当前企业已选中");
                }
            }
        });
    }
    BusinessBeanAdapter myAdapter;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        isConfigChange=true;
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void getSaveChangeCompanys(ChangeCompanySaveReturnBean str) {

        myAdapter.setSelectPosition(mselectPosition);
        mPresenter.getUserInfo();
        LoginBean loginBean= UserManager.getLoginBean();
        loginBean.setCompanyId(str.getCompanyId());
        loginBean.setCompanyName(str.getCompanyName());
        loginBean.setStaffId(str.getStaffId());
        UserManager.saveSerialize(new LoginBean(loginBean.getRealCheck(),loginBean.getToken(),loginBean.getUserPhone(),str.getCompanyId(),str.getCompanyName(),loginBean.getDepartMentId(),str.getUserId(),str.getStaffId(),loginBean.getUserRealName(),loginBean.getIdCard()));
        EventBus.getDefault().post(new MessageEvent(AppConstant.CHANGE_COMPANYID,"刷新"));
    }
//
//    @Override
//    public void realCheckInfoData(RealCheckBean checkBean) {
//        if (checkBean == null) {
//            return;
//        }
//        switch (checkBean.getRealCheck()) {
//            // TODO: 2019/7/25 根据返回的状态，进行业务操作
//        }
//    }

    @Override
    public void returnAppUpdateData(AppUpdateBean appUpdateBean) {
        startUpdate3(appUpdateBean);
    }
    private DownloadManager manager;
    public void startUpdate3(AppUpdateBean appUpdateBean) {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置对话框强制更新时进度条和文字的颜色
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(appUpdateBean.isIsForceUpdating()==1?true:false)
                //设置对话框按钮的点击监听
                .setButtonClickListener(MainActivity.this)
                //设置下载过程的监听
                .setOnDownloadListener(MainActivity.this);

        manager = DownloadManager.getInstance(this);
        manager.setApkName(AppUtils.getAppName(this))
                .setApkUrl(appUpdateBean.getAppVersionUrl())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(false)
                .setConfiguration(configuration)
                .setApkVersionCode(appUpdateBean.getAppVersionCode())
                .setApkVersionName(appUpdateBean.getAppVersionName())
                //               .setApkSize("20.4")
                .setApkDescription(appUpdateBean.getAppVersionDescription())
//                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
                .download();
    }
    @Override
    public void returnAppLogout(Boolean isLogout) {

    }

    @Override
    public void returnCloseAccountState(CloseAccountStateBean closeAccountStateBean) {

    }

    @Override
    public void logout() {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        stopProgressDialog();
    }

    public List<MonitorResponseBean> getCarDatas() {
        return carDatas;
    }

    public void setCarDatas(List<MonitorResponseBean> carDatas) {
        this.carDatas = carDatas;
        SPDefaultHelper.saveString(SPDefaultHelper.CAR_DATAS,OtherUtil.setDataList(carDatas));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginException loginException) {
        ToastUtil.showToast(loginException.getMessage());
        if(!loginException.getMessage().equals(mContext.getResources().getString(com.jaydenxiao.common.R.string.error_networktimeout))&&!loginException.getMessage().equals(mContext.getResources().getString(com.jaydenxiao.common.R.string.error_ip))&&!loginException.getMessage().equals(mContext.getResources().getString(com.jaydenxiao.common.R.string.error_jiekou_networktimeout))){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("isNetWork","yes");//根据这个字段判断是正常进入登陆界面还是异常进入登陆界面（no是异常。）
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            AppApplication.getAppContext().startActivity(intent);
//        SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, false);
//        SPDefaultHelper.saveString(SPDefaultHelper.USER_ID, "");
//        SPDefaultHelper.saveString(SPDefaultHelper.USER_PWD, "");
            UserManager.logout();
            finish();
        }
    }

    @Override
    public void ondialogwc(String mtype) {
        if(mtype.equals("个人未实名")){
            NewPersonDetailActivity.goToPage(this,0,UserManager.getLoginBean().getStaffId(),UserManager.getLoginBean().getUserId(),UserManager.getLoginBean().getCompanyId());
        }
    }

    @Override
    public void onButtonClick(int id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }
}