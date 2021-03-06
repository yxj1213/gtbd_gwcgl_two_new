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
     * ??????
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
        if(nr.equals("?????????")){
            rb.setVisibility(View.GONE);
        }else{
            //TODO ????????????
            rb.setVisibility(View.GONE);
            rb.setText("????????????"+nr);
        }
        if(!items.equals("")){//????????????
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
        titleBarTitle.setText("????????????");

        if(items.equals("")||item.getNeedMarkSimpleAddress()==null||item.getNeedMarkSimpleAddress().equals("")){
            requestPermission();
        }

        //TODO
        // ??????poi???????????????????????????????????????
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        list_view.setOnItemClickListener(this);
        // ??????????????????????????????????????????????????????
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
                //TODO  ??????poi????????????
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
                // ???Geo??????
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
                EventBus.getDefault().postSticky(new MessageBean("?????????",nr));
                AddressBookActivity.goToPage(this);
                break;
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_dtxz:
                EventBus.getDefault().postSticky(new MessageBean("?????????",nr));
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
                //????????????
                initLocation();
            }else{
                //???????????????
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
                    Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    private void initLocation() {

        //???????????????
        try {
            /**
             * ??????SDK??????????????????????????????
             * false??????????????????????????????SDK????????????
             * true???????????????????????????
             */
            LocationClient.setAgreePrivacy(true);
            mLocationClient = new LocationClient(getApplicationContext());
            //??????LocationClientOption??????LocationClient????????????
            LocationClientOption option = new
                    LocationClientOption();
            option.setOpenGps(true); // ??????gps
            option.setCoorType("bd09ll"); // ??????????????????
            option.setScanSpan(1000);
            option.setIsNeedLocationDescribe(true);
            option.setIsNeedAddress(true); // ?????????????????????????????????????????????????????????
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
                // ??????????????????????????????
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

    //TODO  ??????poi????????????
    /**
     * ????????????poi????????????
     *
     * @param result poi????????????
     */
    @Override
    public void onGetPoiResult(final PoiResult result) {
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            list_view.setVisibility(View.GONE);
            real.setVisibility(View.GONE);
            mLoadIndex = 0;
            mBaiduMap.clear();
            ToastUtil.showToast("???????????????");
            return;
        }

        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            list_view.setVisibility(View.VISIBLE);
            real.setVisibility(View.VISIBLE);
            mBaiduMap.clear();
            // ??????poi??????
            mAllPoi = result.getAllPoi();
            PoiListAdapter poiListAdapter = new PoiListAdapter(this, mAllPoi,mKeyWordsView.getText().toString().trim());
            poiListAdapter.setOnGetChildrenLocationListener(this);
            // ???poi????????????????????????
            list_view.setAdapter(poiListAdapter);
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) list_view.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
            linearParams.height = DisplayUtil.getScreenHeight(this) / 3 * 2;// ????????????????????????30
            list_view.setLayoutParams(linearParams); //??????????????????????????????????????????
//            setListViewHeight(list_view,4);
            return;
        }

        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
            String strInfo = "???";

            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "????????????";
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
     * poilist ????????????
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

        //  ?????????????????????????????????????????????????????????????????? PoiDetailView ??????????????????????????????poi????????????????????????
        KeybordUtil.closeKeybord(this);
    }

    /**
     * ???????????????list ??????????????????poi????????????
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

        //  ?????????????????????????????????????????????????????????????????? PoiDetailView ??????????????????????????????poi????????????????????????
        KeybordUtil.closeKeybord(this);
    }


    private static final int REQUEST_CODE_CAMERA = 1;
    private LocationClient mLocationClient;
    boolean isLocationClientStop = false;
    boolean isFirstLoc = true;
    private SuggestionSearch mSuggestionSearch = null;

    //TODO
    // ??????
    private int mLoadIndex = 0;
    private PoiSearch mPoiSearch = null;
    /**
     * ?????????
     */
    public void goToNextPage(View v) {
        mLoadIndex++;
        searchButtonProcess(mKeyWordsView.getText().toString());
    }

    private void searchButtonProcess(String edittext) {
        // ????????????
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(txt_address.getText().toString().trim())
                .keyword(edittext)
                .pageNum(mLoadIndex) // ????????????
                .cityLimit(false)
                .scope(2));
    }

    private void setAds(LatLng ll,String search) {
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
        // ???activity??????onResume???????????????mMapView. onResume ()
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ???activity??????onPause???????????????mMapView. onPause ()
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
        //TODO ??????????????????
        mPoiSearch.destroy();

        if(null !=mSuggestionSearch){
            mSuggestionSearch.destroy();
        }
        // ??????????????????
        mBaiduMap.clear();
        // ???activity??????onDestroy???????????????mMapView. onDestroy ()
        EventBus.getDefault().unregister(this);
        //???????????????????????????
        EventBus.getDefault().removeAllStickyEvents();
        if (null != mMapView) {
            mMapView.onDestroy();
        }
    }
}