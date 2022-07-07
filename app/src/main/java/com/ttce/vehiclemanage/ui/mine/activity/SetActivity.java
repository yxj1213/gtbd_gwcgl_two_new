package com.ttce.vehiclemanage.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.AppUpdateBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CloseAccountStateBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.usermanage.activity.LoginActivity;
import com.ttce.vehiclemanage.ui.usermanage.contract.MainContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.ui.usermanage.model.MainModel;
import com.ttce.vehiclemanage.ui.usermanage.presenter.MainPresenter;
import com.ttce.vehiclemanage.utils.FileUtil;
import com.ttce.vehiclemanage.utils.SdCardUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;


import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.hugeterry.updatefun.UpdateFunGO;

/**
 * Created by hk on 2019/6/19.
 */

public class SetActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, OnDownloadListener, OnButtonClickListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;

    @Bind(R.id.tv_logout)
    TextView tv_logout;
    @Bind(R.id.rl_zxzh)
    RelativeLayout rlZxzh;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        UpdateFunGO.init(this);
        titleBarTitle.setText("设置");

        mPresenter.getCloseAccountState();

    }
    @OnClick({R.id.rl_update_pwd, R.id.rl_zxzh,R.id.title_bar_back, R.id.rl_xxsz, R.id.rl_qkhc, R.id.tv_tcdl,R.id.rl_gywm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_gywm:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.rl_update_pwd:
                startActivity(UpdatePwdActivity.class);
                break;
            case R.id.rl_zxzh:
                getlogout();
                break;
            case R.id.tv_tcdl:
                mPresenter.logout();
                break;
            case R.id.title_bar_back:
                finish();
                break;
            //消息设置
            case R.id.rl_xxsz:
                startActivity(MessageSetActivity.class);
                break;
            //清空缓存
            case R.id.rl_qkhc:
                FileUtil.delFolder(SdCardUtil.getSavedDir(AppConstant.FILE_APP_ROOT_DIR));
                FileUtil.delAllFile(SetActivity.this.getExternalCacheDir().toString());
                ToastUtil.showToast("清理成功");
                break;

        }
    }
    @Override
    public void logout() {
        if (SPDefaultHelper.getBoolean(SPDefaultHelper.USER_REMMBRE)) {

        } else {
            SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, false);
            SPDefaultHelper.saveString(SPDefaultHelper.USER_ID, "");
            SPDefaultHelper.saveString(SPDefaultHelper.USER_PWD, "");
        }
        UserManager.logout();
        Intent intent = new Intent(AppApplication.getAppContext(), LoginActivity.class);
        intent.putExtra("isNetWork","yes");//根据这个字段判断是正常进入登陆界面还是异常进入登陆界面（no是异常。）
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        AppApplication.getAppContext().startActivity(intent);
        startActivity(LoginActivity.class);
    }
   public void getlogout(){
        View view= LinearLayout.inflate(this,R.layout.view_audio_dialog,null);
       EditText etReason = view.findViewById(R.id.et_reason);
       etReason.setHint("请输入注销原因");
       MaterialDialog materialDialog = new MaterialDialog.Builder(this).title("注销原因")
               .widgetColorRes(R.color.blue_color1)// 输入框光标的颜色
               .customView(view, false).positiveText("提交").positiveColorRes(R.color.blue_color1)
               .negativeText("取消").negativeColorRes(R.color.text_grey_color)
               .onPositive(new MaterialDialog.SingleButtonCallback() {
                   @Override
                   public void onClick(MaterialDialog dialog, DialogAction which) {
                       if (which == DialogAction.POSITIVE) {
                           View view = dialog.getCustomView();
                           EditText etReason = view.findViewById(R.id.et_reason);

                           String reason = etReason.getText().toString();
                           if (TextUtils.isEmpty(reason)) {
                               ToastUtil.showToast("请输入注销原因");
                           }else{
                               submitLogOut(reason);
                           }

                       }
                   }
               }).build();

       materialDialog.show();
   }
   public void submitLogOut(String reason){
       MaterialDialog dialog = new MaterialDialog.Builder(this).theme(Theme.LIGHT).
               title("提示").content("注销账号后，您的所有数据将会清空，请您谨慎使用注销功能。").negativeColorRes(R.color.blue_color1)
               .negativeText("取消")
               .positiveText("确认").onPositive(new MaterialDialog
                       .SingleButtonCallback() {
                   @Override
                   public void onClick(@NonNull MaterialDialog dialog, @NonNull
                           DialogAction which) {
                       mPresenter.getAppLogout(reason);
                   }
               })
               .build();
       dialog.setCancelable(true);
       dialog.setCanceledOnTouchOutside(false);
       dialog.show();
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

    }

    @Override
    public void returnAppLogout(Boolean isLogout) {
            if(isLogout==true){
                tv_logout.setText("注销审核中。");
                tv_logout.setTextColor(getResources().getColor(R.color.tv_right_gray));
                rlZxzh.setEnabled(false);
                rlZxzh.setClickable(false);
            }
    }

    @Override
    public void returnCloseAccountState(CloseAccountStateBean closeAccountStateBean) {
            if(closeAccountStateBean.isIsApply()==true&&closeAccountStateBean.getCloseStatus()!=30){
                tv_logout.setText("注销审核中。");
                tv_logout.setTextColor(getResources().getColor(R.color.tv_right_gray));
                rlZxzh.setEnabled(false);
                rlZxzh.setClickable(false);
            }else{
                rlZxzh.setEnabled(true);
                rlZxzh.setClickable(true);
            }
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
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
