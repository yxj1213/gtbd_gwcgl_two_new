package com.ttce.vehiclemanage.ui.main.activity.my.order;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.imagePager.BigImagePagerActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 * */
public class DaKaDetailsActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_address)
    TextView tv_address;
    @Bind(R.id.edt_tjdmc)
    TextView edt_tjdmc;
    @Bind(R.id.mmapview)
    MapView mmapview;
    @Bind(R.id.recycler_view)
    IRecyclerView recycler_view;
    @Bind(R.id.tv_dala_class)
    TextView tv_dala_class;
    @Bind(R.id.tv_bt)
    TextView tv_bt;
    @Bind(R.id.tv_sy)
    TextView tv_sy;
    @Bind(R.id.tv_cjsj)
    TextView tv_cjsj;

    BaiduMap mBaiduMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_daka_details;
    }

    @Override
    public void initPresenter() {

    }
    MarkerDetailsBean item;
    @Override
    public void initView() {
        mBaiduMap=mmapview.getMap();


        titleBarTitle.setText(getResources().getString(R.string.me_txt10));
        String tjd=this.getIntent().getStringExtra("tjd");
        if(!tjd.equals("")){
            item=new Gson().fromJson(tjd, MarkerDetailsBean.class);
            edt_tjdmc.setText(item.getNeedMarkTitle());
            tv_address.setText(item.getRealMarkFullAddress());
            LatLng latLng=new LatLng(item.getRealMarkLat(),item.getRealMarkLng());
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 13);
            mBaiduMap.setMapStatus(mapStatusUpdate);
            createCenterMarker(latLng);
            if (item.getMarkType()==10) {//途经打卡
                tv_dala_class.setText("途经打卡");
            } else if (item.getMarkType()==20) {  //新增打卡
                tv_dala_class.setText("新增打卡");
            }
            tv_bt.setText(item.getRemarkTitle());
            tv_sy.setText(item.getRemarkContent());
            tv_cjsj.setText(item.getRealMarkTimeFormat());
        }
        tuPianData();
    }
    List<WorkBeanchBean> scrymlist;
    private void tuPianData() {

        scrymlist=new ArrayList<>();
        if(item.getCarFlow_Order_Mark_Image_List()!=null&&item.getCarFlow_Order_Mark_Image_List().size()>0){
            for (MarkerDetailsBean.CarFlowOrderMarkImageListBean str:item.getCarFlow_Order_Mark_Image_List()) {
                scrymlist.add(new WorkBeanchBean(str.getMarkImageUrl()));
            }
        }
        WorkBeanchAdapter scryworkBeanchAdapter = new WorkBeanchAdapter(this, R.layout.pic_item, scrymlist,"打卡详情");
        scryworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_view.setAdapter(scryworkBeanchAdapter);
        scryworkBeanchAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                ArrayList<String> mlist=new ArrayList<>();
                for (int i=0;i<scrymlist.size();i++) {
                    mlist.add(scrymlist.get(i).getTitle());
                }
                BigImagePagerActivity.startImagePagerActivity(DaKaDetailsActivity.this,mlist,position-1);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }
    public static final int REQUEST_CODE_PREVIEW = 101;
    /**
     * 创建地图中心点marker
     */
    private void createCenterMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.water_drop))
                .zIndex(9)
                .draggable(true);
        mBaiduMap.addOverlay(markerOptions);
    }
    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }

    public static void goToPage(Activity activity,String tjd) {
        Intent intent = new Intent(activity, DaKaDetailsActivity.class);
        intent.putExtra("tjd",tjd);
        activity.startActivity(intent);
    }
}