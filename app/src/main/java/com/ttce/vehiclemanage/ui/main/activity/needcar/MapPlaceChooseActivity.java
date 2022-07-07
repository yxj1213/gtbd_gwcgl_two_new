package com.ttce.vehiclemanage.ui.main.activity.needcar;

import java.util.ArrayList;
import java.util.List;

import com.aspsine.irecyclerview.IRecyclerView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.PoiItemAdapter;
import com.ttce.vehiclemanage.utils.BDMapUtils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 地图选点demo
 */
public class MapPlaceChooseActivity extends BaseActivity implements BaiduMap.OnMapStatusChangeListener, PoiItemAdapter.MyOnItemClickListener, OnGetGeoCoderResultListener {

    // 默认逆地理编码半径范围
    private static final int sDefaultRGCRadius = 500;
    private BaiduMap mBaiduMap;
    private LatLng mCenter;
    private Handler mHandler;

    private PoiItemAdapter mPoiItemAdapter;

    private GeoCoder mGeoCoder = null;

    private boolean mStatusChangeByItemClick = false;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.mapview)
    MapView mMapView;
    @Bind(R.id.poi_list)
    IRecyclerView mRecyclerView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_map_choose_place_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        titleBarTitle.setText("添加地址");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        requestPermission();

    }
    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (null != mMapView) {
            mMapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mMapView) {
            mMapView.onPause();
        }
    }
    public static String mtag;
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(MessageBean tag) {
        if(tag.getText().equals("途径地")){
            mtag=tag.getValue();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }

        if (null != mGeoCoder) {
            mGeoCoder.destroy();
        }

        if (null != mMapView) {
            mMapView.onDestroy();
        }
    }

    private static final int REQUEST_CODE_CAMERA = 1;
    private LocationClient mLocationClient;
    boolean isLocationClientStop = false;
    boolean isFirstLoc = true;
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
                //授予权限
                initLocation();
            }else{
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE_CAMERA);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initLocation();
                } else {
                    Toast.makeText(this, "定位失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    private void initLocation() {
        if (null == mMapView) {
            return;
        }
        mBaiduMap = mMapView.getMap();
        if (null == mBaiduMap) {
            return;
        }
        //定位初始化
        try {
            /**
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            mLocationClient = new LocationClient(this);
//通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setIsNeedLocationDescribe(true);
            option.setIsNeedAddress(true); // 可选，设置是否需要地址信息，默认不需要
//设置locationClientOption
            mLocationClient.setLocOption(option);
//注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
            mLocationClient.start();
            mHandler = new Handler(this.getMainLooper());

            mBaiduMap.setOnMapStatusChangeListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

            if (isFirstLoc) {
                isFirstLoc = false;
                // 设置初始中心点为北京
                final LatLng center = new LatLng(location.getLatitude(), location.getLongitude());
                mCenter=center;
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(center, 18);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                createCenterMarker();
                reverseRequest(mCenter);
            }
        }

    }
    /**
     * 创建地图中心点marker
     */
    private void createCenterMarker() {
        Projection projection = mBaiduMap.getProjection();
        if (null == projection) {
            return;
        }

        Point point = projection.toScreenLocation(mCenter);
        BitmapDescriptor bitmapDescriptor =
                BitmapDescriptorFactory.fromResource(R.mipmap.water_drop);
        if (null == bitmapDescriptor) {
            return;
        }

        MarkerOptions markerOptions = new MarkerOptions()
                .position(mCenter)
                .icon(bitmapDescriptor)
                .flat(false)
                .fixedScreenPosition(point);
        mBaiduMap.addOverlay(markerOptions);
        bitmapDescriptor.recycle();
    }

    /**
     * 逆地理编码请求
     *
     * @param latLng
     */
    private void reverseRequest(LatLng latLng) {
        if (null == latLng) {
            return;
        }

        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption().location(latLng)
                .newVersion(1) // 建议请求新版数据
                .radius(sDefaultRGCRadius);

        if (null == mGeoCoder) {
            mGeoCoder = GeoCoder.newInstance();
        }

        mGeoCoder.setOnGetGeoCodeResultListener(this);
        mGeoCoder.reverseGeoCode(reverseGeoCodeOption);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(final ReverseGeoCodeResult reverseGeoCodeResult) {
        if (null == reverseGeoCodeResult) {
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                updateUI(reverseGeoCodeResult);
            }
        });
    }

    /**
     * 更新UI
     *
     * @param reverseGeoCodeResult
     */
    private void updateUI(ReverseGeoCodeResult reverseGeoCodeResult) {
        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();

        PoiInfo curAddressPoiInfo = new PoiInfo();
        curAddressPoiInfo.address = reverseGeoCodeResult.getAddress();
        curAddressPoiInfo.location = reverseGeoCodeResult.getLocation();

        if (null == poiInfos) {
            poiInfos = new ArrayList<>(2);
        }

        poiInfos.add(0, curAddressPoiInfo);

        if (null == mPoiItemAdapter) {
            mPoiItemAdapter = new PoiItemAdapter(poiInfos);
            mRecyclerView.setAdapter(mPoiItemAdapter);
            mPoiItemAdapter.setOnItemClickListener(this);
        } else {
            mPoiItemAdapter.updateData(poiInfos);
        }
    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {
    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        LatLng newCenter = mapStatus.target;

        // 如果是点击poi item导致的地图状态更新，则不用做后面的逆地理请求，
        if (mStatusChangeByItemClick) {
            if (!BDMapUtils.isLatlngEqual(mCenter, newCenter)) {
                mCenter = newCenter;
            }
            mStatusChangeByItemClick = false;
            return;
        }

        if (!BDMapUtils.isLatlngEqual(mCenter, newCenter)) {
            mCenter = newCenter;
            reverseRequest(mCenter);
        }
    }

    @Override
    public void onItemClick(int position, PoiInfo poiInfo) {
        if (null == poiInfo || null == poiInfo.getLocation()) {
            return;
        }

        mStatusChangeByItemClick = true;
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(poiInfo.getLocation());
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    @Override
    public void onImgCheckClick(PoiInfo poiInfo) {
        finish();
        EventBus.getDefault().post(poiInfo);
    }
}
