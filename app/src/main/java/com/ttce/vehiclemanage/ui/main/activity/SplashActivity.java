package com.ttce.vehiclemanage.ui.main.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.jaydenxiao.common.base.Base2Activity;
import com.jaydenxiao.common.baserx.LoginException;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.api.ApiConstants;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.usermanage.activity.LoginActivity;
import com.ttce.vehiclemanage.ui.usermanage.activity.YszcDialog;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.ui.usermanage.model.MainModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.MainPresenter;
import com.ttce.vehiclemanage.utils.AppPreferenceSetting;
import com.ttce.vehiclemanage.utils.AppUtils;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import butterknife.Bind;
import cn.hugeterry.updatefun.UpdateFunGO;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by hk on 2019/6/18.
 */

public class SplashActivity extends Base2Activity<MainPresenter, MainModel> implements MainContract.View, OnButtonClickListener, OnDownloadListener {

    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_name)
    TextView tvName;

    @Override
    public int getLayoutId() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        }
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(SplashActivity.this, mModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMsgWhat()){
            case AppConstant.MESSAGE_YSXY:
                // ????????????
                //requestPermission();
                getapplication();
              /*  LoginActivity.goToPage(SplashActivity.this);
                finish();*/
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }
    @Override
    public void initView() {
        //app??????????????????
        UpdateFunGO.init(this);
        EventBus.getDefault().register(this);
        SetTranslanteBar();
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(5000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
         isFirst=SPDefaultHelper.getBoolean(SplashActivity.this,SPDefaultHelper.ISFIRST,true);
        if(isFirst==true){
            YszcDialog registeDialog = YszcDialog.newInstance();
            registeDialog.show(getSupportFragmentManager(), SplashActivity.class.getSimpleName());
        }else{
           getapplication();
        }
    }
    boolean isFirst;
    private void initupdate() {
        mPresenter.getAppUpdate();
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void getChangeCompanys(List<ChangeCompanyBean> list) {

    }

    @Override
    public void getSaveChangeCompanys(ChangeCompanySaveReturnBean str) {

    }

//    @Override
//    public void realCheckInfoData(RealCheckBean checkBean) {
//
//    }

    @Override
    public void returnAppUpdateData(AppUpdateBean appUpdateBean) {
        if(AppUtils.getVersionCode(this)>=appUpdateBean.getAppVersionCode()){
            if(isFirst==true){
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);//??????3???
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        LoginActivity.goToPage(SplashActivity.this,"yes");
                        finish();
                    }

                }.start();
            }else{
                if (UserManager.getLoginBean()!=null&&!UserManager.getLoginBean().getToken().equals("")) {
//                    startProgressDialog();
                    mPresenter.getCloseAccountState();
                } else {
                    LoginActivity.goToPage(SplashActivity.this,"yes");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Thread.sleep(3000);//??????3???
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }

                    }.start();
                }
            }
        }else{
            startUpdate3(appUpdateBean);
        }
    }

    public DownloadManager manager;

    public void startUpdate3(AppUpdateBean appUpdateBean) {
        /*
         * ??????????????????????????????
         * ?????????
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //??????????????????
                .setEnableLog(true)
                //????????????????????????
                //.setHttpManager()
                //????????????????????????????????????
                .setJumpInstallPage(true)
                //??????????????????????????? (??????????????????demo???????????????)
                //.setDialogImage(R.drawable.ic_dialog)
                //?????????????????????
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //?????????????????????????????????????????????????????????
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                //???????????????????????????
                .setDialogButtonTextColor(Color.WHITE)
                //?????????????????????????????????
                .setShowNotification(true)
                //??????????????????????????????toast
                .setShowBgdToast(false)
                //??????????????????
                .setForcedUpgrade(appUpdateBean.isIsForceUpdating()==1?true:false)
                //????????????????????????????????????
                .setButtonClickListener(SplashActivity.this)
                //???????????????????????????
                .setOnDownloadListener(SplashActivity.this);

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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginException loginException) {
           ToastUtil.showToast(loginException.getMessage());
            Intent intent = new Intent(this, LoginActivity.class);
            if(loginException.getMessage().equals(mContext.getResources().getString(com.jaydenxiao.common.R.string.error_ip))){
                intent.putExtra("isNetWork","no");//????????????????????????????????????????????????????????????????????????????????????no???????????????
            }else{
                intent.putExtra("isNetWork","yes");//????????????????????????????????????????????????????????????????????????????????????no???????????????
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            AppApplication.getAppContext().startActivity(intent);
//        SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, false);
//        SPDefaultHelper.saveString(SPDefaultHelper.USER_ID, "");
//        SPDefaultHelper.saveString(SPDefaultHelper.USER_PWD, "");
            UserManager.logout();
            finish();
    }
    @Override
    public void returnCloseAccountState(CloseAccountStateBean closeAccountStateBean) {
        if(closeAccountStateBean!=null){
            if(closeAccountStateBean.isIsApply()==false){//false ?????????????????????????????????
                MainActivity.goToPage(SplashActivity.this);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);//??????3???
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }

                }.start();

            }else{
                if(closeAccountStateBean.getCloseStatus()!=30){//10,???????????????20???????????????30???????????????
                    MainActivity.goToPage(SplashActivity.this);
                    finish();
                }else{
                    MaterialDialog dialog = new MaterialDialog.Builder(this).theme(Theme.LIGHT).
                            title("??????").content("?????????????????????????????????????????????").negativeColorRes(R.color.blue_color1)
                            .negativeText("??????").positiveText("??????").onPositive(new MaterialDialog
                                    .SingleButtonCallback() {

                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull
                                        DialogAction which) {
                                    dialog.dismiss();
                                    LoginActivity.goToPage(SplashActivity.this,"yes");
                                    finish();
                                }
                            }).onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog materialDialog,DialogAction dialogAction) {
                                    materialDialog.dismiss();
                                    finish();
                                }
                            })
                            .build();
                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            }

        }
    }

    @Override
    public void logout() {

    }

    public void getapplication(){
       /* String saveloginip=SPDefaultHelper.getString("saveloginip");
        SaveLoginIPBean saveLoginIPBean=new SaveLoginIPBean();
        if(saveloginip==null||saveloginip.equals("")){
            saveLoginIPBean.setType("v2");
            AppPreferenceSetting.setLastServerIP(AppConstant.DEFAULT_IP);
            AppPreferenceSetting.setLastServerPort(AppConstant.DEFAULT_PORT);
        }else{
            SaveLoginIPBean msaveloginip=new Gson().fromJson(saveloginip, SaveLoginIPBean.class);
            if(msaveloginip.getType().equals("")||msaveloginip.getType().equals("v2")){
                AppPreferenceSetting.setLastServerIP(AppConstant.DEFAULT_IP);
                AppPreferenceSetting.setLastServerPort(AppConstant.DEFAULT_PORT);
            }else{
                rbV4.setChecked(true);
                newMoren(R.id.rbV4,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
            }
        }*/

        if (TextUtils.isEmpty(AppPreferenceSetting.getLastServerIP()) || TextUtils.isEmpty(AppPreferenceSetting.getLastServerPort())) {
            AppPreferenceSetting.setLastServerIP(AppConstant.DEFAULT_IP);
            AppPreferenceSetting.setLastServerPort(AppConstant.DEFAULT_PORT);
        }
        if (TextUtils.isEmpty(AppPreferenceSetting.getSelectIP())) {
            AppPreferenceSetting.setSelectIP("v2");
        }

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        ((AppApplication)SplashActivity.this.getApplicationContext()).OtherInitialization();
        //        UMConfigure.init(this, "5d380889570df375010005cb", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

         initupdate();

AppApplication.appIsCrash=1;
//                AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);
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

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        LoginActivity.goToPage(SplashActivity.this,"yes");
        finish();
    }

}
