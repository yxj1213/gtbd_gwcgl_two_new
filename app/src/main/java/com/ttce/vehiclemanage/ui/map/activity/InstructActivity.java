package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.map.contract.InstructContract;
import com.ttce.vehiclemanage.ui.map.model.InstructModel;
import com.ttce.vehiclemanage.ui.map.presenter.InstructPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 指令
 * Created by hk on 2019/6/20.
 */

public class InstructActivity extends BaseActivity<InstructPresenter, InstructModel> implements InstructContract.View {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_time)
    EditText etTime;
    @Bind(R.id.et_minute)
    EditText etMinute;
    @Bind(R.id.et_command_one)
    EditText etCommandOne;
    @Bind(R.id.tv_send_one)
    TextView tvSendOne;
    @Bind(R.id.rb_open_two)
    RadioButton rbOpenTwo;
    @Bind(R.id.rb_close_two)
    RadioButton rbCloseTwo;
    @Bind(R.id.et_command_two)
    EditText etCommandTwo;
    @Bind(R.id.tv_send_two)
    TextView tvSendTwo;
    @Bind(R.id.rb_open_three)
    RadioButton rbOpenThree;
    @Bind(R.id.rb_close_three)
    RadioButton rbCloseThree;
    @Bind(R.id.et_command_three)
    EditText etCommandThree;
    @Bind(R.id.tv_send_three)
    TextView tvSendThree;
    @Bind(R.id.rb_open_four)
    RadioButton rbOpenFour;
    @Bind(R.id.rb_close_four)
    RadioButton rbCloseFour;
    @Bind(R.id.et_long_four)
    EditText etLongFour;
    @Bind(R.id.et_command_four)
    EditText etCommandFour;
    @Bind(R.id.tv_send_four)
    TextView tvSendFour;
    @Bind(R.id.et_voltage_five)
    EditText etVoltageFive;
    @Bind(R.id.et_command_five)
    EditText etCommandFive;
    @Bind(R.id.tv_send_five)
    TextView tvSendFive;
    @Bind(R.id.rb_open_six)
    RadioButton rbOpenSix;
    @Bind(R.id.rb_close_six)
    RadioButton rbCloseSix;
    @Bind(R.id.et_time_six)
    EditText etTimeSix;
    @Bind(R.id.et_speed_six)
    EditText etSpeedSix;
    @Bind(R.id.et_command_six)
    EditText etCommandSix;
    @Bind(R.id.tv_send_six)
    TextView tvSendSix;
    @Bind(R.id.ll_instruct_one)
    LinearLayout llInstructOne;
    @Bind(R.id.ll_instruct_two)
    LinearLayout llInstructTwo;
    @Bind(R.id.ll_instruct_three)
    LinearLayout llInstructThree;
    @Bind(R.id.ll_instruct_four)
    LinearLayout llInstructFour;
    @Bind(R.id.ll_instruct_five)
    LinearLayout llInstructFive;
    @Bind(R.id.ll_instruct_six)
    LinearLayout llInstructSix;
    @Bind(R.id.tv_instruct_one)
    TextView tvInstructOne;
    @Bind(R.id.tv_instruct_two)
    TextView tvInstructTwo;
    @Bind(R.id.tv_instruct_three)
    TextView tvInstructThree;
    @Bind(R.id.tv_instruct_four)
    TextView tvInstructFour;
    @Bind(R.id.tv_instruct_five)
    TextView tvInstructFive;
    @Bind(R.id.tv_instruct_six)
    TextView tvInstructSix;


    private String InstructId;
    private String DeviceId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_instruct;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        InstructId = getIntent().getStringExtra("InstructId");
        DeviceId = getIntent().getStringExtra("DeviceId");
        titleBarTitle.setText("指令");
        tvInstructOne.setSelected(true);
    }

    public static void goToPage(Activity activity, String InstructId, String DeviceId) {
        Intent intent = new Intent(activity, InstructActivity.class);
        intent.putExtra("InstructId", InstructId);
        intent.putExtra("DeviceId", DeviceId);
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
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
    }

    @OnClick({R.id.title_bar_back, R.id.iv_right, R.id.tv_instruct_one, R.id.tv_instruct_two, R.id.tv_instruct_three, R.id.tv_instruct_four, R.id.tv_instruct_five, R.id.tv_instruct_six, R.id.tv_send_one, R.id.tv_send_two, R.id.tv_send_three, R.id.tv_send_four, R.id.tv_send_five, R.id.tv_send_six})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_right:
                break;
            case R.id.tv_instruct_one:
                tvInstructOne.setSelected(true);
                tvInstructTwo.setSelected(false);
                tvInstructThree.setSelected(false);
                tvInstructFour.setSelected(false);
                tvInstructFive.setSelected(false);
                tvInstructSix.setSelected(false);
                llInstructOne.setVisibility(View.VISIBLE);
                llInstructTwo.setVisibility(View.GONE);
                llInstructThree.setVisibility(View.GONE);
                llInstructFour.setVisibility(View.GONE);
                llInstructFive.setVisibility(View.GONE);
                llInstructSix.setVisibility(View.GONE);
                break;
            case R.id.tv_instruct_two:
                tvInstructOne.setSelected(false);
                tvInstructTwo.setSelected(true);
                tvInstructThree.setSelected(false);
                tvInstructFour.setSelected(false);
                tvInstructFive.setSelected(false);
                tvInstructSix.setSelected(false);
                llInstructOne.setVisibility(View.GONE);
                llInstructTwo.setVisibility(View.VISIBLE);
                llInstructThree.setVisibility(View.GONE);
                llInstructFour.setVisibility(View.GONE);
                llInstructFive.setVisibility(View.GONE);
                llInstructSix.setVisibility(View.GONE);
                break;
            case R.id.tv_instruct_three:
                tvInstructOne.setSelected(false);
                tvInstructTwo.setSelected(false);
                tvInstructThree.setSelected(true);
                tvInstructFour.setSelected(false);
                tvInstructFive.setSelected(false);
                tvInstructSix.setSelected(false);
                llInstructOne.setVisibility(View.GONE);
                llInstructTwo.setVisibility(View.GONE);
                llInstructThree.setVisibility(View.VISIBLE);
                llInstructFour.setVisibility(View.GONE);
                llInstructFive.setVisibility(View.GONE);
                llInstructSix.setVisibility(View.GONE);
                break;
            case R.id.tv_instruct_four:
                tvInstructOne.setSelected(false);
                tvInstructTwo.setSelected(false);
                tvInstructThree.setSelected(false);
                tvInstructFour.setSelected(true);
                tvInstructFive.setSelected(false);
                tvInstructSix.setSelected(false);
                llInstructOne.setVisibility(View.GONE);
                llInstructTwo.setVisibility(View.GONE);
                llInstructThree.setVisibility(View.GONE);
                llInstructFour.setVisibility(View.VISIBLE);
                llInstructFive.setVisibility(View.GONE);
                llInstructSix.setVisibility(View.GONE);
                break;
            case R.id.tv_instruct_five:
                tvInstructOne.setSelected(false);
                tvInstructTwo.setSelected(false);
                tvInstructThree.setSelected(false);
                tvInstructFour.setSelected(false);
                tvInstructFive.setSelected(true);
                tvInstructSix.setSelected(false);
                llInstructOne.setVisibility(View.GONE);
                llInstructTwo.setVisibility(View.GONE);
                llInstructThree.setVisibility(View.GONE);
                llInstructFour.setVisibility(View.GONE);
                llInstructFive.setVisibility(View.VISIBLE);
                llInstructSix.setVisibility(View.GONE);
                break;
            case R.id.tv_instruct_six:
                tvInstructOne.setSelected(false);
                tvInstructTwo.setSelected(false);
                tvInstructThree.setSelected(false);
                tvInstructFour.setSelected(false);
                tvInstructFive.setSelected(false);
                tvInstructSix.setSelected(true);
                llInstructOne.setVisibility(View.GONE);
                llInstructTwo.setVisibility(View.GONE);
                llInstructThree.setVisibility(View.GONE);
                llInstructFour.setVisibility(View.GONE);
                llInstructFive.setVisibility(View.GONE);
                llInstructSix.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_send_one:
                if (TextUtils.isEmpty(etTime.getText().toString())) {
                    ToastUitl.showShort("请填写时间阙值");
                    return;
                }
                int time = Integer.parseInt(etTime.getText().toString());
                if (TextUtils.isEmpty(etMinute.getText().toString())) {
                    ToastUitl.showShort("请填写缓冲值");
                    return;
                }
                int minute = Integer.parseInt(etMinute.getText().toString());
                if (TextUtils.isEmpty(etCommandOne.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl1 = etCommandOne.getText().toString();
                mPresenter.sendPljsRequest(time, minute, kl1, InstructId, DeviceId);
                break;
            case R.id.tv_send_two:
                //0:关闭，1：开启
                int m = 0;
                if (rbOpenTwo.isChecked()) {
                    m = 1;
                } else {
                    m = 0;
                }
                if (TextUtils.isEmpty(etCommandTwo.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl2 = etCommandTwo.getText().toString();
                mPresenter.sendDdbjRequest(m, kl2, InstructId, DeviceId);
                break;
            case R.id.tv_send_three:
                //0:关闭，1：开启
                int n = 0;
                if (rbOpenThree.isChecked()) {
                    n = 1;
                } else {
                    n = 0;
                }
                if (TextUtils.isEmpty(etCommandThree.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl3 = etCommandThree.getText().toString();
                mPresenter.sendAccRequest(n, kl3, InstructId, DeviceId);
                break;
            case R.id.tv_send_four:
                //0:关闭，1：开启
                int x = 0;
                if (rbOpenFour.isChecked()) {
                    x = 1;
                } else {
                    x = 0;
                }
                if (TextUtils.isEmpty(etLongFour.getText().toString())) {
                    ToastUitl.showShort("请填写距离");
                    return;
                }
                int j = Integer.parseInt(etLongFour.getText().toString());
                if (TextUtils.isEmpty(etCommandFour.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl4 = etCommandFour.getText().toString();
                mPresenter.sendWybjRequest(x, j, kl4, InstructId, DeviceId);
                break;
            case R.id.tv_send_five:
                if (TextUtils.isEmpty(etVoltageFive.getText().toString())) {
                    ToastUitl.showShort("请填写距离");
                    return;
                }
                int d = Integer.parseInt(etVoltageFive.getText().toString());
                if (TextUtils.isEmpty(etCommandFive.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl5 = etCommandFive.getText().toString();
                mPresenter.sendDdtxRequest(0, d, kl5, InstructId, DeviceId);
                break;
            case R.id.tv_send_six:
                //0:关闭，1：开启
                int y = 0;
                if (rbOpenSix.isChecked()) {
                    y = 1;
                } else {
                    y = 0;
                }
                if (TextUtils.isEmpty(etTimeSix.getText().toString())) {
                    ToastUitl.showShort("请填写持续时间");
                    return;
                }
                int timesix = Integer.parseInt(etTimeSix.getText().toString());
                if (TextUtils.isEmpty(etSpeedSix.getText().toString())) {
                    ToastUitl.showShort("请填写超速速度");
                    return;
                }
                int sd = Integer.parseInt(etSpeedSix.getText().toString());
                if (TextUtils.isEmpty(etCommandSix.getText().toString())) {
                    ToastUitl.showShort("请填写操作口令");
                    return;
                }
                String kl6 = etCommandSix.getText().toString();
                mPresenter.sendCsbjRequest(y, timesix, sd, kl6, InstructId, DeviceId);
                break;
            default:
                break;
        }
    }

    @Override
    public void returnPljs(String isSend) {
        ToastUtil.showToast(isSend);
    }

    @Override
    public void returnDdbj(String isSend) {
        ToastUtil.showToast(isSend);
    }

    @Override
    public void returnAcc(String isSend) {
        ToastUtil.showToast(isSend);
    }

    @Override
    public void returnWybj(String isSend) {
        ToastUtil.showToast(isSend);
    }

    @Override
    public void returnDdtx(String isSend) {
        ToastUtil.showToast(isSend);
    }

    @Override
    public void returnCsbj(String isSend) {
        ToastUtil.showToast(isSend);
    }

}
