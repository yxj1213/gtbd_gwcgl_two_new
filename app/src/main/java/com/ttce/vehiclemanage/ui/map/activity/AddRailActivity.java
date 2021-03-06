package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.CarDetailBean;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.locus.activity.DeptActivity;
import com.ttce.vehiclemanage.ui.map.contract.AddFenceContract;
import com.ttce.vehiclemanage.ui.map.model.AddFenceModel;
import com.ttce.vehiclemanage.ui.map.presenter.AddFencePresenter;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.BitmapUtil;
import com.ttce.vehiclemanage.utils.MapUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.VerticalSeekBar;
import com.ttce.vehiclemanage.widget.ViView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ????????????
 * Created by hk on 2019/7/23.
 */

public class AddRailActivity extends BaseActivity<AddFencePresenter, AddFenceModel> implements AddFenceContract.View {
    private final String TAG = "AddRailActivity";
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title_bar_layout)
    RelativeLayout llTitleBar;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;

    @Bind(R.id.fenceDviceIdTV)
    TextView fenceDviceIdTV;
    @Bind(R.id.editFenceNameTV)
    TextView editFenceNameTV;
    @Bind(R.id.addDevice)
    TextView addDevice;
    @Bind(R.id.cb_jin)
    CheckBox cbJin;
    @Bind(R.id.cb_chu)
    CheckBox cbChu;
    @Bind(R.id.tv_confirm)
    TextView confirmTV;

    @Bind(R.id.bmapView)
    MapView mMapView;
    @Bind(R.id.seekBar)
    VerticalSeekBar seekBar;

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_car_info)
    TextView tv_car_info;
    @Bind(R.id.map_center)
    LinearLayout mapCenter;
    private LocationClient mLocationClient;
    BaiduMap mBaiduMap;
    LocationClient mLocClient;
    private Overlay cicleOverlay, centerOverlay, textOverlay;
    boolean isFirstLoc = true;
    boolean isMapChange = false;//?????????????????????????????????????????????,????????????????????????????????????
    boolean isLocationClientStop = false;
    private float lastZoom;

    private String ads;
    private double latitude;// ??????
    private double longitude;// ??????

    private int mRadius = 200;// ??????

    LatLng ll = null;

  //  private MonitorResponseBean monitorResponseBean;
    private List<String> deviceIds = new ArrayList<>();
    private List<CarDetailBean> carDetailBeans = new ArrayList<>();
    private List<Integer> alarmTypes = new ArrayList<>();

    private FenceListBean fenceListBean;// ????????????

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_rail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    MonitorResponseBean currentData;
    @Override
    public void initView() {
        etSearch.setHint("?????????????????????");
      //  monitorResponseBean = (MonitorResponseBean) getIntent().getSerializableExtra("Device");
        String JsonData = getIntent().getStringExtra("currentData");
        currentData = new Gson().fromJson(JsonData,MonitorResponseBean.class);

        fenceListBean = (FenceListBean) getIntent().getSerializableExtra("updateDevice");
        mBaiduMap = mMapView.getMap();

        if (fenceListBean == null) {
            llTitleBar.setVisibility(View.GONE);
            llSearch.setVisibility(View.VISIBLE);
            deviceIds.add(currentData.getDeviceId());
            fenceDviceIdTV.setText(currentData.getCarNumber());
            CarDetailBean bean = new CarDetailBean();
            bean.setCarNub(currentData.getCarNumber());
            bean.setDeviceId(currentData.getDeviceId());
            carDetailBeans.add(bean);
            ll = new LatLng(currentData.getLat(), currentData.getLng());
            ll = BDMapUtils.convert(ll);
        } else {
            titleBarTitle.setText("????????????");
            llTitleBar.setVisibility(View.VISIBLE);
            llSearch.setVisibility(View.GONE);
            deviceIds.addAll(fenceListBean.getDevices());
            carDetailBeans = fenceListBean.getCarDetails();
            fenceDviceIdTV.setText(fenceListBean.getName());
            ll = new LatLng(fenceListBean.getLat(), fenceListBean.getLng());
            ll = BDMapUtils.convert(ll);
            mRadius = fenceListBean.getRadius();
            if (fenceListBean.getAlarmTypes() != null) {
                cbJin.setChecked(Arrays.asList(fenceListBean.getAlarmTypes()).contains(2));
                cbChu.setChecked(Arrays.asList(fenceListBean.getAlarmTypes()).contains(4));
            }
            seekBar.setProgress(mRadius - 200);
        }

        //TODO 2021.11.4
//        drawCircle(ll, mRadius);

        setAds();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ll);
        if (mBaiduMap != null && mapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }
        MapStatusUpdate zoomMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 17);
        if (mBaiduMap != null && zoomMapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(zoomMapStatusUpdate);
        }

        /*
         * //mBaiduMap.setMyLocationEnabled(true); //??????????????? mLocClient = new LocationClient(this);
         *
         * //??????LocationClientOption??????LocationClient???????????? LocationClientOption option = new LocationClientOption();
         * option.setOpenGps(true); // ??????gps option.setCoorType("bd09ll"); // ?????????????????? option.setScanSpan(2000);
         *
         * //??????locationClientOption mLocClient.setLocOption(option);
         *
         * //??????LocationListener????????? MyLocationListener myLocationListener = new MyLocationListener();
         * mLocClient.registerLocationListener(myLocationListener); //???????????????????????? mLocClient.start();
         */

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("seekBar", "--" + (progress + 200));
                mRadius = progress + 200;
                //TODO 2021.11.4
                // ??????
//                drawCircle(ll, mRadius);
                isMapChange=false;
                mapCenter.removeAllViews();
                mapCenter.addView(new ViView(AddRailActivity.this, 200+progress/10,mapCenter,progress + 200));

                // ????????????????????????
                float zoomLevel = 17;
                if (mRadius < 350) {
                    zoomLevel = 17;
                } else if (mRadius >= 350 && mRadius < 650) {
                    zoomLevel = 16;
                } else if (mRadius >= 650 && mRadius < 1250) {
                    zoomLevel = 15;
                } else if (mRadius >= 1250 && mRadius < 2450) {
                    zoomLevel = 14;
                } else if (mRadius >= 2450 && mRadius < 4900) {
                    zoomLevel = 13;
                } else if (mRadius >= 4900 && mRadius < 9800) {
                    zoomLevel = 12;
                } else {
                    zoomLevel = 11;
                }
                MapStatusUpdate zoomMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, zoomLevel);
                if (mBaiduMap != null && zoomMapStatusUpdate != null) {
                    mBaiduMap.animateMapStatus(zoomMapStatusUpdate);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("seekBar", "???????????????");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("seekBar", "???????????????");

                // Toast.makeText(mContext, "?????????" + (seekBar.getProgress()+200) + "???", Toast.LENGTH_SHORT).show();

            }
        });

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChangeStart()");
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
                Log.e("seekBar", "--onMapStatusChangeStart()???" + i);
                 if(i==1){
                    isMapChange=true;
                 }
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChange()???" );
                /* int progress = (int) mapStatus.zoom * 100; */


                /*
                 * if(lastZoom== mapStatus.zoom){ drawCircle(mapStatus.target,mRadius); setAds(); return; }
                 *
                 * float zoom = mapStatus.zoom; if (zoom >= 18) { seekBar.setProgress(0); if(zoom>18){ MapStatusUpdate
                 * zoomMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 18); if (mBaiduMap != null &&
                 * zoomMapStatusUpdate != null) { mBaiduMap.animateMapStatus(zoomMapStatusUpdate); } } }else
                 * if(zoom<=11){ seekBar.setProgress(10000); if(zoom<11){ MapStatusUpdate zoomMapStatusUpdate =
                 * MapStatusUpdateFactory.newLatLngZoom(ll, 11); if (mBaiduMap != null && zoomMapStatusUpdate != null) {
                 * mBaiduMap.animateMapStatus(zoomMapStatusUpdate); } } } else { switch ((int) zoom) { case 17:
                 * seekBar.setProgress(3 * 100); break; case 16: seekBar.setProgress(3 * 200); break; case 15:
                 * seekBar.setProgress(3 * 500); break; case 14: seekBar.setProgress(3 * 1000); break; case 13:
                 * seekBar.setProgress(3 * 2000); break; case 12: seekBar.setProgress(2 * 4000); break; default:
                 * drawCircle(mapStatus.target, mRadius); } }
                 *
                 * lastZoom=zoom;
                 *
                 * // ?????? // drawCircle(mapStatus.target,mRadius); setAds();
                 */
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChangeFinish()???" );
                ll = mapStatus.target;
                //TODO 2021.11.4
                if(isMapChange){
                    mapCenter.removeAllViews();
                    mapCenter.addView(new ViView(AddRailActivity.this, (int) (200+(10*mapStatus.zoom)),mapCenter,mRadius));
                    Log.e("seekBar", "--???????????????" +(int) (mRadius+(10*mapStatus.zoom))+"-------???????????????"+mapStatus.zoom);
                }
//                drawCircle(mapStatus.target, mRadius);
                setAds();
            }
        });
        initLocation();

    }
    private void initLocation() {
        try {
            /**
             * ??????SDK??????????????????????????????
             * false??????????????????????????????SDK????????????
             * true???????????????????????????
             */
            LocationClient.setAgreePrivacy(true);
            //???????????????
            mLocationClient = new LocationClient(this);
//??????LocationClientOption??????LocationClient????????????
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // ??????gps
            option.setCoorType("bd09ll"); // ??????????????????
            option.setScanSpan(1000);

//??????locationClientOption
            mLocationClient.setLocOption(option);

//??????LocationListener?????????
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
//????????????????????????
            mLocationClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private void setAds() {
        // ???????????????
        MapUtil.geoCoderResult(ll, new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    // ????????????????????????
                    return;
                } else {
                    // ????????????
                    if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                        ads = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                    }
                }
            }
        });
    }

    @Override
    public void onFenceAdded(boolean result) {
        if (result) {
            showTipDialog("????????????", LoadingTip.LoadStatus.finish, R.color.blue_color1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setResult(RESULT_OK);
                    finish();
                }
            }, 2000);
        }
    }

    @Override
    public void onFenceUpdate(boolean result) {
        if (result) {
            showTipDialog("????????????", LoadingTip.LoadStatus.finish, R.color.blue_color1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setResult(RESULT_OK);
                    finish();
                }
            }, 2000);
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

    @OnClick({R.id.title_bar_back, R.id.editFenceNameTV, R.id.tv_confirm, R.id.addDevice, R.id.tv_search, R.id.iv_left, R.id.tv_car_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.editFenceNameTV:
                showEditNameDialog();
                break;
            case R.id.tv_confirm:
                alarmTypes.clear();


                if (cbJin.isChecked()) {
                    alarmTypes.add(2);
                }
                if (cbChu.isChecked()) {
                    alarmTypes.add(4);
                }

                if (alarmTypes.size() == 0) {
                    ToastUtil.showToast("?????????????????????");
                    return;
                }
                 if(deviceIds.size()==0){
                     ToastUtil.showToast("????????????????????????");
                     return;
                 }
                if (fenceListBean != null) {// ??????
                    mPresenter.updateFence(fenceListBean.getId(), fenceDviceIdTV.getText().toString(), mRadius, ll.latitude,
                            ll.longitude, ads, new Gson().toJson(alarmTypes), new Gson().toJson(deviceIds));
                } else {// ??????
                    mPresenter.addFence(fenceDviceIdTV.getText().toString(), mRadius, ll.latitude, ll.longitude, ads,
                            new Gson().toJson(alarmTypes), new Gson().toJson(deviceIds));
                }

                break;
            case R.id.addDevice:
                Intent intent = new Intent();
                intent.setClass(this, DeptActivity.class);
                intent.putExtra("isMultSelect", true);
                startActivityForResult(intent, AppConstant.REQUEST_SELECT_DEVICE);
                break;
            case R.id.tv_search:
                String address = etSearch.getText().toString();
                GeoPoint ss = MapUtil.getGeoPointBystr(address);
                if (ss != null) {
                    Double num_latitude = (ss.getLatitudeE6()) / 1e6;
                    Double num_longitude = (ss.getLongitudeE6()) / 1e6;
                    ll = new LatLng(num_latitude, num_longitude);
                    ll = BDMapUtils.convert(ll);
                    //TODO 2021.11.4
//                    drawCircle(ll, mRadius);
                    mapCenter.removeAllViews();
                    mapCenter.addView(new ViView(AddRailActivity.this,200,mapCenter,200));
                    setAds();
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ll);
                    if (mBaiduMap != null && mapStatusUpdate != null) {
                        mBaiduMap.animateMapStatus(mapStatusUpdate);
                    }
                    MapStatusUpdate zoomMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 17);
                    if (mBaiduMap != null && zoomMapStatusUpdate != null) {
                        mBaiduMap.animateMapStatus(zoomMapStatusUpdate);
                    }
                } else {
                    ToastUtil.showToast("?????????????????????");
                }
                break;
            case R.id.tv_car_info:
                showCars();
                break;
        }
    }

    MaterialDialog materialDialog;

    /**
     * ????????????
     */
    private void showCars() {
        if (materialDialog != null && materialDialog.isShowing()) {
            return;
        }
        materialDialog = new MaterialDialog.Builder(mContext).title("????????????")
                .widgetColorRes(R.color.blue_color1)// ????????????????????????
                .customView(R.layout.dialog_car_list_layout, false).positiveText("??????").positiveColorRes(R.color.blue_color1).build();
        LinearLayout linearLayout = (LinearLayout) materialDialog.findViewById(R.id.ll_content);
        if (carDetailBeans != null) {
            for (CarDetailBean carDetail : carDetailBeans) {
                View inflate = View.inflate(this, R.layout.layout_car_item, null);
                TextView textView = inflate.findViewById(R.id.tv_carname);
                textView.setText(carDetail.getCarNub());
                inflate.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linearLayout.removeView(inflate);
                        carDetailBeans.remove(carDetail);
                        deviceIds.remove(carDetail.getDeviceId());
                    }
                });
                linearLayout.addView(inflate);
            }
        }
        materialDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case AppConstant.REQUEST_SELECT_DEVICE:
                if (resultCode == RESULT_OK && data != null) {
                    List<CompanyItemBean> devices = (List<CompanyItemBean>) data.getSerializableExtra("datas");

                    if (devices != null&&devices.size()>0) {
                        for (CompanyItemBean device : devices) {
                            if (!deviceIds.contains(device.getDeviceId())) {
                                deviceIds.add(device.getDeviceId());
                                CarDetailBean bean = new CarDetailBean();
                                bean.setDeviceId(device.getDeviceId());
                                bean.setCarNub(device.getCarNumber());
                                carDetailBeans.add(bean);
                            }
                        /* else {
                            showTipDialog("???????????????", LoadingTip.LoadStatus.error, R.color.blue_color1);
                        }*/
                        }
                    }
                }
                break;
        }
    }

    private void showEditNameDialog() {
        new MaterialDialog.Builder(this).title("????????????").widgetColorRes(R.color.blue_color1)// ????????????????????????
                // ???2????????????hint???????????????????????????
                .input("?????????????????????", deviceIds.size() > 0 ? deviceIds.get(0) : "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (input == null || TextUtils.isEmpty(input.toString())) {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        }
                    }
                }).positiveText("??????").positiveColorRes(R.color.blue_color1).negativeText("??????")
                .negativeColorRes(R.color.text_grey_color).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                fenceDviceIdTV.setText(dialog.getInputEditText().getText().toString());
            }
        }).show();
    }
    public static void goToPage(Activity activity, String currentData,int request) {
        Intent intent = new Intent(activity, AddRailActivity.class);
        intent.putExtra("currentData", currentData);
        activity.startActivityForResult(intent, request);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (location == null || isLocationClientStop)
                return;

              MyLocationData locData = new MyLocationData.Builder() .accuracy(location.getRadius()) .direction(100)
             .latitude(location.getLatitude()) .longitude(location.getLongitude()) .build();
              mBaiduMap.setMyLocationData(locData);


            latitude = location.getLatitude();
            longitude = location.getLongitude();

            if (isFirstLoc) {
                isFirstLoc = false;
                // ???????????????
                ll = new LatLng(latitude, longitude);
                // ????????????????????????????????????
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 13);
                // ???????????????????????????????????????????????? 300 ms
                mBaiduMap.animateMapStatus(u);
                mapCenter.removeAllViews();
                mapCenter.addView(new ViView(AddRailActivity.this,200,mapCenter,200));
//                // ????????????????????????
//                OverlayOptions ooCircle = new CircleOptions().fillColor(0x384d73b3).center(ll)
//                        .stroke(new Stroke(3, 0x784d73b3)).radius(mRadius);
//                mBaiduMap.addOverlay(ooCircle);

            }
        }

    }

    private void drawCircle(LatLng centerPoint, int mRadius) {
        if (cicleOverlay != null) {
            cicleOverlay.remove();
        }

        // ????????????????????????
        OverlayOptions ooCircle = new CircleOptions().fillColor(0x384d73b3).center(centerPoint)
                .stroke(new Stroke(3, 0x784d73b3)).radius(mRadius);
        cicleOverlay = mBaiduMap.addOverlay(ooCircle);

        if (centerOverlay != null) {
            centerOverlay.remove();
        }

        //?????????
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(llp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        ImageView carIV = new ImageView(this);
        carIV.setImageResource(R.mipmap.ico_weilan_car);
        linearLayout.addView(carIV);
        TextView radiusTV = new TextView(this);
        radiusTV.setText(mRadius + "???");
        radiusTV.setTextSize(12);
        radiusTV.setTextColor(getResources().getColor(R.color.common_black));
        radiusTV.setPadding(0, 10, 0, 0);
        linearLayout.addView(radiusTV);

        OverlayOptions centerOption = new MarkerOptions().position(centerPoint).anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapUtil.getViewBitmap(linearLayout))).zIndex(9);
        centerOverlay = mBaiduMap.addOverlay(centerOption);
    }

    @Override
    public void onDestroy() {

        // mLocClient.stop();
        // mBaiduMap.setMyLocationEnabled(false);
        // mMapView = null;
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {

        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
        mMapView.onResume();
    }

}
