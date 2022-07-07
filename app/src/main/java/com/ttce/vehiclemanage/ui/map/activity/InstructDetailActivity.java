package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.ui.map.contract.InstructDetailContract;
import com.ttce.vehiclemanage.ui.map.model.InstructDetailModel;
import com.ttce.vehiclemanage.ui.map.presenter.InstructDetailPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 指令详情
 * Created by hk on 2019/6/24.
 */

public class InstructDetailActivity extends BaseActivity<InstructDetailPresenter, InstructDetailModel> implements InstructDetailContract.View {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_sf)
    TextView tvSf;
    @Bind(R.id.tv_lx)
    TextView tvLx;
    @Bind(R.id.tv_zl)
    TextView tvZl;
    @Bind(R.id.tv_sendtime)
    TextView tvSendtime;
    @Bind(R.id.tv_jg)
    TextView tvJg;
    @Bind(R.id.tv_pt)
    TextView tvPt;
    @Bind(R.id.tv_send_person)
    TextView tvSendPerson;

    private String instructId;

    public static void goToPage(Activity activity, String instructId) {
        Intent intent = new Intent(activity, InstructDetailActivity.class);
        intent.putExtra("instructId", instructId);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_instruct_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        instructId = getIntent().getStringExtra("instructId");
        titleBarTitle.setText("指令详情");
        mPresenter.getDatail(instructId);
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
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
    public void returnDetail(InstructDetailBean instructDetailBean) {
        tvSf.setText(instructDetailBean.getPlateNumber());
        tvLx.setText(String.valueOf(instructDetailBean.getSendType()));
        tvZl.setText(instructDetailBean.getName());
        tvSendtime.setText(instructDetailBean.getSendTimeFormat());
        tvJg.setText(instructDetailBean.getStatusFormat());
        tvPt.setText(instructDetailBean.getOrginName());
        tvSendPerson.setText(instructDetailBean.getUserName());
    }
}
