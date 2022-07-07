package com.ttce.vehiclemanage.ui.locus.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.lbsapi.panoramaview.PanoramaViewListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.ui.locus.contract.TrackContract;
import com.ttce.vehiclemanage.ui.locus.model.TrackModel;
import com.ttce.vehiclemanage.ui.locus.popwindow.BottomControlPanel;
import com.ttce.vehiclemanage.ui.locus.presenter.TrackPresenter;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.CarStateFactory;
import com.ttce.vehiclemanage.utils.DeviceUtils;
import com.ttce.vehiclemanage.utils.LocationUtils;
import com.ttce.vehiclemanage.utils.MapUtil;
import com.ttce.vehiclemanage.utils.OtherUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.tree.Dept;
import com.ttce.vehiclemanage.widget.tree.NodeHelper;
import com.ttce.vehiclemanage.widget.tree.NodeTreeAdapter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

import static com.jaydenxiao.common.commonutils.DisplayUtil.dip2px;
import static com.ttce.vehiclemanage.app.AppConstant.MESSAGE_UPDATE_TRACK_VR;

public class TrackActivity extends BaseActivity<TrackPresenter, TrackModel>
        implements TrackContract.View, LocationUtils.LocationListener, BottomControlPanel.ControlPanelListener,
        NodeTreeAdapter.OnItemClickListener {
    private final String TAG = "TrackActivity";

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView carListIV;
    @Bind(R.id.rootRL)
    RelativeLayout rootRL;
    @Bind(R.id.ll_right_panel)
    LinearLayout ll_right_panel;
    @Bind(R.id.ll_status)
    LinearLayout ll_status;
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
    @Bind(R.id.id_tree)
    ListView deptTreeLV;
    @Bind(R.id.iv_car_expand)
    ImageView iv_car_expand;
    @Bind(R.id.mapview)
    TextureMapView mMapView;
    @Bind(R.id.myPositionIV)
    ImageView myPositionIV;
    @Bind(R.id.carIV)
    ImageView carIV;
    @Bind(R.id.zoomJiaTV)
    TextView zoomJiaTV;
    @Bind(R.id.zoomJianTV)
    TextView zoomJianTV;
    @Bind(R.id.fl_panorama)
    FrameLayout fl_panorama;
    @Bind(R.id.iv_full)
    ImageView fullIV;

    MonitorResponseBean currentData;
    private List<MonitorResponseBean> datas;

    //    private PanoramaView panoramaView;
    private BaiduMap mBaiduMap;
    private OverlayOptions myLocationOptions;
    private Marker myLocMark;
    private Marker carMarker;
    private BottomControlPanel bottomControlPanel;

    private LocationUtils locationUtils;
    private final long DATA_TIME_INTERVAL = 10000l;// 2s取一次数据
    private List<LatLng> carPointList = new ArrayList<>();
    private LatLng myPoint;
    private Overlay polylineOverlay;
    private Thread carMoveThread;
    private int moveFinishInterval = 3000;// 3秒钟内，完成移动
    private int TIME_INTERVAL = 80;// 时间间隔，线程休息的间隔
    private double DISTANCE = 0;// 距离
    private boolean initCarLocation;
    private Timer carMoveTimer;
    private int carPositionIndex = 0;
    private int position;
    private boolean initBoundView;
    private boolean isNormal = false;
    private boolean isTraffic = false;
    private boolean isFillData = false;
    private boolean isFullScreen = false;

    // 缓存机构数据
    private List<CompanyItemBean> companyBeanList;
    private NodeTreeAdapter mAdapter;
    private LinkedList<Dept> mLinkedList = new LinkedList<>();
    List<Dept> data = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_track;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        String str= SPDefaultHelper.getString(SPDefaultHelper.CAR_DATAS);
        datas=OtherUtil.getDataList(str);

        String JsonData = getIntent().getStringExtra("currentData");
        currentData = new Gson().fromJson(JsonData,MonitorResponseBean.class);

        if (currentData == null) {
            finish();
            return;
        }
        if (datas != null) {
            position = datas.indexOf(currentData);
        }
        titleBarTitle.setText("追踪");
//        carListIV.setImageResource(R.mipmap.ico_track_car_list);
//        carListIV.setVisibility(View.VISIBLE);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,0,0,DisplayUtil.getScreenHeight(this) / 3-30);//4个参数按顺序分别是左上右下
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        myPositionIV.setLayoutParams(layoutParams);

//        panoramaView = new PanoramaView(this);
        mMapView.showZoomControls(true);
        mBaiduMap = mMapView.getMap();
        EventBus.getDefault().register(this);

        mBaiduMap.setOnMarkerClickListener(myOnMarkerClickListener);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(BDMapUtils.convert(new LatLng(currentData.getLat(), currentData.getLng())));
        if (mBaiduMap != null && mapStatusUpdate != null) {
            //mBaiduMap.animateMapStatus(mapStatusUpdate);
            mBaiduMap.setMapStatus(mapStatusUpdate);
        }

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                hideVRMap();
            }


            @Override
            public void onMapPoiClick(MapPoi poi) {

            }
        });

//        panoramaView.setPanoramaViewListener(this);
        initData();
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                    setMapBottom();
            }
        });

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

    private void initData() {
        checkPermission();
        mAdapter = new NodeTreeAdapter(this, deptTreeLV, /*mLinkedList,*/ -1);
        mAdapter.setOnItemClickListener(this);
      //  deptTreeLV.setAdapter(mAdapter);//TODO 2021.11.30
//        mPresenter.getDeptData();//TODO 2022.6.17
    }

    @Override
    public void buildDeptTree(List<CompanyItemBean> deptListData) {
        this.companyBeanList = deptListData;
        filterCompanyData();
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
        deptTreeLV.setAdapter(mAdapter);

      //  mAdapter.notifyDataSetChanged();
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
    public void onItemClick(Dept dept) {
        if (dept.getCompanyItemBean().getType() == 0) {
            if(dept.getCompanyItemBean().getIsPass()==1){
                ToastUtil.showToast("该车辆已过期",0);
            }else{

                MonitorResponseBean monitorResponseBean = new MonitorResponseBean();
                monitorResponseBean.setCarNumber(dept.getCompanyItemBean().getCarNumber());
                position = datas.indexOf(monitorResponseBean);

                if (position != -1) {
                    currentData = datas.get(position);
                    reloadData();
                    ll_right_panel.setVisibility(View.GONE);
                }
            }
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
        myPoint = new LatLng(location.getLatitude(), location.getLongitude());
        initMyLocation(location);
        mPresenter.getTrackData(currentData.getDeviceId());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                locationUtils.startLocation();
            }
        }, DATA_TIME_INTERVAL);
    }

    @Override
    public void failed() {

    }

    private void initMyLocation(BDLocation location) {
        if (location == null) {
            return;
        }

        if (myLocMark != null) {
            myLocMark.remove();
        }

        myLocationOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_point)).zIndex(9).draggable(true);
        myLocMark = (Marker) mBaiduMap.addOverlay(myLocationOptions);
    }

    private void initCarLocation() {
        if (initCarLocation) {
            return;
        }

        LatLng carPoint = null;
        if (carPointList != null && carPointList.size() > 0) {
            carPoint = carPointList.get(0);
        }

        if (carPoint == null) {
            return;
        }

        if (carMarker != null) {
            carMarker.remove();
        }
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(
                CarStateFactory.getCarColorByStatus(currentData.getCarDisplayColorFormat()));
        OverlayOptions markerOptions =
                new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(bitmap).position(carPoint).zIndex(9);
        // 添加覆盖物小车的图标
        carMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
        initCarLocation = true;

        // 初始化当前位置与车辆位置的指向线
        updateMyLocToCarPointLine(carPointList.get(carPointList.size() - 1));

        startCarMoveAnimation();
    }

    // 更新当前位置与车辆最后位置的指向线
    private void updateMyLocToCarPointLine(LatLng lastCarPoint) {
        if (polylineOverlay != null) {
            polylineOverlay.remove();
        }

        List<LatLng> points = new ArrayList<>();
        points.add(myPoint);
        points.add(lastCarPoint);
        OverlayOptions polylineOptions = new PolylineOptions().width(5).dottedLine(false).zIndex(8)
                .color(getResources().getColor(R.color.red)).points(points);
        polylineOverlay = mBaiduMap.addOverlay(polylineOptions);
    }

    @Override
    public void drawTrack(TravelItemBean currentPositionData) {
        if (currentPositionData == null) {
            return;
        }

        LatLng bdPoint =
                BDMapUtils.convert(new LatLng(currentPositionData.getLat(), currentPositionData.getLng()));
        carPointList.add(bdPoint);
        if (carPointList != null && carPointList.size() > 1) {
            double moveDIsance = MapUtil.getDistance(carPointList.get(carPointList.size() - 1),
                    carPointList.get(carPointList.size() - 2));
            if (moveDIsance == 0) {
                carPointList.remove(carPointList.size() - 1);
            }
        }

        // 初始化车辆位置
        initCarLocation();
        // 控制可视区域范围
        if (!initBoundView) {
            List<List<LatLng>> pointsList = new ArrayList<>();
            List<LatLng> subPointList = new ArrayList<>();
            subPointList.add(bdPoint);
            subPointList.add(myPoint);
            pointsList.add(subPointList);
            MapUtil.showAllArea(this, mMapView, mBaiduMap, pointsList, dip2px(100));
            initBoundView = true;
        }
    }

    private void startCarMoveAnimation() {
        carMoveTimer = new Timer();
        carMoveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (carPointList != null && carPointList.size() > 1) {
                    // 边走边画追踪轨迹路线
                    if (carPointList.size() > carPositionIndex + 1) {
                        carMove(carPointList.get(carPositionIndex), carPointList.get(carPositionIndex + 1));
                        EventBus.getDefault().post(new MessageEvent(MESSAGE_UPDATE_TRACK_VR));
                        carPositionIndex++;
                    }
                }
            }
        }, 500, moveFinishInterval);
    }
    private void carMove(final LatLng startPoint, final LatLng endPoint) {
        carMoveThread = new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d(TAG, "carMove running");
                LatLng[] linePoint = new LatLng[2];
                LatLng lastPoint = null;
                carMarker.setPosition(startPoint);// 设置 Marker 覆盖物的位置坐标
                carMarker.setRotate((float) MapUtil.getAngle(startPoint, endPoint) + 90);// 设置 Marker 覆盖物旋转角度，逆时针
                boolean xIsReverse = endPoint.latitude > startPoint.latitude;// x方向，像右是正方向
                boolean yIsReverse = endPoint.longitude > startPoint.longitude;// y方向，像上是正方向
                double length = MapUtil.getDistance(startPoint, endPoint);// 求得两个点之间的连线长度
                DISTANCE = length / (moveFinishInterval / TIME_INTERVAL);// 每3000/80毫秒移动的距离
                double xMove = MapUtil.xMoveDistance(startPoint, endPoint, DISTANCE, length);// 计算每次x方向上移动的距离
                double yMove = MapUtil.yMoveDistance(startPoint, endPoint, DISTANCE, length);// 计算每次y方向上移动的距离
                for (int i = 0; i < moveFinishInterval / TIME_INTERVAL; i++) {// 遍历4000/80次
                    double x, y;

                    if (xIsReverse) {// 向右移动
                        x = startPoint.latitude + i * xMove;
                    } else {// 向左移动
                        x = startPoint.latitude - i * xMove;
                    }

                    if (yIsReverse) {// 向上
                        y = startPoint.longitude + i * yMove;
                    } else {// 向下
                        y = startPoint.longitude - i * yMove;
                    }
                    LatLng latLng = new LatLng(x, y);

                    final LatLng finalLatLng = latLng;
                    carMarker.setPosition(finalLatLng);

                    if (i == 0) {
                        linePoint[0] = startPoint;
                        linePoint[1] = finalLatLng;
                    } else {
                        linePoint[0] = lastPoint;
                        linePoint[1] = finalLatLng;
                    }
                    lastPoint = finalLatLng;

                    // 追踪轨迹路线
                    Log.d(TAG, "point1:" + linePoint[0].latitude + "," + linePoint[0].longitude + " point2:"
                            + linePoint[1].latitude + "," + linePoint[1].longitude);
                    OverlayOptions polylineOptions = new PolylineOptions().width(8).dottedLine(false).zIndex(8)
                            .color(getResources().getColor(R.color.blue_color1)).points(Arrays.asList(linePoint));
                    PolylineOverlayList.add(mBaiduMap.addOverlay(polylineOptions));

                    // 更新当前位置与车辆最后位置的指向线
                    updateMyLocToCarPointLine(lastPoint);

                    try {
                        Thread.sleep(moveFinishInterval / TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

                if (mBaiduMap != null) {
                    mBaiduMap.removeMarkerClickListener(myOnMarkerClickListener);
                    mBaiduMap.setOnMarkerClickListener(myOnMarkerClickListener);
                }
            }
        };
        carMoveThread.start();
    }
    private List<Overlay> PolylineOverlayList = new ArrayList<>();
    private void reloadData() {
//        setMapBottom();
        initCarLocation = false;

        initBoundView = false;
        if (PolylineOverlayList != null) {
            for (Overlay seekBarPolylineOverlay : PolylineOverlayList) {
                seekBarPolylineOverlay.remove();
            }
        }

        carPointList.clear();
        mPresenter.getTrackData(currentData.getDeviceId());
    }

    @Override
    public void onPre() {
        if (position == -1) {
            return;
        }

        position--;
        if (position < 0) {
            position = 0;
        }
        currentData = datas.get(position);
        reloadData();
    }

    @Override
    public void onNext() {
        if (position == -1) {
            return;
        }

        position++;
        if (position > datas.size() - 1) {
            position = datas.size() - 1;
        }
        currentData = datas.get(position);
        reloadData();
    }

    private void setMapBottom() {
        Log.d(TAG, "setMapBottom");
        if (bottomControlPanel == null) {
            bottomControlPanel = BottomControlPanel.newInstance(TrackActivity.this, this);
        }
        bottomControlPanel.setData(currentData);
        bottomControlPanel.show(rootRL);
        mMapView.getChildAt(2).setPadding(0, 0, 10, DisplayUtil.getScreenHeight(this) / 3);//这是控制缩放控件的位置
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            hideVRMap();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.title_bar_back, R.id.carIV, R.id.myPositionIV, R.id.zoomJiaTV, R.id.zoomJianTV, R.id.tv_replace,
            R.id.tv_land, R.id.tv_jing, R.id.iv_right, R.id.iv_car_expand, R.id.tv_car_status, R.id.tv_all,
            R.id.tv_online, R.id.tv_outline, R.id.tv_drive, R.id.tv_task, R.id.iv_full})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                hideVRMap();
                finish();
                break;
            case R.id.myPositionIV:
                MapStatusUpdate myLocMapStatusUpdate = MapStatusUpdateFactory.newLatLng(myPoint);
                if (mBaiduMap != null && myLocMapStatusUpdate != null) {
                    mBaiduMap.animateMapStatus(myLocMapStatusUpdate);
                }
                break;
            case R.id.carIV:
                if (carPointList != null && carPointList.size() > 0) {
                    MapStatusUpdate carMapStatusUpdate =
                            MapStatusUpdateFactory.newLatLng(carPointList.get(carPointList.size() - 1));
                    if (mBaiduMap != null && carMapStatusUpdate != null) {
                        mBaiduMap.animateMapStatus(carMapStatusUpdate);
                    }
                }else{
                    ToastUtil.showToast("当前车辆无数据。");
                }
                break;
            case R.id.zoomJiaTV:
                this.mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(mBaiduMap.getMapStatus().zoom + 1));
                controlZoomShow();
                break;
            case R.id.zoomJianTV:
                this.mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(mBaiduMap.getMapStatus().zoom - 1));
                controlZoomShow();
                break;
            case R.id.tv_car_status:
                controlCarManager();
                break;
            case R.id.tv_all:
            case R.id.tv_online:
            case R.id.tv_outline:
            case R.id.tv_drive:
            case R.id.tv_task:
                isFillData = false;
                setDefaultColor((TextView) view);
                tv_car_status.setText(((TextView) view).getText().toString());
                tv_car_status.setTag(view.getTag());
                controlCarManager();
                filterCompanyData();
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
                    // 开启交通图
                    mBaiduMap.setTrafficEnabled(true);
                    isTraffic = false;
                } else {
                    // 开启交通图
                    mBaiduMap.setTrafficEnabled(false);
                    isTraffic = true;
                }
                break;
            case R.id.tv_jing:
                if (carPointList != null && carPointList.size() > 0) {
                    if (fl_panorama.isShown()) {
                        hideVRMap();
                    } else {
                        showVRMap(carPointList.get(carPointList.size() - 1).longitude,
                                carPointList.get(carPointList.size() - 1).latitude);
                    }
                }
                break;
            case R.id.iv_right:
//                if (ll_right_panel.isShown()) {
//                    ll_right_panel.setVisibility(View.GONE);
//                } else {
//                    ll_right_panel.setVisibility(View.VISIBLE);
//                    bottomControlPanel.dissmiss();
//                    hideVRMap();
//                    // filterCompanyData();
//                }
                break;
            case R.id.iv_car_expand:
                if (ll_right_panel.isShown()) {
                    String tag = (String) iv_car_expand.getTag();
                    if (tag.equals("0")) {
                        mAdapter.expanAll();
                        iv_car_expand.setTag("1");
                        iv_car_expand.setImageResource(R.mipmap.car_manager_close);
                    } else {
                        isFillData = false;
                        filterCompanyData();
                        iv_car_expand.setTag("0");
                        iv_car_expand.setImageResource(R.mipmap.car_manager_open);
                    }
                }
                break;
            case R.id.iv_full:
                if (isFullScreen) {
                    setMapBottom();
                    fullIV.setImageResource(R.mipmap.icon_full_screen);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fl_panorama.getLayoutParams();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params.height = DeviceUtils.dip2px(this, 250f);
                    fl_panorama.setLayoutParams(params);
                } else {
                    bottomControlPanel.dissmiss();
                    fullIV.setImageResource(R.mipmap.icon_exit_full);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fl_panorama.getLayoutParams();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params.height = LinearLayout.LayoutParams.MATCH_PARENT;
                    fl_panorama.setLayoutParams(params);
                    ll_right_panel.setVisibility(View.GONE);
                }
                isFullScreen = !isFullScreen;
                break;
        }
    }

    /**
     *
     */
    private void setDefaultColor(TextView textView) {
        tv_all.setTextColor(getResources().getColor(R.color.common_black));
        tv_online.setTextColor(getResources().getColor(R.color.common_black));
        tv_outline.setTextColor(getResources().getColor(R.color.common_black));
        tv_drive.setTextColor(getResources().getColor(R.color.common_black));
        tv_task.setTextColor(getResources().getColor(R.color.common_black));
        textView.setTextColor(getResources().getColor(R.color.blue_color1));
    }

    private void changeTextImg(TextView textView, int resourceId) {
        Drawable drawable = getResources().getDrawable(resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     *
     */
    private void controlCarManager() {
        if (ll_status.isShown()) {
            ll_status.setVisibility(View.GONE);
            deptTreeLV.setVisibility(View.VISIBLE);
            changeTextImg(tv_car_status, R.mipmap.arraw_smal_down);
        } else {
            ll_status.setVisibility(View.VISIBLE);
            deptTreeLV.setVisibility(View.GONE);
            changeTextImg(tv_car_status, R.mipmap.arraw_smal_up);
        }

    }

    /**
     * 展示全景地图
     *
     * @param lng
     * @param lat
     */
    public void showVRMap(double lng, double lat) {
        fl_panorama.setVisibility(View.VISIBLE);
        PanoramaView panoramaView = null;
        if (fl_panorama.getChildCount() <= 1) {
            panoramaView = new PanoramaView(this);
            panoramaView.setPanoramaImageLevel(PanoramaView.ImageDefinition.ImageDefinitionMiddle);
            fl_panorama.addView(panoramaView, 0);
        } else {
            panoramaView = (PanoramaView) fl_panorama.getChildAt(0);
        }
        // 测试回调函数,需要注意的是回调函数要在setPanorama()之前调用，否则回调函数可能执行异常
        panoramaView.setPanoramaViewListener(new PanoramaViewListener() {

            @Override
            public void onLoadPanoramaBegin() {
                Log.d("11111111111111","onLoadPanoramaStart...");
            }

            @Override
            public void onLoadPanoramaEnd(String json) {
                Log.d("11111111111111","onLoadPanoramaEnd : " + json);
            }

            @Override
            public void onLoadPanoramaError(String error) {
                Log.d("11111111111111","onLoadPanoramaError : " + error);
            }

            @Override
            public void onDescriptionLoadEnd(String json) {

            }

            @Override
            public void onMessage(String msgName, int msgType) {

            }

            @Override
            public void onCustomMarkerClick(String key) {

            }

            @Override
            public void onMoveStart() {

            }

            @Override
            public void onMoveEnd() {

            }
        });
        panoramaView.setPanorama(lng, lat);
    }
    public void updateVRMap(double lng, double lat) {
        if (fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).setPanorama(lng, lat);
        }
    }

    public void hideVRMap() {
        fl_panorama.setVisibility(View.GONE);
        if (fl_panorama.getChildCount() > 1) {
            fl_panorama.removeViewAt(0);
        }
    }

    private void controlZoomShow() {
        // 获取当前地图状态
        float zoom = this.mBaiduMap.getMapStatus().zoom;
        // 如果当前状态大于等于地图的最大状态，则放大按钮则失效
        if (zoom >= mBaiduMap.getMaxZoomLevel()) {
            zoomJiaTV.setEnabled(false);
        } else {
            zoomJiaTV.setEnabled(true);
        }
        // 如果当前状态小于等于地图的最小状态，则缩小按钮失效
        if (zoom <= mBaiduMap.getMinZoomLevel()) {
            zoomJianTV.setEnabled(false);
        } else {
            zoomJianTV.setEnabled(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMsgWhat()) {
            case MESSAGE_UPDATE_TRACK_VR:
                if (fl_panorama.getVisibility() == View.VISIBLE) {
                    updateVRMap(carPointList.get(carPointList.size() - 1).longitude,
                            carPointList.get(carPointList.size() - 1).latitude);
                }
                break;
        }
    }

    public static void goToPage(Activity activity, String currentData) {
        Intent intent = new Intent(activity, TrackActivity.class);
        intent.putExtra("currentData", currentData);
        activity.startActivity(intent);
    }

    private BaiduMap.OnMarkerClickListener myOnMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            setMapBottom();
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        if (fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        if (fl_panorama.getChildCount() > 1) {
            ((PanoramaView) fl_panorama.getChildAt(0)).onPause();
        }
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        mBaiduMap.clear();
        if(locationUtils!=null){
            locationUtils.stopLocation();
        }
        if (carMoveTimer != null)
            carMoveTimer.cancel();
        if (bottomControlPanel != null)
            bottomControlPanel.dissmiss();
        super.onDestroy();
    }
}
