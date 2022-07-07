package com.ttce.vehiclemanage.ui.mine.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.constract.AddSosConstract;
import com.ttce.vehiclemanage.ui.mine.model.AddSosModel;
import com.ttce.vehiclemanage.ui.mine.presenter.AddSosPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.LocationUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/21.
 */

public class SubmitSosActivity extends BaseActivity<AddSosPresenter, AddSosModel> implements AddSosConstract.View, LocationUtils.LocationListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.et_title)
    EditText etTitle;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_person)
    EditText etPerson;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_command)
    EditText etCommand;

    private String Title;
    private String Content;
    private String LinkMan;
    private String LinkPhone;
    private String Password;
    private String CompanyId;
    private String Address;

    private LocationUtils locationUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_sos;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("提交SOS报警");
        checkPermission();
    }

    @OnClick({R.id.title_bar_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_submit:
                Title = etTitle.getText().toString();
                Content = etContent.getText().toString();
                LinkMan = etPerson.getText().toString();
                LinkPhone = etPhone.getText().toString();
                Password = etCommand.getText().toString();
                CompanyId = UserManager.getLoginBean().getCompanyId();
                if (TextUtils.isEmpty(Title)) {
                    ToastUitl.showShort("请输入标题");
                    return;
                }
                if (TextUtils.isEmpty(Content)) {
                    ToastUitl.showShort("请输入内容");
                    return;
                }
                if (TextUtils.isEmpty(LinkMan)) {
                    ToastUitl.showShort("请输入联系人");
                    return;
                }
                if (TextUtils.isEmpty(LinkPhone)) {
                    ToastUitl.showShort("请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    ToastUitl.showShort("请输入登录口令");
                    return;
                }
                mPresenter.addSos(Title, Content, LinkMan, LinkPhone, Password, CompanyId, Address);
                break;
        }
    }

    private void checkPermission() {
        AndPermission.with(this).runtime().permission(Permission.Group.LOCATION).onGranted(permissions -> {
            // 授权成功
            // 初始化我的位置
            locationUtils = new LocationUtils(this, this);
            locationUtils.startLocation();
        }).onDenied(permissions -> {
            // 授权失败
            showTipDialog("权限获取失败，暂时无法使用此功能。", LoadingTip.LoadStatus.error,R.color.blue_color1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }).start();
    }


    @Override
    protected void onDestroy() {
        if(locationUtils!=null) {
            locationUtils.stopLocation();
        }
        super.onDestroy();
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
    public void isAdd(String message) {
        ToastUitl.showToastWithImg("保存成功", R.drawable.ic_success);
        setResult(RESULT_OK);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    @Override
    public void detecting() {

    }

    @Override
    public void succeed(BDLocation location) {
        if (location == null) {
            return;
        }
        Address = location.getAddrStr();
    }

    @Override
    public void failed() {

    }
}
