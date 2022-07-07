package com.ttce.vehiclemanage.ui.usermanage.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.security.LoginUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.api.ApiConstants;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.LoginImageVerificationBean;
import com.ttce.vehiclemanage.bean.PostServerBean;
import com.ttce.vehiclemanage.bean.SaveLoginIPBean;
import com.ttce.vehiclemanage.bean.VerificationBean;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;
import com.ttce.vehiclemanage.ui.main.activity.SplashActivity;
import com.ttce.vehiclemanage.ui.usermanage.contract.LoginContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.ui.usermanage.model.LoginModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.LoginPresenter;
import com.ttce.vehiclemanage.utils.AppPreferenceSetting;
import com.ttce.vehiclemanage.utils.DomainCheck;
import com.ttce.vehiclemanage.utils.OtherUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.glide.GlideUtils;
import com.ttce.vehiclemanage.widget.BlockPuzzleDialog;
import com.ttce.vehiclemanage.widget.IpLinearlayout.IPEditText;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by hk on 2019/6/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, RegisteDialog.SureListener {


    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_pic_yzm)
    EditText etPicYzm;
    @Bind(R.id.rl_pic_yzm)
    RelativeLayout rl_pic_yzm;
    @Bind(R.id.iv_imagecode)
    ImageView ivImagecode;
    @Bind(R.id.cb_rember)
    CheckBox cbRember;

    VerificationBean mVerificationBean;
    public static LoginActivity  mcontext;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        mcontext=this;
        String type= this.getIntent().getExtras().getString("isNetWork");
        if(type.equals("yes")){
            mPresenter.getImgVaild();
        }
        if (SPDefaultHelper.getBoolean(SPDefaultHelper.USER_REMMBRE)) {
            etName.setText(SPDefaultHelper.getString(SPDefaultHelper.USER_ID));
            etPwd.setText(SPDefaultHelper.getString(SPDefaultHelper.USER_PWD));

            cbRember.setButtonDrawable(R.mipmap.icon_rb_sel);
            cbRember.setChecked(true);
        }else{
            cbRember.setButtonDrawable(R.drawable.soild_gray_yuan);
            cbRember.setChecked(false);
        }
        cbRember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b==true){
                        cbRember.setButtonDrawable(R.mipmap.icon_rb_sel);
                        cbRember.setChecked(true);
                    }else{
                        cbRember.setButtonDrawable(R.drawable.soild_gray_yuan);
                        cbRember.setChecked(false);
                    }
            }
        });
    }

    BlockPuzzleDialog dialog;
    public static void goToPage(Activity activity,String isnetwork) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("isNetWork","yes");//根据这个字段判断是正常进入登陆界面还是异常进入登陆界面（no是异常。）
        activity.startActivity(intent);
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ivImagecode.performClick();
    }

    @Override
    public void returnLoginData(LoginBean newsDetail) {
        if (cbRember.isChecked()) {
            SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, true);
            SPDefaultHelper.saveString(SPDefaultHelper.USER_ID, etName.getText().toString());
        } else {
            SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, false);
        }

        //1是实名 2是未实名
//        if (newsDetail.getRealCheck() != 1) {
//            AuthUseActivity.goToPage(this, newsDetail.getRealCheck());
//            //UserManager.loginBean = null;
//        } else {
        UserManager.saveSerialize(newsDetail);
        MainActivity.goToPage(this);
//        }
        finish();
    }

    @Override
    public void returnImgVaild(VerificationBean verificationBean) {
        mVerificationBean = verificationBean;
        if(verificationBean != null && verificationBean.isIsVerification()==true){
            rl_pic_yzm.setVisibility(View.VISIBLE);
            GlideUtils.displayimg(this,ivImagecode,verificationBean.getVerificationImgUrl(),R.drawable.ic_image_loading,R.drawable.ic_image_loading);
            //TODO  图片验证码
//            dialog=new BlockPuzzleDialog(this);
//            dialog.show();
        }else{
            rl_pic_yzm.setVisibility(View.GONE);
        }
    }

    @Override
    public void returnImgLoginVaild(LoginImageVerificationBean loginImageVerificationBean) {
        dialog.getimgVerification(loginImageVerificationBean);
    }

    @Override
    public void returnCheckImgLoginVaild(String tag) {
        dialog.checkimgVerification(tag);
    }

    @Override
    public void returnPostServerBeanView(PostServerBean postServerBean) {
        stopProgressDialog();
        if(materialDialog!=null&&materialDialog.isShowing()){
            View view = materialDialog.getCustomView();
            RadioButton v2RB = view.findViewById(R.id.rbV2);
            IPEditText ipET = view.findViewById(R.id.ipET);
            EditText vn_portET = view.findViewById(R.id.vn_portET);//端口输入
            TextView txt_ip = view.findViewById(R.id.vn_txt_ip);
            EditText yuMingET = view.findViewById(R.id.vn_ymET);


            SaveLoginIPBean saveLoginIPBean=new SaveLoginIPBean();

            if (v2RB.isChecked()) {
                saveLoginIPBean.setType("v2");

                AppPreferenceSetting.setSelectIP("v2");

                AppPreferenceSetting.setLastServerIP(AppConstant.DEFAULT_IP);
                AppPreferenceSetting.setLastServerPort(AppConstant.DEFAULT_PORT);

                ApiConstants.NETEAST_HOST = "http://" + AppPreferenceSetting.getLastServerIP() + ":" + AppPreferenceSetting.getLastServerPort() + "/";
            }else {

                if (txt_ip.getText().toString().trim().equals(getResources().getString(R.string.str_ip))) {
                    String ip = ipET.getText(LoginActivity.this);
                    String port = vn_portET.getText().toString();

                    saveLoginIPBean.setType("v4IP");
                    saveLoginIPBean.setV4Ip(ip.trim());
                    saveLoginIPBean.setV4Port(port.trim());

                    AppPreferenceSetting.setSelectIP("v4");
                    AppPreferenceSetting.setLastServerIP(ip.trim());
                    AppPreferenceSetting.setLastServerPort(port.trim());

                    ApiConstants.NETEAST_HOST = "http://" + AppPreferenceSetting.getLastServerIP() + ":" + AppPreferenceSetting.getLastServerPort() + "/";

                } else {
                    String yuming = yuMingET.getText().toString().trim();

                    saveLoginIPBean.setType("v4Ym");
                    if (yuming.contains("http://") || yuming.contains("https://")) {
                        saveLoginIPBean.setV4YuMing(yuming);
                    } else {
                        saveLoginIPBean.setV4YuMing("http://" +yuming);
                    }

                    AppPreferenceSetting.setSelectIP("v4_ym");
                    AppPreferenceSetting.setLastServerDomainName(yuming);

                    if (yuming.contains("http://") || yuming.contains("https://")) {
                        ApiConstants.NETEAST_HOST = AppPreferenceSetting.getLastServerDomainName();
                    } else {
                        ApiConstants.NETEAST_HOST = "http://" + AppPreferenceSetting.getLastServerDomainName();
                    }
                }
            }

            SPDefaultHelper.saveString("saveloginip", new Gson().toJson(saveLoginIPBean));
            materialDialog.dismiss();
            LoginUtil.isConnet=1;
        }
         mPresenter.getImgVaild();
    }

    @OnClick({R.id.iv_imagecode, R.id.tv_register, R.id.tv_forget_pwd, R.id.bt_login, R.id.fuwuqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_imagecode:
                mPresenter.getImgVaild();
                break;
            case R.id.tv_register:
                ForgetPwdActivity.goToPage(this, ForgetPwdActivity.TYPE_REGISTER);
                break;
            case R.id.tv_forget_pwd:
                ForgetPwdActivity.goToPage(this, ForgetPwdActivity.TYPE_FORGET);
                break;
            case R.id.bt_login:
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    ToastUitl.showShort("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText().toString())) {
                    ToastUitl.showShort("请输入密码");
                    return;
                }
                if (mVerificationBean != null&&mVerificationBean.isIsVerification()==false) {
                    mPresenter.loginRequest(etName.getText().toString(), etPwd.getText().toString(), "", "");
                } else if (mVerificationBean != null&&mVerificationBean.isIsVerification()==true){
                    mPresenter.loginRequest(etName.getText().toString(), etPwd.getText().toString(),
                            mVerificationBean.getVerificationId(), etPicYzm.getText().toString());
                }else{
                    ToastUitl.showLong("图片验证码接口获取失败，请联系管理员。");
                }
                break;
            case R.id.fuwuqi:
                showChangeServerDialog();
                break;
        }
    }

    @Override
    public void sure() {
        ForgetPwdActivity.goToPage(this, ForgetPwdActivity.TYPE_REGISTER);
    }

    private void reloadApp() {
        Intent mStartActivity = new Intent(this, SplashActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

    MaterialDialog materialDialog;
    private void showChangeServerDialog() {

        materialDialog = new MaterialDialog.Builder(this).title("服务器地址及端口")
                .widgetColorRes(R.color.blue_color1)// 输入框光
                // 标的颜色
                // 前2个一个是hint一个是预输入的文字
                .customView(R.layout.view_server_dialog, false).positiveText("确认").positiveColorRes(R.color.blue_color1)
                .negativeText("取消").negativeColorRes(R.color.text_grey_color)
                .autoDismiss(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            View view = dialog.getCustomView();
                            RadioButton v2RB = view.findViewById(R.id.rbV2);
                            RadioButton v4RB = view.findViewById(R.id.rbV4);
                            IPEditText ipET = view.findViewById(R.id.ipET);
                            EditText vn_portET = view.findViewById(R.id.vn_portET);//端口输入
                            TextView txt_ip = view.findViewById(R.id.vn_txt_ip);
                            EditText yuMingET = view.findViewById(R.id.vn_ymET);



                            if (v2RB.isChecked()) {
                                ApiConstants.NETEAST_HOST = "http://" + AppConstant.DEFAULT_IP+ ":" + AppConstant.DEFAULT_PORT + "/";
                                ApiConstants.HOST_CHANGE = true;
                                LoginUtil.isConnet=0;


                                AppPreferenceSetting.setSelectIP("v2");
                                AppPreferenceSetting.setLastServerIP(AppConstant.DEFAULT_IP);
                                AppPreferenceSetting.setLastServerPort(AppConstant.DEFAULT_PORT);

                                 String saveloginip=SPDefaultHelper.getString("saveloginip");
                                 if(saveloginip==null||saveloginip.equals("")){
                                     SaveLoginIPBean saveLoginIPBean=new SaveLoginIPBean();
                                     saveLoginIPBean.setType("v2");
                                     SPDefaultHelper.saveString("saveloginip", new Gson().toJson(saveLoginIPBean));
                                 }else{
                                     SaveLoginIPBean msaveloginip=new Gson().fromJson(saveloginip, SaveLoginIPBean.class);
                                     msaveloginip.setType("v2");
                                     msaveloginip.setV4Ip(msaveloginip.getV4Ip());
                                     msaveloginip.setV4Port(msaveloginip.getV4Port());
                                     msaveloginip.setV4YuMing(msaveloginip.getV4YuMing());
                                     SPDefaultHelper.saveString("saveloginip", new Gson().toJson(msaveloginip));
                                 }
                                materialDialog.dismiss();
                                LoginUtil.isConnet=1;
                                mPresenter.getImgVaild();
                            }else {
                                if (txt_ip.getText().toString().trim().equals(getResources().getString(R.string.str_ip))) {
                                    String ip = ipET.getText(LoginActivity.this);
                                    String port = vn_portET.getText().toString();
                                    if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(port)) {
                                        ToastUtil.showToast("请输入IP和端口");
                                        return;
                                    }
                                    if(DomainCheck.isNetPort(Integer.valueOf(port))!=true){
                                        ToastUtil.showToast("端口输入不正确");
                                        return;
                                    }

                                    ApiConstants.NETEAST_HOST = "http://" + ip.trim()+ ":" + port.trim() + "/";

                                } else {
                                    String yuming = yuMingET.getText().toString().trim();
                                    if (TextUtils.isEmpty(yuming)) {
                                        ToastUtil.showToast("请输入域名");
                                        return;
                                    }
                                    if(DomainCheck.domainCheckLegal(yuming)!=true){
                                        ToastUtil.showToast("请输入合法的域名");
                                        return;
                                    }
                                    if (yuming.contains("http://") || yuming.contains("https://")) {
                                        ApiConstants.NETEAST_HOST = yuming;
                                    } else {
                                        ApiConstants.NETEAST_HOST = "http://" +yuming;
                                    }
                                }
                                ApiConstants.HOST_CHANGE = true;
                                LoginUtil.isConnet=0;
                                startProgressDialog();
                                mPresenter.getPostServerBeanPresenter();
                            }

                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();

        materialDialog.show();
        View view = materialDialog.getCustomView();
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton rbV2 = view.findViewById(R.id.rbV2);
        RadioButton rbV4 = view.findViewById(R.id.rbV4);
        LinearLayout lin_V0 = view.findViewById(R.id.lin_V0);//公网
        TextView v0_ipET = view.findViewById(R.id.v0_ipET);//公网ip
        TextView v0_portET = view.findViewById(R.id.v0_portET);//公网端口

        LinearLayout lin_Vn = view.findViewById(R.id.lin_Vn);//专网
        LinearLayout lin_ipall = view.findViewById(R.id.lin_ipall);//专网ip和端口
        RelativeLayout vn_rel_dk = view.findViewById(R.id.vn_rel_dk);//端口布局
/*        ImageView img_look = view.findViewById(R.id.img_look);//ip是否显示
        ImageView img_look_port = view.findViewById(R.id.img_look_port);//端口是否显示*/
        IPEditText ipET = view.findViewById(R.id.ipET);//ip输入
        EditText vn_portET = view.findViewById(R.id.vn_portET);//端口输入
        EditText vn_ymET = view.findViewById(R.id.vn_ymET);//域名输入
        TextView vn_txt_ip = view.findViewById(R.id.vn_txt_ip);//ip和域名切换

       vn_txt_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                View popupWindow_view = LinearLayout.inflate(LoginActivity.this, R.layout.pop_login_ip, null);
                PopupWindow popupWindow = new PopupWindow(popupWindow_view, DisplayUtil.dip2px(100), DisplayUtil.dip2px(80), true);
                TextView tv=popupWindow_view.findViewById(R.id.tv);
                TextView tv_1=popupWindow_view.findViewById(R.id.tv_1);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vn_txt_ip.setText(getResources().getString(R.string.str_ip));
                        vn_rel_dk.setVisibility(View.VISIBLE);
                      /*  img_look.setVisibility(View.VISIBLE);*/
                        ipET.setVisibility(View.VISIBLE);
                        vn_ymET.setVisibility(View.GONE);
                        popupWindow.dismiss();
                        newMoren(R.id.rbV4,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
                    }
                });
                tv_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vn_txt_ip.setText(getResources().getString(R.string.str_ym));
                        vn_rel_dk.setVisibility(View.INVISIBLE);
                      /*  img_look.setVisibility(View.GONE);*/
                        ipET.setVisibility(View.GONE);
                        vn_ymET.setVisibility(View.VISIBLE);
                        popupWindow.dismiss();
                        newMoren(R.id.rbV4,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
                    }
                });
                //点击外部消失
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                //软键盘不会挡着popupwindow
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                popupWindow.showAsDropDown(vn_txt_ip,-30, 0);
            }
        });


//        if (AppPreferenceSetting.getSelectIP().equals("v2")) {
//            rbV2.setChecked(true);
//            newMoren(R.id.rbV2,lin_V0,lin_Vn,v0_ipET,v0_portET,ipET,vn_portET,vn_ymET,vn_txt_ip,vn_rel_dk,img_look,img_look_port);
//        } else {
//            rbV4.setChecked(true);
//            newMoren(R.id.rbV4,lin_V0,lin_Vn,v0_ipET,v0_portET,ipET,vn_portET,vn_ymET,vn_txt_ip,vn_rel_dk,img_look,img_look_port);
//        }
        String saveloginip=SPDefaultHelper.getString("saveloginip");
        if(saveloginip==null||saveloginip.equals("")){
            rbV2.setChecked(true);
            newMoren(R.id.rbV2,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
        }else{
            SaveLoginIPBean msaveloginip=new Gson().fromJson(saveloginip, SaveLoginIPBean.class);
            if(msaveloginip.getType().equals("")||msaveloginip.getType().equals("v2")){
                rbV2.setChecked(true);
                newMoren(R.id.rbV2,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
            }else{
                rbV4.setChecked(true);
                if(msaveloginip.getType().equals("v4IP")){
                    vn_txt_ip.setText(getResources().getString(R.string.str_ip));
                }else{
                    vn_txt_ip.setText(getResources().getString(R.string.str_ym));
                }
                newMoren(R.id.rbV4,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
            }
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                newMoren(checkedId,lin_V0,lin_Vn,v0_ipET,v0_portET,ipET,vn_portET,vn_ymET,vn_txt_ip,vn_rel_dk,img_look,img_look_port);
                newMoren(checkedId,lin_V0,lin_Vn,v0_ipET,v0_portET,vn_txt_ip,vn_rel_dk,ipET,vn_portET,vn_ymET);
            }
        });

    }
    String strportETShow="",smallstrportETShow="";

   /* public void newMoren(int id,LinearLayout V0,LinearLayout Vn,TextView V0Ip,TextView V0DK,IPEditText VnIp,EditText VnPort,EditText VnYM,TextView ipOrYM,RelativeLayout VnRelDk,ImageView VnIpLook,ImageView img_look_port){
        if(id==R.id.rbV2) {//V0
            V0.setVisibility(View.VISIBLE);
            Vn.setVisibility(View.GONE);
            V0Ip.setText(OtherUtil.setIp(AppConstant.DEFAULT_IP));
            V0DK.setText(OtherUtil.setIp(AppConstant.DEFAULT_PORT));
        }else{
            V0.setVisibility(View.GONE);
            Vn.setVisibility(View.VISIBLE);

            if(AppPreferenceSetting.getSelectIP().equals("v4_ym")){
                ipOrYM.setText(getResources().getString(R.string.str_ym));
            }else{
                ipOrYM.setText(getResources().getString(R.string.str_ip));
            }
            if(ipOrYM.getText().toString().trim().equals(getResources().getString(R.string.str_ip))) {
                VnRelDk.setVisibility(View.VISIBLE);
                VnIpLook.setVisibility(View.GONE);
                img_look_port.setVisibility(View.GONE);
                VnIp.setVisibility(View.VISIBLE);
                VnYM.setVisibility(View.GONE);


                if (AppPreferenceSetting.getSelectIP().equals("v4")) {
                    VnIp.setText(AppPreferenceSetting.getLastServerIP(),true,2);

                    strportETShow=AppPreferenceSetting.getLastServerPort();
                    smallstrportETShow= OtherUtil.setIp(AppPreferenceSetting.getLastServerPort());
                    VnPort.setText(strportETShow);
                } else{
                    VnIp.setText(AppConstant.DEFAULT_IP_ZY,false,2);

                    strportETShow=AppConstant.DEFAULT_PORT_ZY;
                    smallstrportETShow= OtherUtil.setIp(AppConstant.DEFAULT_PORT_ZY);
                    VnPort.setText(strportETShow);
                }
            }else{
                VnRelDk.setVisibility(View.INVISIBLE);
                VnIpLook.setVisibility(View.GONE);
                VnIp.setVisibility(View.GONE);
                VnYM.setVisibility(View.VISIBLE);
                if(AppPreferenceSetting.getSelectIP().equals("v4_ym")){
                    VnYM.setText(AppPreferenceSetting.getLastServerDomainName());
                }else{
                    VnYM.setText("");
                }
            }
        }
    }
*/
   public void newMoren(int id ,LinearLayout lin_V0,LinearLayout lin_Vn,TextView v0_ipET,TextView v0_portET, TextView vn_txt_ip, RelativeLayout vn_rel_dk, IPEditText ipET, EditText vn_portET,EditText vn_ymET ){

       String saveloginip=SPDefaultHelper.getString("saveloginip");
           if(id==R.id.rbV2){
               lin_V0.setVisibility(View.VISIBLE);
               lin_Vn.setVisibility(View.GONE);
               v0_ipET.setText(OtherUtil.setIp(AppConstant.DEFAULT_IP));
               v0_portET.setText(OtherUtil.setIp(AppConstant.DEFAULT_PORT));
           }else{

               lin_V0.setVisibility(View.GONE);
               lin_Vn.setVisibility(View.VISIBLE);

               if(vn_txt_ip.getText().toString().trim().equals(getResources().getString(R.string.str_ip))){
                   vn_txt_ip.setText(getResources().getString(R.string.str_ip));
                   vn_rel_dk.setVisibility(View.VISIBLE);
                   ipET.setVisibility(View.VISIBLE);
                   vn_ymET.setVisibility(View.GONE);


                   if(saveloginip==null||saveloginip.equals("")){
                       ipET.setText("",true,2);
                       vn_portET.setText("");
                   }else{
                       SaveLoginIPBean msaveloginip=new Gson().fromJson(saveloginip, SaveLoginIPBean.class);
                       ipET.setText(msaveloginip.getV4Ip(),true,2);
                       vn_portET.setText(msaveloginip.getV4Port());
                   }

               }else if(vn_txt_ip.getText().toString().trim().equals(getResources().getString(R.string.str_ym))){
                   vn_txt_ip.setText(getResources().getString(R.string.str_ym));
                   vn_rel_dk.setVisibility(View.INVISIBLE);
                   ipET.setVisibility(View.GONE);
                   vn_ymET.setVisibility(View.VISIBLE);
                   if(saveloginip==null||saveloginip.equals("")){
                       vn_ymET.setText("");
                   }else{
                       SaveLoginIPBean msaveloginip=new Gson().fromJson(saveloginip, SaveLoginIPBean.class);
                       vn_ymET.setText(msaveloginip.getV4YuMing());
                   }

               }
           }
       }
}
