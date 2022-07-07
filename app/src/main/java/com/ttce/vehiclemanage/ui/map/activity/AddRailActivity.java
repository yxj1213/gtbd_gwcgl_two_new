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
 * 添加围栏
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
    boolean isMapChange = false;//用户手势触发导致的地图状态改变,比如双击、拖拽、滑动底图
    boolean isLocationClientStop = false;
    private float lastZoom;

    private String ads;
    private double latitude;// 维度
    private double longitude;// 精度

    private int mRadius = 200;// 半径

    LatLng ll = null;

  //  private MonitorResponseBean monitorResponseBean;
    private List<String> deviceIds = new ArrayList<>();
    private List<CarDetailBean> carDetailBeans = new ArrayList<>();
    private List<Integer> alarmTypes = new ArrayList<>();

    private FenceListBean fenceListBean;// 用于编辑

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
        etSearch.setHint("请输入搜索位置");
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
            titleBarTitle.setText("编辑围栏");
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
         * //mBaiduMap.setMyLocationEnabled(true); //定位初始化 mLocClient = new LocationClient(this);
         *
         * //通过LocationClientOption设置LocationClient相关参数 LocationClientOption option = new LocationClientOption();
         * option.setOpenGps(true); // 打开gps option.setCoorType("bd09ll"); // 设置坐标类型 option.setScanSpan(2000);
         *
         * //设置locationClientOption mLocClient.setLocOption(option);
         *
         * //注册LocationListener监听器 MyLocationListener myLocationListener = new MyLocationListener();
         * mLocClient.registerLocationListener(myLocationListener); //开启地图定位图层 mLocClient.start();
         */

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("seekBar", "--" + (progress + 200));
                mRadius = progress + 200;
                //TODO 2021.11.4
                // 画圆
//                drawCircle(ll, mRadius);
                isMapChange=false;
                mapCenter.removeAllViews();
                mapCenter.addView(new ViView(AddRailActivity.this, 200+progress/10,mapCenter,progress + 200));

                // 动态调整地图缩放
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
                Log.e("seekBar", "开始滑动！");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("seekBar", "停止滑动！");

                // Toast.makeText(mContext, "半径：" + (seekBar.getProgress()+200) + "米", Toast.LENGTH_SHORT).show();

            }
        });

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChangeStart()");
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
                Log.e("seekBar", "--onMapStatusChangeStart()：" + i);
                 if(i==1){
                    isMapChange=true;
                 }
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChange()：" );
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
                 * // 画圆 // drawCircle(mapStatus.target,mRadius); setAds();
                 */
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                Log.e("seekBar", "--onMapStatusChangeFinish()：" );
                ll = mapStatus.target;
                //TODO 2021.11.4
                if(isMapChange){
                    mapCenter.removeAllViews();
                    mapCenter.addView(new ViView(AddRailActivity.this, (int) (200+(10*mapStatus.zoom)),mapCenter,mRadius));
                    Log.e("seekBar", "--圆的半径：" +(int) (mRadius+(10*mapStatus.zoom))+"-------缩放级别："+mapStatus.zoom);
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
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            //定位初始化
            mLocationClient = new LocationClient(this);
//通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);

//设置locationClientOption
            mLocationClient.setLocOption(option);

//注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
            mLocationClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private void setAds() {
        // 逆地址解析
        MapUtil.geoCoderResult(ll, new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有找到检索结果
                    return;
                } else {
                    // 详细地址
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
            showTipDialog("添加成功", LoadingTip.LoadStatus.finish, R.color.blue_color1);
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
            showTipDialog("更新成功", LoadingTip.LoadStatus.finish, R.color.blue_color1);
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
                    ToastUtil.showToast("请选择进出告警");
                    return;
                }
                 if(deviceIds.size()==0){
                     ToastUtil.showToast("车辆信息不能为空");
                     return;
                 }
                if (fenceListBean != null) {// 更新
                    mPresenter.updateFence(fenceListBean.getId(), fenceDviceIdTV.getText().toString(), mRadius, ll.latitude,
                            ll.longitude, ads, new Gson().toJson(alarmTypes), new Gson().toJson(deviceIds));
                } else {// 添加
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
                    ToastUtil.showToast("输入地址不匹配");
                }
                break;
            case R.id.tv_car_info:
                showCars();
                break;
        }
    }

    MaterialDialog materialDialog;

    /**
     * 展示车辆
     */
    private void showCars() {
        if (materialDialog != null && materialDialog.isShowing()) {
            return;
        }
        materialDialog = new MaterialDialog.Builder(mContext).title("车辆列表")
                .widgetColorRes(R.color.blue_color1)// 输入框光标的颜色
                .customView(R.layout.dialog_car_list_layout, false).positiveText("确定").positiveColorRes(R.color.blue_color1).build();
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
                            showTipDialog("设备已存在", LoadingTip.LoadStatus.error, R.color.blue_color1);
                        }*/
                        }
                    }
                }
                break;
        }
    }

    private void showEditNameDialog() {
        new MaterialDialog.Builder(this).title("编辑名称").widgetColorRes(R.color.blue_color1)// 输入框光标的颜色
                // 前2个一个是hint一个是预输入的文字
                .input("请输入围栏名称", deviceIds.size() > 0 ? deviceIds.get(0) : "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (input == null || TextUtils.isEmpty(input.toString())) {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        }
                    }
                }).positiveText("确认").positiveColorRes(R.color.blue_color1).negativeText("取消")
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
                // 定位点坐标
                ll = new LatLng(latitude, longitude);
                // 设置地图中心点和缩放级别
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 13);
                // 以动画方式更新地图状态，动画耗时 300 ms
                mBaiduMap.animateMapStatus(u);
                mapCenter.removeAllViews();
                mapCenter.addView(new ViView(AddRailActivity.this,200,mapCenter,200));
//                // 画圆，主要是这里
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

        // 画圆，主要是这里
        OverlayOptions ooCircle = new CircleOptions().fillColor(0x384d73b3).center(centerPoint)
                .stroke(new Stroke(3, 0x784d73b3)).radius(mRadius);
        cicleOverlay = mBaiduMap.addOverlay(ooCircle);

        if (centerOverlay != null) {
            centerOverlay.remove();
        }

        //中心点
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(llp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        ImageView carIV = new ImageView(this);
        carIV.setImageResource(R.mipmap.ico_weilan_car);
        linearLayout.addView(carIV);
        TextView radiusTV = new TextView(this);
        radiusTV.setText(mRadius + "米");
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
