package com.ttce.vehiclemanage.ui.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppApplication;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.ui.mine.constract.UpdatePwdConstract;
import com.ttce.vehiclemanage.ui.mine.model.UpdatePwdModel;
import com.ttce.vehiclemanage.ui.mine.presenter.UpdatePwdPresenter;
import com.ttce.vehiclemanage.ui.usermanage.activity.LoginActivity;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/19.
 */

public class UpdatePwdActivity extends BaseActivity<UpdatePwdPresenter, UpdatePwdModel> implements UpdatePwdConstract.View {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.et_old_pwd)
    EditText etOldPwd;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.et_sure_pwd)
    EditText etSurePwd;

    private String OldPassword;
    private String NewPassword;
    private String ConfirmNewPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("修改密码");
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
    public void isUpdate(String message) {
        ToastUitl.showShort("修改成功");
        Intent intent = new Intent(AppApplication.getAppContext(), LoginActivity.class);
        intent.putExtra("isNetWork","yes");//根据这个字段判断是正常进入登陆界面还是异常进入登陆界面（no是异常。）
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        AppApplication.getAppContext().startActivity(intent);
        SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_REMMBRE, false);
        SPDefaultHelper.saveString(SPDefaultHelper.USER_ID, "");
        SPDefaultHelper.saveString(SPDefaultHelper.USER_PWD, "");
        UserManager.logout();
        finish();
    }

    @OnClick({R.id.title_bar_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_next:
                OldPassword = etOldPwd.getText().toString();
                NewPassword = etNewPwd.getText().toString();
                ConfirmNewPassword = etSurePwd.getText().toString();
                if (TextUtils.isEmpty(OldPassword)) {
                    ToastUitl.showShort("请输入旧密码");
                    return;
                }
                if (TextUtils.isEmpty(NewPassword)) {
                    ToastUitl.showShort("请输入新密码");
                    return;
                }
                if (TextUtils.isEmpty(ConfirmNewPassword)) {
                    ToastUitl.showShort("请输入确认的新密码");
                    return;
                }
                if (!ConfirmNewPassword.equals(NewPassword)) {
                    ToastUitl.showShort("两次输入的密码不一致");
                    return;
                }
                mPresenter.updatePwd(OldPassword, NewPassword, ConfirmNewPassword);
                break;
        }
    }
}
