package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EquipmentDetailBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.map.contract.EquipmentDetailContract;
import com.ttce.vehiclemanage.ui.map.model.EquipmentDetailModel;
import com.ttce.vehiclemanage.ui.map.presenter.EquipmentDetailPresenter;
import com.ttce.vehiclemanage.utils.BDMapUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设备详情
 * Created by hk on 2019/6/25.
 */

public class EquipmentDetailActivity extends BaseActivity<EquipmentDetailPresenter, EquipmentDetailModel> implements EquipmentDetailContract.View, OnGetGeoCoderResultListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_cph)
    TextView tvCph;
    @Bind(R.id.tv_sbh)
    TextView tvSbh;
    @Bind(R.id.tv_dyz)
    TextView tvDyz;
    @Bind(R.id.tv_dl)
    TextView tvDl;
    @Bind(R.id.tv_yl)
    TextView tvYl;
    @Bind(R.id.tv_wd_loc)
    TextView tvWdLoc;
    @Bind(R.id.tv_zt)
    TextView tvZt;
    @Bind(R.id.tv_azwz)
    TextView tvAzwz;
    @Bind(R.id.tv_dwsj)
    TextView tvDwsj;
    @Bind(R.id.tv_txsj)
    TextView tvTxsj;
    @Bind(R.id.tv_ss)
    TextView tvSs;
    @Bind(R.id.tv_jd)
    TextView tvJd;
    @Bind(R.id.tv_wd)
    TextView tvWd;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_dqsj)
    TextView tvDqsj;
    @Bind(R.id.tv_bz)
    TextView tvBz;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_dept)
    TextView tvDept;
    private String DeviceId;


    @Bind(R.id.tv_yl_sy)
    TextView tvYlSy;

    // 逆地理解析
    private GeoCoder mCoder;

    public static void goToPage(Activity activity, String deviceId) {
        Intent intent = new Intent(activity, EquipmentDetailActivity.class);
        intent.putExtra("DeviceId", deviceId);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_equipment_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    public void initView() {
        titleBarTitle.setText("设备详情");
        DeviceId = getIntent().getStringExtra("DeviceId");
        mPresenter.getDatail(DeviceId);
        initGeoCoder();

    }

    public void initGeoCoder() {
        mCoder = GeoCoder.newInstance();
        mCoder.setOnGetGeoCodeResultListener(this);
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
    public void returnDatail(EquipmentDetailBean equipmentDetailBean) {
        tvCompany.setText(equipmentDetailBean.getCompanyName());
        tvDept.setText(equipmentDetailBean.getDepartMentName());
        tvCph.setText(equipmentDetailBean.getPlateNumber());
        tvSbh.setText(String.valueOf(equipmentDetailBean.getDevieNumber()));
//        tvDyz.setText(equipmentDetailBean.getVoltage());
//        tvDl.setText(equipmentDetailBean.getElectric());
        if (equipmentDetailBean.getIsOilSensor() == 0) {
            tvYl.setText(equipmentDetailBean.getIsOilSensorFormat());
            tvYlSy.setText(equipmentDetailBean.getIsOilSensorFormat());
        } else {
            tvYl.setText(equipmentDetailBean.getOilQuantity());
            tvYlSy.setText(equipmentDetailBean.getOilPercent());
        }
        if (equipmentDetailBean.getIsTemperatureSensor() == 0) {
            tvWd.setText(equipmentDetailBean.getIsTemperatureSensorFormat());
        } else {
            tvWd.setText(equipmentDetailBean.getTemperatureFormat());
        }
        tvZt.setText(equipmentDetailBean.getCarStatusFormat());
        // tvAzwz.setText(equipmentDetailBean.getInstallationPosition());
        tvDwsj.setText(equipmentDetailBean.getGpsTimeFormat());
        tvTxsj.setText(equipmentDetailBean.getServiceTimeFormat());
        tvWdLoc.setText(equipmentDetailBean.getLat() + "");
        tvJd.setText(equipmentDetailBean.getLng() + "");
        tvDqsj.setText(equipmentDetailBean.getFuWuQiXian());
        tvSs.setText(equipmentDetailBean.getSpeed() + "km/h");
        //  tvBz.setText(equipmentDetailBean.getRemark());
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(BDMapUtils.convert(new LatLng(equipmentDetailBean.getLat(), equipmentDetailBean.getLng()))));
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            tvAddress.setText("解析地址错误");
        } else {
            //详细地址
            // 详细地址
            String address="";
            if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
            }
            tvAddress.setText(address);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCoder.destroy();
    }
}
