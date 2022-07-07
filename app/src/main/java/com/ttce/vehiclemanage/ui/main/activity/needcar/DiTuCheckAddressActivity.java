package com.ttce.vehiclemanage.ui.main.activity.needcar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.PoiListAdapter;
import com.ttce.vehiclemanage.utils.KeybordUtil;
import com.ttce.vehiclemanage.utils.MapUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/18.
 */

public class DiTuCheckAddressActivity extends BaseActivity implements OnGetPoiSearchResultListener, AdapterView.OnItemClickListener, PoiListAdapter.OnGetChildrenLocationListener {
    @Bind(R.id.mapview)
    MapView mMapView;
    @Bind(R.id.txt_address)
    TextView txt_address;
    @Bind(R.id.edt_address)
    AutoCompleteTextView mKeyWordsView;
    @Bind(R.id.txt_address1)
    TextView txt_address1;
    @Bind(R.id.txt_address2)
    TextView txt_address2;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.rb)
    CheckBox rb;
    @Bind(R.id.list_view)
    ListView list_view;
    @Bind(R.id.edt_lxdh)
    EditText edt_lxdh;
    @Bind(R.id.edt_xm)
    EditText edt_xm;
    @Bind(R.id.edt_dzpz)
    EditText edt_dzpz;
    @Bind(R.id.lin_bottom)
    LinearLayout lin_bottom;
    @Bind(R.id.real)
    RelativeLayout real;
    private BaiduMap mBaiduMap;
    /**
     * 入口
     *
     * @param activity
     */
    public static void goToPage(Activity activity,String nr,String item,String type) {
        Intent intent = new Intent(activity, DiTuCheckAddressActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("tag",nr);
        bundle.putString("item",item);
        bundle.putString("type",type);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ditu_check_address;
    }

    String nr,items,type;
    EmptyOrderBean.OrderMarkListBean item;
    @Override
    public void initView() {
        mBaiduMap = mMapView.getMap();
        nr=this.getIntent().getExtras().getString("tag");
        items=this.getIntent().getExtras().getString("item");
        type=this.getIntent().getExtras().getString("type");
        if(nr.equals("途径地")){
            rb.setVisibility(View.GONE);
        }else{
            //TODO 二期开发
            rb.setVisibility(View.GONE);
            rb.setText("设为默认"+nr);
        }
        if(!items.equals("")){//反射数据
            item=new Gson().fromJson(items, EmptyOrderBean.OrderMarkListBean.class);
            txt_address1.setText(item.getNeedMarkSimpleAddress()==null||item.getNeedMarkSimpleAddress().equals("")?"":item.getNeedMarkSimpleAddress());
            txt_address2.setText(item.getNeedMarkFullAddress()==null||item.getNeedMarkFullAddress().equals("")?"":item.getNeedMarkFullAddress());
            edt_dzpz.setText(item.getNeedMarkTitle()==null||item.getNeedMarkTitle().equals("")?"":item.getNeedMarkTitle());
            edt_xm.setText(item.getLinkName()==null||item.getLinkName().equals("")?"":item.getLinkName());
            edt_lxdh.setText(item.getLinkPhone()==null||item.getLinkPhone().equals("")?"":item.getLinkPhone());
            newLatLng= new LatLng(item.getNeedMarkLat(),item.getNeedMarkLng());
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(newLatLng, 18);
            mBaiduMap.setMapStatus(mapStatusUpdate);
        }

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        titleBarTitle.setText("添加地址");

        if(items.equals("")||item.getNeedMarkSimpleAddress()==null||item.getNeedMarkSimpleAddress().equals("")){
            requestPermission();
        }

        //TODO
        // 创建poi检索实例，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        list_view.setOnItemClickListener(this);
        // 当输入关键字变化时，动态更新建议列表
        mKeyWordsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    return;
                }
                //TODO  城市poi搜索功能
                searchButtonProcess(cs.toString());
            }
        });
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
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
                LatLng ptCenter = mapStatus.target;
                newLatLng=ptCenter;
                // 反Geo搜索
                setAds(ptCenter,"map");
            }
        });
    }

    @OnClick({R.id.title_bar_back,R.id.tv_dtxz,R.id.tv_sub,R.id.tv_address_book,R.id.views})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.views:
                list_view.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                break;
            case R.id.tv_address_book:
                EventBus.getDefault().postSticky(new MessageBean("途径地",nr));
                AddressBookActivity.goToPage(this);
                break;
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_dtxz:
                EventBus.getDefault().postSticky(new MessageBean("途径地",nr));
                Intent intent=new Intent(this,MapPlaceChooseActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sub:
                    EmptyOrderBean.OrderMarkListBean markBean=new EmptyOrderBean.OrderMarkListBean();
                    markBean.setNeedMarkTitle(edt_dzpz.getText().toString().trim());
                    markBean.setNeedMarkSimpleAddress(txt_address1.getText().toString().trim());
                    markBean.setNeedMarkFullAddress(txt_address2.getText().toString().trim());
                    markBean.setNeedMarkLat(newLatLng.latitude);
                    markBean.setNeedMarkLng(newLatLng.longitude);
                    markBean.setLinkName(edt_xm.getText().toString().trim());
                    markBean.setLinkPhone(edt_lxdh.getText().toString().trim());
                    markBean.setMarkTypeFormat(type);
                    EventBus.getDefault().postSticky(markBean);
                    finish();
                break;
        }
    }

    LatLng newLatLng;
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

        //定位初始化
        try {
            /**
             * 定位SDK是否同意隐私政策接口
             * false不同意：不支持定位，SDK抛出异常
             * true同意：支持定位功能
             */
            LocationClient.setAgreePrivacy(true);
            mLocationClient = new LocationClient(getApplicationContext());
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
            txt_address.setText(location.getCity());

            if (isFirstLoc) {
                isFirstLoc = false;
                // 设置初始中心点为北京
                final LatLng center = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(center, 18);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                txt_address2.setText(location.getAddrStr());
                txt_address1.setText(location.getLocationDescribe());
                newLatLng=center;
            }
        }

    }
    @Override
    public void initPresenter() {
    }

    //TODO  城市poi搜索功能
    /**
     * 获取城市poi检索结果
     *
     * @param result poi查询结果
     */
    @Override
    public void onGetPoiResult(final PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            list_view.setVisibility(View.GONE);
            real.setVisibility(View.GONE);
            mLoadIndex = 0;
            mBaiduMap.clear();
            ToastUtil.showToast("未找到结果");
            return;
        }

        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            list_view.setVisibility(View.VISIBLE);
            real.setVisibility(View.VISIBLE);
            mBaiduMap.clear();
            // 获取poi结果
            mAllPoi = result.getAllPoi();
            PoiListAdapter poiListAdapter = new PoiListAdapter(this, mAllPoi,mKeyWordsView.getText().toString().trim());
            poiListAdapter.setOnGetChildrenLocationListener(this);
            // 把poi结果添加到适配器
            list_view.setAdapter(poiListAdapter);
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) list_view.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = DisplayUtil.getScreenHeight(this) / 3 * 2;// 控件的宽强制设成30
            list_view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
//            setListViewHeight(list_view,4);
            return;
        }

        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";

            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            ToastUtil.showToast(strInfo);
        }
    }

    private List<PoiInfo> mAllPoi;
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    /**
     * poilist 点击处理
     *
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PoiInfo poiInfo = mAllPoi.get(position);
        if (poiInfo.getLocation() == null) {
            return;
        }
        txt_address2.setText(poiInfo.getAddress());
        txt_address1.setText(poiInfo.getName());
//        list_view.setVisibility(View.GONE);
        real.setVisibility(View.GONE);
        newLatLng=poiInfo.getLocation();

        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(poiInfo.getLocation(), 18);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        //  按搜索按钮时隐藏软件盘，为了在结果回调时计算 PoiDetailView 控件的高度，把地图中poi展示到合理范围内
        KeybordUtil.closeKeybord(this);
    }

    /**
     * 点击子节点list 获取经纬添加poi更新地图
     *
     */
    @Override
    public void getChildrenLocation(PoiChildrenInfo poiInfo) {
        setAds(poiInfo.getLocation(),"search");
        newLatLng=poiInfo.getLocation();
        txt_address1.setText(poiInfo.getName());
//        list_view.setVisibility(View.GONE);
        real.setVisibility(View.GONE);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(poiInfo.getLocation(), 18);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        //  按搜索按钮时隐藏软件盘，为了在结果回调时计算 PoiDetailView 控件的高度，把地图中poi展示到合理范围内
        KeybordUtil.closeKeybord(this);
    }


    private static final int REQUEST_CODE_CAMERA = 1;
    private LocationClient mLocationClient;
    boolean isLocationClientStop = false;
    boolean isFirstLoc = true;
    private SuggestionSearch mSuggestionSearch = null;

    //TODO
    // 分页
    private int mLoadIndex = 0;
    private PoiSearch mPoiSearch = null;
    /**
     * 下一页
     */
    public void goToNextPage(View v) {
        mLoadIndex++;
        searchButtonProcess(mKeyWordsView.getText().toString());
    }

    private void searchButtonProcess(String edittext) {
        // 发起请求
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(txt_address.getText().toString().trim())
                .keyword(edittext)
                .pageNum(mLoadIndex) // 分页编号
                .cityLimit(false)
                .scope(2));
    }

    private void setAds(LatLng ll,String search) {
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
                    if(!search.equals("search")){
                        if(reverseGeoCodeResult!=null&&reverseGeoCodeResult.getPoiList()!=null&&reverseGeoCodeResult.getPoiList().size()>0){
                            if(reverseGeoCodeResult.getPoiList().get(0).getName()!=null&&!reverseGeoCodeResult.getPoiList().get(0).getName().equals("")&&txt_address1!=null){
                                txt_address1.setText(reverseGeoCodeResult.getPoiList().get(0).getName());
                            }
                        }
                    }
                    if(reverseGeoCodeResult!=null&&reverseGeoCodeResult.getAddress()!=null&&!reverseGeoCodeResult.getAddress().equals("")&&txt_address2!=null){
                        txt_address2.setText(reverseGeoCodeResult.getAddress());
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时必须调用mMapView. onPause ()
        mMapView.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PoiInfo reverseGeoCodeResult) {
       txt_address1.setText(reverseGeoCodeResult.getName());
        newLatLng=reverseGeoCodeResult.getLocation();
        setAds(reverseGeoCodeResult.getLocation(),"search");
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(reverseGeoCodeResult.getLocation(), 18);
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO 释放检索对象
        mPoiSearch.destroy();

        if(null !=mSuggestionSearch){
            mSuggestionSearch.destroy();
        }
        // 清除所有图层
        mBaiduMap.clear();
        // 在activity执行onDestroy时必须调用mMapView. onDestroy ()
        EventBus.getDefault().unregister(this);
        //清空粘性事件的缓存
        EventBus.getDefault().removeAllStickyEvents();
        if (null != mMapView) {
            mMapView.onDestroy();
        }
    }
}