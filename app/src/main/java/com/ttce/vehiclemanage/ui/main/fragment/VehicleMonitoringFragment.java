package com.ttce.vehiclemanage.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.api.ApiConstants;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;
import com.ttce.vehiclemanage.ui.main.contract.VehicalMonitoringContract;
import com.ttce.vehiclemanage.ui.main.model.VehicleMonitoringModel;
import com.ttce.vehiclemanage.ui.main.popwindow.BottomControlPanel;
import com.ttce.vehiclemanage.ui.main.presenter.VehicleMonitoringPresenter;
import com.ttce.vehiclemanage.ui.search.activity.SearchActivity;
import com.ttce.vehiclemanage.utils.AppPreferenceSetting;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.CarStateFactory;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.utils.clustering.ClusterManager;
import com.ttce.vehiclemanage.widget.tree.Dept;
import com.ttce.vehiclemanage.widget.tree.Node;
import com.ttce.vehiclemanage.widget.tree.NodeHelper;
import com.ttce.vehiclemanage.widget.tree.NodeTreeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ttce.vehiclemanage.utils.DeviceUtils.dip2px;

/**
 * 车辆管理
 * Created by hk on 2019/6/18.
 */

public class VehicleMonitoringFragment extends BaseFragment<VehicleMonitoringPresenter, VehicleMonitoringModel> implements VehicalMonitoringContract.View, BaiduMap.OnMarkerClickListener, BottomControlPanel.ControlPanelListener, NodeTreeAdapter.OnItemClickListener,BaiduMap.OnMapStatusChangeListener ,BaiduMap.OnMapLoadedCallback{

    @Bind(R.id.bmapView)
    MapView mMapView;

    @Bind(R.id.iv_car)
    ImageView iv_car;
    @Bind(R.id.ll_right_panel)
    LinearLayout ll_right_panel;
    @Bind(R.id.tv_car_status)
    TextView tv_car_status;
    @Bind(R.id.tv_all)
    TextView tv_all;
    @Bind(R.id.tv_online)
    TextView tv_online;
    @Bind(R.id.tv_outline)
    TextView tv_outline;
    @Bind(R.id.tv_drive)
    TextView tv_drive;
    @Bind(R.id.tv_task)
    TextView tv_task;
    @Bind(R.id.ll_status)
    LinearLayout ll_status;
    @Bind(R.id.lin_ditu)
    LinearLayout linDitu;
    @Bind(R.id.lin_car)
    LinearLayout linCar;
    @Bind(R.id.lin_car_status)
    LinearLayout linCarStatus;
    @Bind(R.id.id_tree)
    ListView id_tree;
    @Bind(R.id.iv_car_expand)
    ImageView iv_car_expand;

    @Bind(R.id.tv_weidingwei)
    TextView tvWeiDingWei;
    @Bind(R.id.tv_daisu)
    TextView tvDaiSu;
    @Bind(R.id.tv_baojing)
    TextView tvBaoJing;
    @Bind(R.id.tv_tingche)
    TextView tvTingChe;
    @Bind(R.id.tv_xingshi)
    TextView tvXingShi;
    @Bind(R.id.tv_lixian)
    TextView tvLiXian;
    @Bind(R.id.tv_zaixian)
    TextView tvZaiXian;
    @Bind(R.id.tv_ditu_shou)
    TextView tvDituShou;
    @Bind(R.id.tv_refresh)
    ImageView tv_refresh;
    @Bind(R.id.tv_yuan_zaixian)
    TextView tvYuanZaixian;
    @Bind(R.id.iv_car_status_shou)
    ImageView ivCarStatusShou;
    @Bind(R.id.lin_ditu_controller)
    LinearLayout linDituController;
    private BaiduMap mBaiduMap;

    private boolean is_First_locate = true;
    private boolean isOpen = false;
    private boolean isNormal = false;
    private boolean isTraffic = false;
    private boolean isFillData = false;

    private MonitorResponseBean dataBean;

    BottomControlPanel bottomControlPanel;

    private Marker animationMarker;

    private List<Overlay> markerList;
    /**
     * 当前的marker
     */
    private Marker currentMarker;

    private CoordinateConverter converter;
    // 缓存机构数据
    private List<CompanyItemBean> companyBeanList = new ArrayList<>();
    private NodeTreeAdapter mAdapter;
    private LinkedList<Dept> mLinkedList = new LinkedList<>();
    List<Dept> data = new ArrayList<>();


    private LocationClient mLocationClient;

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_vehicle_monitoring;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    public void setShow(boolean show) {
        nowIsShow = show;
        if(show==true){
            requestPermission();
            beginRefreshTimer();
        }else{
            if(sendMsgTimer!=null){
                sendMsgTimer.cancel();
            }
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            if(mMapView!=null) {
                mMapView.setVisibility(View.GONE);
            }
        } else {
            if(mMapView!=null) {
                mMapView.setVisibility(View.VISIBLE);
            }
        }
    }
    MKOfflineMap mOffline;
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.app_main_colors));
        EventBus.getDefault().register(this);

        mOffline = new MKOfflineMap();
// 传入MKOfflineMapListener，离线地图状态发生改变时会触发该回调
        mOffline.init(new MKOfflineMapListener() {
            @Override
            public void onGetOfflineMapState(int i, int i1) {
            }
        });


        mMapView.showZoomControls(true);
        //
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMarkerClickListener(this);

        mBaiduMap.setOnMapStatusChangeListener(this);
        mBaiduMap.setOnMapLoadedCallback(this);

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                ((MainActivity) getActivity()).hideVRMap();
                if (ll_right_panel.isShown()) {
                    iv_car.performClick();
                }
            }

            @Override
            public void onMapPoiClick(MapPoi poi) {

            }
        });
        //创建动画mark
        createAnimMark();


        initData();
    }

    private static final int REQUEST_CODE_CAMERA = 1;
    private LocationManager lm;//【位置管理】
    public void requestPermission() {
        lm = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //未获得权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE_CAMERA);
            } else {
                //授予权限
                initLocation();
            }
        } else {
            Toast.makeText(getActivity(), "系统检测到未开启GPS定位服务", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 1315);
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
                    Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    private void initLocation() {
        //定位初始化
        try {
            /**
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            mLocationClient = new LocationClient(getActivity());

//通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1);
            option.setIsNeedLocationDescribe(true);
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

    private void createAnimMark() {
        //创建OverlayOptions的集合
        OverlayOptions markerOptions = initMarkAnimation();
        animationMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
        animationMarker.setVisible(false);
        animationMarker.setPeriod(10);
    }


    private MarkerOptions initMarkAnimation() {
        ArrayList<BitmapDescriptor> bitmapDescriptorList = new ArrayList<>();
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_01));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_02));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_03));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_04));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_05));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_06));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_07));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_08));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_09));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_10));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_11));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_12));
        bitmapDescriptorList.add(BitmapDescriptorFactory
                .fromResource(R.mipmap.mark_animation_13));
        return new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icons(bitmapDescriptorList)
                .position(new LatLng(0, 0));
    }

    /**
     * 请求数据
     */
    private void initData() {
        mAdapter = new NodeTreeAdapter(getActivity(), id_tree, /*mLinkedList,*/ tv_car_status.getTag() == null ? -1 : Integer.parseInt(tv_car_status.getTag().toString()));
        mAdapter.setOnItemClickListener(this);
        id_tree.setDividerHeight(0);
//        id_tree.setAdapter(mAdapter);//TODO 2021.11.30
//        mPresenter.getVehiclesListRequest();
//        mPresenter.getCompanyListRequest();
//        mPresenter.getIsStaffs();
    }

    /**
     * 开始刷新定时器
     */
    private void beginRefreshTimer() {
        if(sendMsgTimer!=null)
            sendMsgTimer.cancel();
        sendMsgTimer = new Timer();
        sendMsgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mPresenter.getVehiclesListRequest();
                mPresenter.getCompanyListRequest();
            }
        }, 0, 20000);
    }
    private Timer sendMsgTimer;

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        //暂停下载
        MKOLUpdateElement temp = mOffline.getUpdateInfo(cityId);
        if (temp != null && temp.status == MKOLUpdateElement.DOWNLOADING) {
            mOffline.pause(cityId);
        }
        if(sendMsgTimer!=null)
            sendMsgTimer.cancel();
        if (updateMarkTask != null) {
            updateMarkTask.cancel(true);
            updateMarkTask = null;
        }
        if (addMarkTask != null) {
            addMarkTask.cancel(true);
            addMarkTask = null;
        }
    }
    boolean isRefDara=true;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent msg) {
        switch (msg.getMsgWhat()) {
            case AppConstant.CHANGE_COMPANYID:
                mBaiduMap.clear();
                if (markerList != null) {
                    for (Overlay seekBarPolylineOverlay : markerList) {
                        seekBarPolylineOverlay.remove();
                        Marker mMarkerC = (Marker)seekBarPolylineOverlay;
                        mMarkerC.hideInfoWindow();
                    }
                    animationMarker.setVisible(false);
                    animationMarker.hideInfoWindow();
                    markerList.clear();
                    markerList=null;
                }
                currentMarker=null;
                is_First_locate=true;
                dataBean=null;
                if (updateMarkTask != null) {
                    updateMarkTask.cancel(true);
                    updateMarkTask = null;
                }
                if (addMarkTask != null) {
                    addMarkTask.cancel(true);
                    addMarkTask = null;
                }
                createAnimMark();

                isFillData = false;
                ll_right_panel.setVisibility(View.GONE);
                isRefDara=true;
                data.clear();
                mLinkedList.clear();
                companyBeanList.clear();


                id_tree.setAdapter(null);




                tv_all.setText("全部车辆(0)");
                tv_online.setText("在线车辆(0)");
                tv_outline.setText("离线车辆(0)");
                tv_drive.setText("行驶车辆(0)");
                tv_task.setText("任务车辆(0)");

                String str1 = "--<br><font><small>" + "在线" + "</small></font>";
                String str2 = "--<br><font><small>" + "离线" + "</small></font>";
                String str3 = "--<br><font><small>" + "行驶" + "</small></font>";
                tvZaiXian.setText(Html.fromHtml(str1));
                tvXingShi.setText(Html.fromHtml(str3));
                tvLiXian.setText(Html.fromHtml(str2));




                iv_car_expand.setTag("0");
                iv_car_expand.setImageResource(R.mipmap.icon_veh_bottom);

                mPresenter.getVehiclesListRequest();
                mPresenter.getCompanyListRequest();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(nowIsShow==true){
            beginRefreshTimer();
//            mPresenter.getVehiclesListRequest();
//            mPresenter.getCompanyListRequest();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        MKOLUpdateElement temp = mOffline.getUpdateInfo(cityId);
        if (temp != null && temp.status == MKOLUpdateElement.DOWNLOADING) {
            mOffline.pause(cityId);
        }

        if(mLocationClient!=null){
            mLocationClient.stop();
        }
        mBaiduMap.setMyLocationEnabled(false);
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
        if (updateMarkTask != null) {
            updateMarkTask.cancel(true);
            updateMarkTask = null;
        }
        if (addMarkTask != null) {
            addMarkTask.cancel(true);
            addMarkTask = null;
        }
        EventBus.getDefault().unregister(this);
    }

    private void changeTextImg(TextView textView, int resourceId) {
        Drawable drawable = getActivity().getResources().getDrawable(resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     *
     */
    private void controlCarManager() {
        if (ll_status.isShown()) {
            ll_status.setVisibility(View.GONE);
            id_tree.setVisibility(View.VISIBLE);
            changeTextImg(tv_car_status, R.mipmap.arraw_smal_down);
        } else {
            ll_status.setVisibility(View.VISIBLE);
            id_tree.setVisibility(View.GONE);
            changeTextImg(tv_car_status, R.mipmap.arraw_smal_up);
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
            if(mapStatus.zoom<10){
                showJuHeDian(jieKoumonitorResponseBeanList);
                animationMarker.setVisible(false);
                hidePanel();
                linCar.setVisibility(View.GONE);
                if (markerList != null) {
                    //全部隐藏车牌号
                    for (Overlay m : markerList) {
                        Marker mMarkerC = (Marker)m;
                        mMarkerC.setVisible(false);
                        final LatLngBounds visibleBounds = mBaiduMap.getMapStatus().bound;//地图在手机屏幕上的可见范围
                        boolean onScreen = visibleBounds.contains(mMarkerC.getPosition());//是否在屏幕内
                        if(onScreen){
                           mMarkerC.hideInfoWindow();
                       }
                    }
                }
            }else{
                if(mClusterManager!=null&&mClusterManager.getClusterMarkerCollection()!=null){
                    mClusterManager.getClusterMarkerCollection().clear();
                }
                animationMarker.setVisible(true);

                //全部显示车牌号
                if (markerList != null) {
                    for (Overlay m : markerList) {
                        Marker mMarkerC = (Marker)m;
                        mMarkerC.setVisible(true);
                        final LatLngBounds visibleBounds = mBaiduMap.getMapStatus().bound;//地图在手机屏幕上的可见范围
                        boolean onScreen = visibleBounds.contains(mMarkerC.getPosition());//是否在屏幕内
                        if(onScreen){
                            if(mMarkerC.getInfoWindow()!=null){
                                mMarkerC.showInfoWindow(mMarkerC.getInfoWindow());//首次加载是否显示InfoWindow
                            }else{
                                MonitorResponseBean bean = (MonitorResponseBean) mMarkerC.getExtraInfo().getSerializable("bean");
                                //用来构造InfoWindow
                                Button button = new Button(getContext());
                                button.setBackground(null);
                                button.setText(bean.getCarNumbers());
                                int color=CarStateFactory.getCarTextColorByStatus(bean.getCarDisplayColorFormat());
                                button.setTextColor(getContext().getResources().getColor(color));
                                button.setPadding(5,5,5,5);
                                button.setTextSize(12);
                                // 创建InfoWindow
                                InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button),bean.getPosition(), -20, null);
                                mMarkerC.showInfoWindow(mInfoWindow);
                            }
                      }
                    }
                }
            }
    }

    public void showJuHeDian(List<MonitorResponseBean> monitorResponseBeanList) {
        List<MonitorResponseBean> mlist = (List<MonitorResponseBean>) mClusterManager.getAllItems();
            if(mlist!=null){
                mClusterManager.clearItems();
            }
            if(monitorResponseBeanList!=null&&monitorResponseBeanList.size()>0){
                mClusterManager.addItems(monitorResponseBeanList);
                mClusterManager.cluster();
            }
    }
    ClusterManager mClusterManager;
    boolean isDisplayCluster = false;
    private void initCluster() {
        mClusterManager = new ClusterManager<MonitorResponseBean>(getContext(), mBaiduMap);
    }
    @Override
    public void onMapLoaded() {
        isDisplayCluster = true;
        // 添加marker
        initCluster();
        // 设置初始中心点为北京
        MapStatus.Builder mapStatusBuilder = new MapStatus.Builder();
        float zoom = mMapView.getMap().getMapStatus().zoom;
        mapStatusBuilder.zoom(zoom);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatusBuilder.build()));
    }
    Animation animation;
    public void startAnmain(){
        //开始旋转
        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.vehicle_monitor_ref);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
    }

    boolean isSelectTxtCar=false;
    @OnClick({R.id.tv_yuan_zaixian,R.id.iv_car_status_shou,R.id.rel_car_right,R.id.rel_car_left,R.id.tv_ditu_shou,R.id.rl_top, R.id.iv_car, R.id.tv_replace, R.id.tv_land, R.id.tv_jing, R.id.tv_refresh, R.id.iv_left, R.id.tv_car_status, R.id.ll_right_panel, R.id.tv_all, R.id.tv_online, R.id.tv_outline, R.id.tv_drive, R.id.tv_task, R.id.iv_car_expand})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yuan_zaixian:
                tvYuanZaixian.setVisibility(View.GONE);
                linCarStatus.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_car_status_shou:
                tvYuanZaixian.setVisibility(View.VISIBLE);
                linCarStatus.setVisibility(View.GONE);
                break;
            case R.id.tv_ditu_shou:
                if (linDitu.isShown()) {
                    linDituController.setBackgroundResource(0);
                    tvDituShou.setBackgroundResource(R.drawable.alpha30_yuan_bg);
                    //取控件当前的布局参数
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvDituShou.getLayoutParams();
                    //设置宽度值
                    params.width = dip2px(getActivity(), 40);
                    //设置高度值
                    params.height = dip2px(getActivity(), 40);
                    //使设置好的布局参数应用到控件
                    tvDituShou.setLayoutParams(params);

                    linDitu.setVisibility(View.GONE);
                } else {
                    linDituController.setBackgroundResource(R.drawable.alpha30_bg);
                    tvDituShou.setBackgroundResource(0);

                    linDitu.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rel_car_right:
                onNext();
                break;
            case R.id.rel_car_left:
                onPre();
                break;
            case R.id.rl_top:
                break;
            case R.id.ll_right_panel:
                // TODO: 2019/9/20 nothing
                break;
            case R.id.iv_car:
//                isFillData = false;
//                filterCompanyData();
                if(bottomControlPanel!=null){
                    bottomControlPanel.dissmiss();
                }
                if (ll_right_panel.isShown()) {
                    ll_right_panel.setVisibility(View.GONE);
                } else {
                    ll_right_panel.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_car_status:
                iv_car_expand.setTag("0");
                iv_car_expand.setImageResource(R.mipmap.icon_veh_bottom);
                controlCarManager();
                if(isSelectTxtCar==false){
                    setDefaultColor(tv_all);
                }
                break;
            case R.id.tv_all:
                isSelectTxtCar=true;
                refTree(tv_all);
                break;
            case R.id.tv_online:
                isSelectTxtCar=true;
                refTree(tv_online);
                break;
            case R.id.tv_outline:
                isSelectTxtCar=true;
                refTree(tv_outline);
                break;
            case R.id.tv_drive:
                isSelectTxtCar=true;
                refTree(tv_drive);
                break;
            case R.id.tv_task:
                isSelectTxtCar=true;
                refTree(tv_task);
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
            case R.id.tv_jing:
                if (dataBean != null) {
                    ((MainActivity) getActivity()).showVRMap(dataBean.getLng(), dataBean.getLat());
                }
                break;
            case R.id.tv_refresh:
                startAnmain();
                if(animation!=null){
                    tv_refresh.startAnimation(animation);
                }
                mPresenter.getVehiclesListRequest();
                break;
            case R.id.iv_left:
                SearchActivity.go2Page(getActivity(), 1);
                break;
            case R.id.iv_car_expand:
                if (ll_right_panel.isShown()) {
                    String tag = (String) iv_car_expand.getTag();
                    if (tag.equals("0")) {
                        mAdapter.expanAll();
                        iv_car_expand.setTag("1");
                        iv_car_expand.setImageResource(R.mipmap.icon_veh_top);
                    } else {
                        isFillData = false;
                        filterCompanyData();
                        iv_car_expand.setTag("0");
                        iv_car_expand.setImageResource(R.mipmap.icon_veh_bottom);
                    }
                }
                break;
        }
    }
    public void refTree(View view){
        isFillData = false;
        setDefaultColor((TextView) view);
        String str= ((TextView) view).getText().toString();
        if(str.contains("(")){
            str=str.substring(0,str.indexOf("("));
        }
        tv_car_status.setText(str);
        tv_car_status.setTag(view.getTag());
        controlCarManager();
        filterCompanyData();
    }
    /**
     *
     */
    private void setDefaultColor(TextView textView) {
        tv_all.setTextColor(getActivity().getResources().getColor(R.color.common_black));
        tv_online.setTextColor(getActivity().getResources().getColor(R.color.common_black));
        tv_outline.setTextColor(getActivity().getResources().getColor(R.color.common_black));
        tv_drive.setTextColor(getActivity().getResources().getColor(R.color.common_black));
        tv_task.setTextColor(getActivity().getResources().getColor(R.color.common_black));
        textView.setTextColor(getActivity().getResources().getColor(R.color.blue_color1));
    }

    /**
     * 过滤数据
     */
    private void filterCompanyData() {
        if (isFillData) {
            return;
        }

        data.clear();
        mLinkedList.clear();
        String status = (String) tv_car_status.getTag();
        int level = 1;
        setList(companyBeanList, level, Integer.parseInt(status));
        mAdapter.setType(Integer.parseInt(status));
        mLinkedList.addAll(NodeHelper.sortDepts(data));
        //TODO 2021.11.30
        mAdapter.setLinkedList(mLinkedList);
        id_tree.setAdapter(mAdapter);


//        mAdapter.notifyDataSetChanged();
        isFillData = true;

    }

    /**
     * 遍历
     *
     * @param dataList
     */
    private void setList(List<CompanyItemBean> dataList, int level, int status) {
        for (int m = 0; m < dataList.size(); m++) {
            Dept dept = null;
            if(dataList.get(m).getPid()==null){
                dept = new Dept(dataList.get(m).getId(),"", dataList.get(m).getCarNumber(), dataList.get(m));
            }else{
                dept = new Dept(dataList.get(m).getId(), dataList.get(m).getPid(), dataList.get(m).getCarNumber(), dataList.get(m));
            }
            dept.set_level(level);
            switch (status) {
                case -1:// 全部
                    data.add(dept);
                    break;
                case 0://在线  10、20、40、51、52、60
                    if (dataList.get(m).getCarStatus() ==10 || dataList.get(m).getType() != 0 ||dataList.get(m).getCarStatus() ==20 ||dataList.get(m).getCarStatus() ==40||dataList.get(m).getCarStatus() ==51||dataList.get(m).getCarStatus() ==52||dataList.get(m).getCarStatus() ==60) {
                        data.add(dept);
                    }
                    break;
                case 1://行驶  60
                    if (dataList.get(m).getCarStatus() == 60|| dataList.get(m).getType() != 0) {
                        data.add(dept);
                    }
                    break;
                case 8://任务
                    break;
                case 4://离线  30 、255
                    if (dataList.get(m).getCarStatus() == 30 || dataList.get(m).getCarStatus() == 255|| dataList.get(m).getType() != 0) {
                        data.add(dept);
                    }
                    break;
            }
//            if (status == -1 || dataList.get(m).getStatus() == status || dataList.get(m).getType() != 0) {
//                Dept dept = new Dept(dataList.get(m).getId(), dataList.get(m).getPId(), dataList.get(m).getName(), dataList.get(m));
//                dept.set_level(level);
//                data.add(dept);
//            }
            int levels = level + 1;
            setList(dataList.get(m).getChildren(), levels, status);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            MonitorResponseBean bean = (MonitorResponseBean) data.getSerializableExtra("bean");
            if(markerList!=null){
                for (Overlay overlay : markerList) {
                    Marker marker = (Marker) overlay;
                    MonitorResponseBean monitorResponseBean = (MonitorResponseBean) marker.getExtraInfo().getSerializable("bean");
                    if (bean.getCarNumber().equals(monitorResponseBean.getCarNumber())) {
                        onMarkerClick(marker);
                        break;
                    }
                }
            }else {
                ToastUitl.showLong("当前车辆不存在，请刷新界面后重新操作。");
            }
        }else  if (requestCode == 1315){
            requestPermission();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(mBaiduMap.getMapStatus().zoom<10&&marker.getExtraInfo()==null){
              return false;
        }
        if(mBaiduMap.getMapStatus().zoom>=10){
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.zoom(19);
        }

            linCar.setVisibility(View.VISIBLE);
            //取控件当前的布局参数
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linCar.getLayoutParams();
            params.setMargins(dip2px(getActivity(), 0), dip2px(getActivity(), 0), dip2px(getActivity(), 15), DisplayUtil.getScreenHeight(getActivity()) / 3+ dip2px(getActivity(), 30));
            //使设置好的布局参数应用到控件
            linCar.setLayoutParams(params);


            currentMarker = marker;
            // 获取数据
            MonitorResponseBean dataBean = (MonitorResponseBean) marker.getExtraInfo().getSerializable("bean");
            if(dataBean!=null){
                // 底部闪锁
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", dataBean);
                animationMarker.setExtraInfo(bundle);
                // 显示
                animationMarker.setVisible(true);
                animationMarker.setPosition(marker.getPosition());
                // 弹出操作页面块
                setMapBottom(dataBean);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 地图滑动到中心
                        MapStatus.Builder builder = new MapStatus.Builder();
                        builder.zoom(19).target(marker.getPosition());
                        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(marker.getPosition()));
                        ((MainActivity) getActivity()).updateVRMap(dataBean.getLng(), dataBean.getLat());
                    }
                }, 500);

                AppPreferenceSetting.setLastMonitorCar(new Gson().toJson(dataBean));
            }
        return false;
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        if (nowIsShow) {
            ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
        } else {

        }
    }

//    private LatLng convert(LatLng latLng) {
//        if (converter == null) {
//            converter = new CoordinateConverter();
//            converter.from(CoordinateConverter.CoordType.GPS);
//        }
//        return converter.coord(latLng).convert();
//    }

    private void updateMarker(Marker marker, MonitorResponseBean bean) {
        LatLng latLng = BDMapUtils.convert(new LatLng(bean.getLat(), bean.getLng()));
        if(mBaiduMap.getMapStatus().zoom>=10){
            if(marker.getInfoWindow()!=null){
//                marker.updateInfoWindowPosition(latLng);

                Button button = new Button(getContext());
                button.setBackground(null);
                button.setText(bean.getCarNumbers());
                int color=CarStateFactory.getCarTextColorByStatus(bean.getCarDisplayColorFormat());
                button.setTextColor(getContext().getResources().getColor(color));
                button.setPadding(5,5,5,5);
                button.setTextSize(12);
                BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromView(button);

                marker.getInfoWindow().setBitmapDescriptor(bitmapDescriptor);
            }
        }

        marker.setPositionWithInfoWindow(latLng);
//        marker.setPosition(latLng);
        marker.setRotate(90 + 360 - bean.getAngle());
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(CarStateFactory.getCarColorByStatus(bean.getCarDisplayColorFormat()));
        marker.setIcon(bitmap);
        bean.setBitmap(bitmap);
        marker.getExtraInfo().putSerializable("bean", bean);
    }

    private void updateAnimMarker(Marker marker, MonitorResponseBean bean) {
        LatLng latLng = BDMapUtils.convert(new LatLng(bean.getLat(), bean.getLng()));
        marker.setPositionWithInfoWindow(latLng);
        marker.getExtraInfo().putSerializable("bean", bean);
    }


    class AddMarkTask extends AsyncTask<List<MonitorResponseBean>, Integer, MonitorResponseBean> {//继承AsyncTask
        MonitorResponseBean bean;
        List<OverlayOptions> overlayOptions = new ArrayList<>();
        @Override
        protected MonitorResponseBean doInBackground(List<MonitorResponseBean>... params) {//处理后台执行的任务，在后台线程执行

            for (Iterator<MonitorResponseBean> it = params[0].iterator(); it.hasNext(); ) {
                bean = it.next();
                if (isCancelled()) {
                    return null;
                }
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(CarStateFactory.getCarColorByStatus(bean.getCarDisplayColorFormat()));
                bean.setBitmap(bitmap);
                LatLng latLng = BDMapUtils.convert(new LatLng(bean.getLat(), bean.getLng()));
                //用来构造InfoWindow
                Button button = new Button(getContext());
                button.setBackground(null);
                button.setText(bean.getCarNumbers());
                int color=CarStateFactory.getCarTextColorByStatus(bean.getCarDisplayColorFormat());
                button.setTextColor(getContext().getResources().getColor(color));
                button.setPadding(5,5,5,5);
                button.setTextSize(12);
                // 创建InfoWindow
                InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button),bean.getPosition(), -20, null);
                // 设置数据
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean);
                // 添加infowindow
                OverlayOptions options = new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .position(latLng)
                        .rotate(90 + 360 - bean.getAngle())// 地图的0度是地图的东，服务器返回的0度是地图的北
                        .extraInfo(bundle)
                        .icon(bitmap).infoWindow(mInfoWindow);
                overlayOptions.add(options);

            }
            return bean;
        }

        protected void onProgressUpdate(Integer... progress) {//在调用publishProgress之后被调用，在ui线程执行
            super.onProgressUpdate(progress);
        }

        protected void onPostExecute(MonitorResponseBean result) {//后台任务执行完之后被调用，在ui线程执行
            super.onPostExecute(result);
            if (result != null) {
                Log.d("LocusActivity", "加载完成。");
            } else {
                Log.d("LocusActivity", "加载失败。");
            }
            markerList = mBaiduMap.addOverlays(overlayOptions);
            onMapStatusChangeFinish(mBaiduMap.getMapStatus());
        }

        protected void onPreExecute() {//在 doInBackground(Params...)之前被调用，在ui线程执行
        }

        protected void onCancelled() {//在ui线程执行
        }

    }
    class UpdateMarkTask extends AsyncTask<List<UpdateMark>, Integer, UpdateMark> {//继承AsyncTask
        UpdateMark bean;
        @Override
        protected UpdateMark doInBackground(List<UpdateMark>... params) {//处理后台执行的任务，在后台线程执行
                for (Iterator<UpdateMark> it = params[0].iterator(); it.hasNext(); ) {
                    bean = it.next();
                    if (isCancelled()) {
                        return null;
                    }
                    LatLng latLng = BDMapUtils.convert(new LatLng(bean.monitorResponseBean.getLat(),bean.monitorResponseBean.getLng()));


                    if(mBaiduMap.getMapStatus().zoom>=10){
                        if(bean.marker.getInfoWindow()!=null){
                            Button button = new Button(getContext());
                            button.setBackground(null);
                            button.setText(bean.monitorResponseBean.getCarNumbers());
                            int color=CarStateFactory.getCarTextColorByStatus(bean.monitorResponseBean.getCarDisplayColorFormat());
                            button.setTextColor(getContext().getResources().getColor(color));
                            button.setPadding(5,5,5,5);
                            button.setTextSize(12);
                            // 创建InfoWindow
                            BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromView(button);
                            if(bean.marker.isInfoWindowEnabled()==false){
                                bean.marker.showInfoWindow(bean.marker.getInfoWindow());
                            }else{
                                bean.marker.getInfoWindow().setBitmapDescriptor(bitmapDescriptor);
                            }
                        }
                        bean.marker.setPositionWithInfoWindow(latLng);
                        bean.marker.setRotate(90 + 360 - bean.monitorResponseBean.getAngle());
                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromResource(CarStateFactory.getCarColorByStatus(bean.monitorResponseBean.getCarDisplayColorFormat()));
                        bean.marker.setIcon(bitmap);
                        bean.monitorResponseBean.setBitmap(bitmap);
                        bean.marker.getExtraInfo().putSerializable("bean", bean.monitorResponseBean);


                        Bundle bundle = animationMarker.getExtraInfo();
                        if (bundle != null) {
                            MonitorResponseBean monitorResponseBean = (MonitorResponseBean) bundle.get("bean");
                            if (monitorResponseBean.getCarNumber().equals(bean.monitorResponseBean.getCarNumber())) {
//                                updateAnimMarker(animationMarker, bean.monitorResponseBean);
                                animationMarker.setPositionWithInfoWindow(latLng);
                                animationMarker.getExtraInfo().putSerializable("bean", bean.monitorResponseBean);
                                currentMarker.updateInfoWindowPosition(latLng);
                                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
                            }
                        }
                    }
                }

            return bean;
        }

        protected void onProgressUpdate(Integer... progress) {//在调用publishProgress之后被调用，在ui线程执行
            super.onProgressUpdate(progress);
        }

        protected void onPostExecute(UpdateMark result) {//后台任务执行完之后被调用，在ui线程执行
            super.onPostExecute(result);
            if (result != null) {
                Log.d("LocusActivity", "加载完成。");
            } else {
                Log.d("LocusActivity", "加载失败。");
            }
 /*  if (currentMarker != null && dataBean != null) {
                LatLng latLng2 = BDMapUtils.convert(new LatLng(dataBean.getLat(), dataBean.getLng()));
                currentMarker.updateInfoWindowPosition(latLng2);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng2));
            }*/
            updateMarkTask.cancel(true);
            updateMarkTask=null;
        }

        protected void onPreExecute() {//在 doInBackground(Params...)之前被调用，在ui线程执行
        }

        protected void onCancelled() {//在ui线程执行
        }

    }
    AddMarkTask addMarkTask;
    UpdateMarkTask updateMarkTask;

    List<MonitorResponseBean> jieKoumonitorResponseBeanList;

    @Override
    public void returnVehiclesList(List<MonitorResponseBean> monitorResponseBeanList) {
        if(animation!=null){
            //停止旋转
            tv_refresh.clearAnimation();
        }
        if (getActivity() instanceof MainActivity) {
           // ((MainActivity) getActivity()).setCarNumber(monitorResponseBeanList.size());
            ((MainActivity) getActivity()).setCarDatas(monitorResponseBeanList);
        }
        jieKoumonitorResponseBeanList=monitorResponseBeanList;
        List<MonitorResponseBean> moreResponseBean = new ArrayList<>();
        List<UpdateMark> updateResponseBean = new ArrayList<>();
        if(monitorResponseBeanList.size()>0){
            if (markerList != null) {
                for (MonitorResponseBean bean : monitorResponseBeanList) {
                    if (dataBean != null && dataBean.getCarNumber().equals(bean.getCarNumber())) {
                        dataBean = bean;
                    }
                    // 车辆
                    boolean isMore = true;
                    for (Overlay overlay : markerList) {
                        Marker marker = (Marker) overlay;

                        MonitorResponseBean responseBean = (MonitorResponseBean) marker.getExtraInfo().getSerializable("bean");
                        if (responseBean.getCarNumber().equals(bean.getCarNumber())) {
                            final LatLngBounds visibleBounds = mBaiduMap.getMapStatus().bound;//地图在手机屏幕上的可见范围
                            boolean onScreen = visibleBounds.contains(responseBean.getPosition());//是否在屏幕内
                            isMore = false;
//                            if(onScreen){
//                                updateMarker(marker, bean);
//                                break;
//                            }
                            updateResponseBean.add(new UpdateMark(bean,marker));
                        }
                    }

                    //闪烁
                    Bundle bundle = animationMarker.getExtraInfo();
                    if (bundle != null) {
                        MonitorResponseBean monitorResponseBean = (MonitorResponseBean) bundle.get("bean");
//                        if (monitorResponseBean.getCarNumber().equals(bean.getCarNumber())) {
//                            updateAnimMarker(animationMarker, bean);
//                        }
                        if (isMore) {
                            moreResponseBean.add(monitorResponseBean);
                        }
                    }
                }
                if (updateMarkTask == null && mBaiduMap.getMapStatus().zoom>=10) {
                    updateMarkTask = new UpdateMarkTask();
                    updateMarkTask.execute(updateResponseBean);
                }
                // 新增的车辆信息
                if (moreResponseBean.size() > 0) {
                    markerList.addAll(addMark(moreResponseBean));
                }
            } else {
//                markerList = addMark(monitorResponseBeanList);
                if (addMarkTask == null) {
                    addMarkTask = new AddMarkTask();
                    addMarkTask.execute(monitorResponseBeanList);
                }
            }
        }else{
            if (markerList != null) {
                for (Overlay seekBarPolylineOverlay : markerList) {
                    seekBarPolylineOverlay.remove();
                }
                markerList.clear();
                markerList=null;
            }
            dataBean=null;
            //mMapView.removeView(view);
            linCar.setVisibility(View.GONE);
            mBaiduMap.clear();


            mlixianLinkedList = new LinkedList<>();
            mzaixianLinkedList = new LinkedList<>();
            mxingshiLinkedList = new LinkedList<>();
            tingcheLinkedList = new LinkedList<>();
            baojingLinkedList = new LinkedList<>();
            daisuLinkedList = new LinkedList<>();
            weidingweiLinkedList = new LinkedList<>();
        }

        if (bottomControlPanel != null && bottomControlPanel.isShow()) {
            showPanel();
        }
//        if (currentMarker != null && dataBean != null) {
//            LatLng latLng = convert(new LatLng(dataBean.getLat(), dataBean.getLng()));
//            currentMarker.updateInfoWindowPosition(latLng);
//            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
//        }
    }
    private LinkedList<Dept> mlixianLinkedList = new LinkedList<>();
    private LinkedList<Dept> mzaixianLinkedList = new LinkedList<>();
    private LinkedList<Dept> mxingshiLinkedList = new LinkedList<>();
    private LinkedList<Dept> tingcheLinkedList = new LinkedList<>();
    private LinkedList<Dept> baojingLinkedList = new LinkedList<>();
    private LinkedList<Dept> daisuLinkedList = new LinkedList<>();
    private LinkedList<Dept> weidingweiLinkedList = new LinkedList<>();
    private LinkedList<Dept> allLinkedList = new LinkedList<>();
    @Override
    public void returnCompanyListRequest(List<CompanyItemBean> dataList) {
        this.companyBeanList = dataList;

        if(isRefDara){
            isFillData=false;
            filterCompanyData();
            isRefDara=false;
        }
        setleftcardata(-1, null,"全部",tv_all);
        setleftcardata(0, tvZaiXian,"在线",tv_online);
        setleftcardata(1, tvXingShi,"行驶",tv_drive);
        setleftcardata(4, tvLiXian,"离线",tv_outline);

        //TODO 2022.2.11 未添加数据
//        setleftcardata(6, tingcheLinkedList, tvTingChe, "停车",null);
//        setleftcardata(6, baojingLinkedList, tvBaoJing, "报警",null);
//        setleftcardata(6, daisuLinkedList, tvDaiSu, "怠速",null);
//        setleftcardata(6, weidingweiLinkedList, tvWeiDingWei, "未定位",null);
    }

    public int leafCount(Dept dept,int type) {
        int count = 0;
        if (dept.getCompanyItemBean().getType() == 0) {
            if (type == -1) {// 表示选择的是全部（排除状态为离线的数量  dept.getCompanyItemBean().getCarStatus() == 30 || dept.getCompanyItemBean().getCarStatus() == 255）
                count = 1;
            } else {
                count = 1;
            }

        } else {
            for (Node node : dept.get_childrenList()) {
                Dept mDept = (Dept) node;
                count += leafCount(mDept,type);
            }

        }
        return count;
    }
    public void setleftcardata(int status,TextView txt,String str,TextView righttxt){
        LinkedList<Dept> linkedList=new LinkedList<Dept>();
        setList(companyBeanList, 1, status);
        linkedList.addAll(NodeHelper.sortDepts(data));
        if(txt!=null){
            if (linkedList.size() > 0) {
                String str2 = leafCount(linkedList.getFirst(),status) + "<br><font><small>" + str + "</small></font>";
                txt.setText(Html.fromHtml(str2));
            } else {
                String str2 = "--<br><font><small>" + str + "</small></font>";
                txt.setText(Html.fromHtml(str2));
            }
        }
        if(righttxt!=null&&righttxt.getText().toString().trim()!=null){
            if (linkedList.size() > 0) {
                righttxt.setText(righttxt.getText().toString().trim().substring(0,righttxt.getText().toString().trim().lastIndexOf("辆")+1)+"("+leafCount(linkedList.getFirst(),status)+")");
            }else{
                righttxt.setText(righttxt.getText().toString().trim().substring(0,righttxt.getText().toString().trim().lastIndexOf("辆")+1)+"(0)");
            }
        }
    }
    @Override
    public void returnStaff(Boolean str) {
        SPDefaultHelper.saveBoolean(SPDefaultHelper.USER_ISSTAFF, str);
    }
    private List<Overlay> addMark(List<MonitorResponseBean> monitorResponseBeanList) {
        List<OverlayOptions> overlayOptions = new ArrayList<>();
        for (MonitorResponseBean bean : monitorResponseBeanList) {
       /*     final LatLngBounds visibleBounds = mBaiduMap.getMapStatus().bound;//地图在手机屏幕上的可见范围
            boolean onScreen = visibleBounds.contains(bean.getPosition());//是否在屏幕内
            if(onScreen){*/

                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(CarStateFactory.getCarColorByStatus(bean.getCarDisplayColorFormat()));
                bean.setBitmap(bitmap);
                LatLng latLng = BDMapUtils.convert(new LatLng(bean.getLat(), bean.getLng()));
                //用来构造InfoWindow
                Button button = new Button(getContext());
                button.setBackground(null);
                button.setText(bean.getCarNumbers());
                int color=CarStateFactory.getCarTextColorByStatus(bean.getCarDisplayColorFormat());
                button.setTextColor(getContext().getResources().getColor(color));
                button.setPadding(5,5,5,5);
                button.setTextSize(12);
                // 创建InfoWindow
                InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button),bean.getPosition(), -20, null);
                // 设置数据
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean);
                // 添加infowindow
                OverlayOptions options = new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .position(latLng)
                        .rotate(90 + 360 - bean.getAngle())// 地图的0度是地图的东，服务器返回的0度是地图的北
                        .extraInfo(bundle)
                        .visible(true)
                        .icon(bitmap).infoWindow(mInfoWindow);
            overlayOptions.add(options);
           /* }*/
        }
        return mBaiduMap.addOverlays(overlayOptions);
    }

    private void setMapBottom(MonitorResponseBean dataBean) {
        this.dataBean = dataBean;
//        hidePanel();
        if (bottomControlPanel == null) {
            bottomControlPanel = BottomControlPanel.newInstance(getActivity(), this);
        }

        this.dataBean.setStartlocationDescribe(locationDescribe);
        this.dataBean.setStartlocationLat(locationlat);
        this.dataBean.setStartlocationLng(locationlng);
        bottomControlPanel.setData(this.dataBean);
        bottomControlPanel.show(mMapView,getActivity());
    }

    public void hidePanel() {
        if (bottomControlPanel != null) {
            bottomControlPanel.dissmiss();
            bottomControlPanel = null;
        }
    }

    public boolean nowIsShow;

    public void showPanel() {
        if (dataBean != null && nowIsShow) {
            setMapBottom(dataBean);
        }
    }

    @Override
    public void onPre() {
        int i = 0;
        for (Overlay overlay : markerList) {
            Marker marker = (Marker) overlay;
            if (marker.getId().equals(currentMarker.getId())) {
                break;
            }
            i++;
        }
        if (i > 0) {
            onMarkerClick((Marker) markerList.get(i - 1));
        } else {
            onMarkerClick((Marker) markerList.get(markerList.size() - 1));
        }
    }

    @Override
    public void onNext() {
        int i = 0;
        for (Overlay overlay : markerList) {
            Marker marker = (Marker) overlay;
            if (marker.getId().equals(currentMarker.getId())) {
                break;
            }
            i++;
        }
        if (i < (markerList.size() - 1)) {
            onMarkerClick((Marker) markerList.get(i + 1));
        } else {
            onMarkerClick((Marker) markerList.get(0));
        }
    }

    @Override
    public void onZZ() {
        ((MainActivity) getActivity()).hideVRMap();
    }

    @Override
    public void onShowNextPreCar(int hight) {
       // linCar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDismissNextPreCar() {
       // linCar.setVisibility(View.GONE);
    }
    @Override
    public void onItemClick(Dept dept) {
        if(dept!=null){
            if (dept.getCompanyItemBean().getType() == 0) {
                if(dept.getCompanyItemBean().getIsPass()==1){
                    ToastUtil.showToast("该车辆已过期",0);
                }else if(dept.getCompanyItemBean().getIsPass()==2){
                    ToastUtil.showToast("该车辆暂无定位数据",0);
                }else{
                    iv_car.performClick();
                    for (Overlay overlay : markerList) {
                        Marker marker = (Marker) overlay;
                        MonitorResponseBean monitorResponseBean = (MonitorResponseBean) marker.getExtraInfo().getSerializable("bean");
                        if(monitorResponseBean!=null){
                            if (monitorResponseBean.getCarNumber().equals(dept.getCompanyItemBean().getCarNumber())) {
                                onMarkerClick(marker);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    int cityId = 0;
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            mBaiduMap.setMyLocationEnabled(true);
            if (is_First_locate) {
                /**
                 * 离线地图相关数据
                 * */
                if(mOffline!=null&&location.getCity()!=null&&!location.getCity().equals("")){
                    ArrayList<MKOLSearchRecord> records = mOffline.searchCity(location.getCity());
                    if (records != null && records.size() == 1) {
                        cityId = records.get(0).cityID;
                    }
                    // 获取已下过的离线地图信息
                    List<MKOLUpdateElement> localMapList = mOffline.getAllUpdateInfo();
                    if (localMapList == null) {
                        localMapList = new ArrayList<MKOLUpdateElement>();
                    }

                    boolean isContent=false;
                    for (MKOLUpdateElement mk:localMapList) {
                        if(mk.cityID==cityId){
                            isContent=true;
                            break;
                        }
                    }
                    if(isContent==false){
                        // 开始下载离线地图
                        // cityID 城市的数字标识
                        mOffline.start(cityId);
                    }
                }

                is_First_locate = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(13.0f);

                //给地图设置状态
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }
            locationDescribe = location.getLocationDescribe();
            locationlat=location.getLatitude();
            locationlng=location.getLongitude();
        }
    }
    String locationDescribe;
    double locationlat;
    double locationlng;

   public class UpdateMark{
       MonitorResponseBean monitorResponseBean;
       Marker marker;

       public UpdateMark(MonitorResponseBean monitorResponseBean, Marker marker) {
           this.monitorResponseBean = monitorResponseBean;
           this.marker = marker;
       }

       public MonitorResponseBean getMonitorResponseBean() {
           return monitorResponseBean;
       }

       public void setMonitorResponseBean(MonitorResponseBean monitorResponseBean) {
           this.monitorResponseBean = monitorResponseBean;
       }

       public Marker getMarker() {
           return marker;
       }

       public void setMarker(Marker marker) {
           this.marker = marker;
       }
   }
}
