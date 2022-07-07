package com.ttce.vehiclemanage.ui.map.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.LocationUtils;
import com.ttce.vehiclemanage.utils.MapUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.Bind;
import butterknife.OnClick;

public class AlarmDetailActivity extends BaseActivity implements LocationUtils.LocationListener {
    private final String TAG = "AlarmDetailActivity";
    @Bind(R.id.title_bar_back)
    RelativeLayout titleBarBack;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title_bar_right_text)
    TextView titleBarRightText;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.title_bar_layout)
    RelativeLayout titleBarLayout;
    @Bind(R.id.bmapView)
    MapView mMapView;
    @Bind(R.id.adsTV)
    TextView adsTV;
    @Bind(R.id.myPositionIV)
    ImageView myPositionIV;

    private LocationUtils locationUtils;
    private AlarmListBean detailbean;
    private BaiduMap mBaiduMap;
    private LatLng center;
    private Marker myLocMark;
    private boolean isNormal = false;
    private boolean isTraffic = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBarTitle.setText("报警详情");
        mBaiduMap = mMapView.getMap();
        detailbean = (AlarmListBean) getIntent().getSerializableExtra("data");
        center= BDMapUtils.convert(new LatLng(detailbean.getLat(),detailbean.getLng()));
        initMap();
        setAds();
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

    private void initMap(){
        mMapView.showZoomControls(true);

        View view = View.inflate(this, R.layout.infowindow_alarm_detail, null);
        TextView tvNumber = view.findViewById(R.id.tv_number);
        TextView tvStatus = view.findViewById(R.id.tv_state);
        TextView tvTime = view.findViewById(R.id.tv_time);
        tvNumber.setText(detailbean.getPlateNumber());
        tvStatus.setText(detailbean.getName());
        tvTime.setText(detailbean.getAlarmTimeStr());
        InfoWindow mInfoWindow = new InfoWindow(view, center, -40);

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_alarm);
        OverlayOptions markerOptions =
                new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(bitmap).position(center).zIndex(9).infoWindow(mInfoWindow);
        // 添加覆盖物图标
        mBaiduMap.addOverlay(markerOptions);

        MapStatusUpdate zoomMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(center, 18);
        if (mBaiduMap != null && zoomMapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(zoomMapStatusUpdate);
        }
    }

    private void setAds() {
        // 逆地址解析
        MapUtil.geoCoderResult(center, new OnGetGeoCoderResultListener() {
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
                    String address="";
                    if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                        address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                    }
                    adsTV.setText(address);
                }
            }
        });
    }

    @OnClick({ R.id.title_bar_back, R.id.myPositionIV, R.id.tv_replace, R.id.tv_land })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.myPositionIV:
                checkPermission();
                break;
            case R.id.tv_replace:
                if (isNormal) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    isNormal = false;
                } else {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    isNormal = true;
                }
                break;
            case R.id.tv_land:
                if (isTraffic) {
                    //开启交通图
                    mBaiduMap.setTrafficEnabled(true);
                    isTraffic = false;
                } else {
                    //开启交通图
                    mBaiduMap.setTrafficEnabled(false);
                    isTraffic = true;
                }
                break;
        }
    }

    @Override
    public void detecting() {

    }

    @Override
    public void succeed(BDLocation location) {
        if (location == null) {
            return;
        }
        Log.d(TAG, "定位成功:" + location.getLatitude() + "," + location.getLongitude());

        if (myLocMark != null) {
            myLocMark.remove();
        }

        OverlayOptions myLocationOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_point)).zIndex(9).draggable(true);
        myLocMark = (Marker) mBaiduMap.addOverlay(myLocationOptions);

        MapStatusUpdate myLocMapStatusUpdate = MapStatusUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude()));
        if (mBaiduMap != null && myLocMapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(myLocMapStatusUpdate);
        }
    }

    @Override
    public void failed() {

    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        if(locationUtils!=null) {
            locationUtils.stopLocation();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }


}
