package com.ttce.vehiclemanage.ui.main.activity.my.order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.bean.WorkBeanchBean;
import com.ttce.vehiclemanage.ui.main.activity.needcar.DiTuCheckAddressActivity;
import com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu.CheckCarActivity;
import com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu.DispatchCarActivity;
import com.ttce.vehiclemanage.ui.main.adapter.WorkBeanchAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.me.order.CarRecordsAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.me.order.MyOrderListAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.TuJingDiAdapter;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyOrderConstract;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyOrderModel;
import com.ttce.vehiclemanage.ui.main.popwindow.DrivingRouteOverlay;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyOrderPresenter;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;
import com.ttce.vehiclemanage.utils.AlertDialogUtils;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.SizeLabel;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.MaxRecyclerView;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment.isRef;

/**
 * ?????????
 */
public class MyOrderDetailsActivity extends BaseActivity<MyOrderPresenter, MyOrderModel> implements MyOrderConstract.View, TuJingDiAdapter.PushclickListener, OnGetRoutePlanResultListener, AlertDialogUtils.DialogDataListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.title)
    RelativeLayout title_bar_layout;
    @Bind(R.id.recycler_view_ddzz)
    MaxRecyclerView recycler_view_ddzz;
    @Bind(R.id.recycler_view_tjd)
    MaxRecyclerView recycler_view_tjd;
    @Bind(R.id.recycler_view_scry)
    MaxRecyclerView recycler_view_scry;
    @Bind(R.id.recycler_view_ycjl)
    MaxRecyclerView recycler_view_ycjl;
    @Bind(R.id.tv_scry_num)
    TextView tv_scry_num;
    @Bind(R.id.tv_ddzz_num)
    TextView tv_ddzz_num;
    @Bind(R.id.stateTV)
    TextView stateTV;
    @Bind(R.id.tv_gdxx)
    TextView tv_gdxx;
    @Bind(R.id.lin_gdxx)
    LinearLayout lin_gdxx;
    @Bind(R.id.lin_ycxx_details)
    LinearLayout lin_ycxx_details;
    @Bind(R.id.txt_ycxx)
    TextView txt_ycxx;
    @Bind(R.id.txt_ycjl)
    TextView txt_ycjl;
    @Bind(R.id.txt_end)
    TextView txt_end;
    @Bind(R.id.txt_start)
    TextView txt_start;
    @Bind(R.id.txt_zk)
    TextView txt_zk;
    @Bind(R.id.txt_tjd_num)
    TextView txt_tjd_num;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;
    @Bind(R.id.tv_center)
    TextView tv_center;
    @Bind(R.id.view)
    View view1;
    @Bind(R.id.view2)
    View view2;
    @Bind(R.id.map)
    MapView map;
    @Bind(R.id.lin_tjd)
    LinearLayout lin_tjd;
    @Bind(R.id.lin_all_bottom)
    NestedScrollView lin_all_bottom;


    @Bind(R.id.rel_jsr)
    RelativeLayout rel_jsr;
    @Bind(R.id.rel_cph)
    RelativeLayout rel_cph;
    @Bind(R.id.rel_cx)
    RelativeLayout rel_cx;
    @Bind(R.id.view_cph)
    View view_cph;
    @Bind(R.id.view_cx)
    View view_cx;
    @Bind(R.id.view_jsr)
    View view_jsr;

    @Bind(R.id.tv_ycr_vaule)
    TextView tv_ycr_vaule;
    @Bind(R.id.tv_yx_jsr_vaule)
    TextView tv_yx_jsr_vaule;
    @Bind(R.id.tv_sj_jsr_vaule)
    TextView tv_sj_jsr_vaule;
    @Bind(R.id.tv_kssj_vaule)
    TextView tv_kssj_vaule;
    @Bind(R.id.tv_jssj_vaule)
    TextView tv_jssj_vaule;
    @Bind(R.id.tv_yclx_vaule)
    TextView tv_yclx_vaule;
    @Bind(R.id.tv_sj_cx_vaule)
    TextView tv_sj_cx_vaule;
    @Bind(R.id.tv_sj_cph_vaule)
    TextView tv_sj_cph_vaule;
    @Bind(R.id.tv_yx_cx_vaule)
    TextView tv_yx_cx_vaule;
    @Bind(R.id.tv_yx_cph_vaule)
    TextView tv_yx_cph_vaule;


    @Bind(R.id.tv_hplx_vaule)
    TextView tv_hplx_vaule;
    @Bind(R.id.ddbhTV)
    TextView ddbhTV;
    @Bind(R.id.tv_cjsj)
    TextView tv_cjsj;
    @Bind(R.id.tv_fyzj)
    TextView tv_fyzj;
    @Bind(R.id.tv_ycsc)
    TextView tv_ycsc;
    @Bind(R.id.tv_yclc)
    TextView tv_yclc;
    @Bind(R.id.tv_ycsy_bt_vaule)
    TextView tv_ycsy_bt_vaule;
    @Bind(R.id.tv_ycsy_nr_vaule)
    TextView tv_ycsy_nr_vaule;
    @Bind(R.id.tv_sx_tjd)
    TextView tv_sx_tjd;
    @Bind(R.id.fra_bottom_sure_cancel)
    ShadowLayout fra_bottom_sure_cancel;
    @Bind(R.id.lin_tjddk)
    LinearLayout lin_tjddk;
    @Bind(R.id.tv_xzdk)
    TextView tv_xzdk;
    @Bind(R.id.tv_add_tjd)
    TextView tv_add_tjd;


    @Bind(R.id.tv_zwyclx)
    TextView tv_zwyclx;
    @Bind(R.id.tv_top)
    TextView tv_top;
    @Bind(R.id.img_bottom)
    ImageView img_bottom;
    @Bind(R.id.lin_qi_zhong_dian)
    LinearLayout lin_qi_zhong_dian;
    @Bind(R.id.lin_top)
    ConstraintLayout lin_top;
    @Bind(R.id.bottom_sheet)
    ConstraintLayout bottom_sheet;


    @Bind(R.id.rel_end)
    RelativeLayout rel_end;
    WorkBeanchAdapter ddzzworkBeanchAdapter;
    WorkBeanchAdapter scryworkBeanchAdapter;
    CarRecordsAdapter carRecordsBeanAdapter;
    TuJingDiAdapter tuJingDiAdapter;
    BaiduMap mBaiduMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    String groupState;
    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mBaiduMap = map.getMap();
        map.getChildAt(2).setPadding(0, 0, 0, DisplayUtil.dip2px(70));//?????????????????????????????????

        titleBarTitle.setText(getResources().getString(R.string.me_txt0));


        tv_cancle.setVisibility(View.GONE);
        tv_center.setVisibility(View.GONE);
        tv_sure.setVisibility(View.GONE);


        setScrollview(true);
        lin_all_bottom.setNestedScrollingEnabled(false);
        //???????????????????????????
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    //??????????????????
                    case BottomSheetBehavior.STATE_EXPANDED:
                        lin_all_bottom.setNestedScrollingEnabled(true);
                        setScrollview(false);
                        break;
                    //????????????
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    //????????????
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        lin_all_bottom.setNestedScrollingEnabled(false);
                        setScrollview(true);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
    BottomSheetBehavior mBehavior;
    String orderId;
    @Override
    protected void onResume() {
        super.onResume();
        orderId = this.getIntent().getStringExtra("orderId");
        groupState = this.getIntent().getStringExtra("groupState");
        mPresenter.getMyOrderDetailsBeans(orderId,groupState);
        /**
         *  ???????????? = 1000  ????????? 1000   ?????????  1100  ?????????  1200  ?????????  1400  ?????????  1500
         *   ???????????? = 2000  ????????? 10    ????????? 12   ????????? 90
         *   ???????????? = 3000   ????????? 20    ????????? 22   ????????? 90
         *   ???????????? = 4000
         * */
        if(groupState.equals(getResources().getString(R.string.type2))){
            txt_ycjl.setText("????????????");
        }else{
            txt_ycjl.setText("????????????");
        }

    }
    boolean isXuanZe;
    private void tjdData(EmptyOrderBean emptyOrderBean,boolean isShow) {
        isXuanZe=memptyOrderBean.isUseMapPoint();
        //????????????????????????
        String qidian_jiancheng = "", qidian_quancheng = "", qidian_pizhu = "",qidian_lxxx="";
        String zhongdian_jiancheng = "", zhongdian_quancheng = "", zhongdian_pizhu = "",zhongdian_lxxx="";


        String html="";
        if(emptyOrderBean.getOrder_Mark_List()!=null&&emptyOrderBean.getOrder_Mark_List().size()>0) {
            lin_qi_zhong_dian.setVisibility(View.VISIBLE);
            tv_zwyclx.setVisibility(View.GONE);
            qidian_jiancheng = !emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkSimpleAddress().equals("") ? emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkSimpleAddress() : "";
            qidian_quancheng = !emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkFullAddress().equals("") ? emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkFullAddress() : "";
            qidian_pizhu= !emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkTitle().equals("") ? emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkTitle() : "";
            qidian_lxxx =!emptyOrderBean.getOrder_Mark_List().get(0).getLinkName().equals("")||!emptyOrderBean.getOrder_Mark_List().get(0).getLinkPhone().equals("")?emptyOrderBean.getOrder_Mark_List().get(0).getLinkName() + " " + emptyOrderBean.getOrder_Mark_List().get(0).getLinkPhone() : "";
            if (isXuanZe==true) {//??????????????????????????????
                if(!qidian_pizhu.equals("")&&qidian_lxxx.equals("")&&qidian_jiancheng.equals("")&&qidian_quancheng.equals("")){
                    html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_pizhu + "</strong></body></html>";
                }else if(!qidian_pizhu.equals("")&&!qidian_lxxx.equals("")){
                    html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + qidian_quancheng + "<br />" + qidian_pizhu+ "<br />"+ qidian_lxxx+ "</size></font></body></html>";
                }else if(qidian_pizhu.equals("")&&qidian_lxxx.equals("")){
                    html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + qidian_quancheng+ "</size></font></body></html>";
                }else if(qidian_lxxx.equals("")&&!qidian_pizhu.equals("")){
                    html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + qidian_quancheng + "<br />"+ qidian_pizhu+ "</size></font></body></html>";
                }else if(!qidian_lxxx.equals("")&&qidian_pizhu.equals("")){
                    html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+ qidian_quancheng + "<br />" + qidian_lxxx+ "</size></font></body></html>";
                }
            } else if (isXuanZe==false) {
                if(emptyOrderBean.isHasMark()==false){//?????????????????????????????????
                    initGeoCoder(new LatLng(emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLat(), emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLng()),"??????");
                    rel_end.setVisibility(View.GONE);
                }else{//????????????????????????(?????????????????????)

                    if(!qidian_quancheng.equals("")&&!qidian_pizhu.equals("")){
                        html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_quancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+ qidian_pizhu+"</size></font></body></html>";
                    }else if(!qidian_quancheng.equals("")&&qidian_pizhu.equals("")){
                        html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_quancheng + "</strong></body></html>";
                    }else if(qidian_quancheng.equals("")&&!qidian_pizhu.equals("")){
                        html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_pizhu + "</strong></body></html>";
                    }
                }
            }
            txt_start.setText(Html.fromHtml(html, null, new SizeLabel(12, mContext)));

            String html2 = "";
            zhongdian_jiancheng = !emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkSimpleAddress().equals("") ? emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkSimpleAddress() : "";
            zhongdian_quancheng = !emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkFullAddress().equals("") ? emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkFullAddress() : "";
            zhongdian_pizhu= !emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkTitle().equals("") ? emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkTitle() : "";
            zhongdian_lxxx =!emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getLinkName().equals("")||!emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getLinkPhone().equals("")?emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getLinkName() + " " + emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getLinkPhone() : "";
            if (isXuanZe==true) {//??????????????????????????????
                if(!zhongdian_pizhu.equals("")&&zhongdian_lxxx.equals("")&&zhongdian_jiancheng.equals("")&&zhongdian_quancheng.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_pizhu + "</strong></body></html>";
                }else if(!zhongdian_pizhu.equals("")&&!zhongdian_lxxx.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + zhongdian_quancheng + "<br />" + zhongdian_pizhu+ "<br />"+ zhongdian_lxxx+ "</size></font></body></html>";
                }else if(zhongdian_pizhu.equals("")&&zhongdian_lxxx.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + zhongdian_quancheng+ "</size></font></body></html>";
                }else if(zhongdian_lxxx.equals("")&&!zhongdian_pizhu.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + zhongdian_quancheng + "<br />"+ zhongdian_pizhu+ "</size></font></body></html>";
                }else if(!zhongdian_lxxx.equals("")&&zhongdian_pizhu.equals("")){
                    html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_jiancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+ zhongdian_quancheng + "<br />" + zhongdian_lxxx+ "</size></font></body></html>";
                }
            } else if (isXuanZe==false) {//????????????????????????
                if(emptyOrderBean.isHasMark()==false) {//?????????????????????????????????
                    if(emptyOrderBean.getOrderState()==180){
                        rel_end.setVisibility(View.VISIBLE);
                        initGeoCoder(new LatLng(emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getRealMarkLat(), emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getRealMarkLng()),"??????");
                    }
                }else{
                    if(!zhongdian_quancheng.equals("")&&!zhongdian_pizhu.equals("")){
                        html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + qidian_quancheng + "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>"+ qidian_pizhu+"</size></font></body></html>";
                    }else if(!zhongdian_quancheng.equals("")&&zhongdian_pizhu.equals("")){
                        html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_quancheng + "</strong></body></html>";
                    }else if(zhongdian_quancheng.equals("")&&!zhongdian_pizhu.equals("")){
                        html2 = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + zhongdian_pizhu + "</strong></body></html>";
                    }
                }
            }
            txt_end.setText(Html.fromHtml(html2, null, new SizeLabel(12, mContext)));

            if(isXuanZe==true){
                //??????????????????
                tjdAdapterList = new ArrayList<>();
                for (int i = 1; i < emptyOrderBean.getOrder_Mark_List().size() - 1; i++) {
                    tjdAdapterList.add(emptyOrderBean.getOrder_Mark_List().get(i));
                }
            }else if (isXuanZe==false) {//????????????????????????
                //??????????????????
                tjdAdapterList = new ArrayList<>();
                if(emptyOrderBean.isHasMark()==false){//???????????????????????????????????????????????????????????????????????????
                    if(emptyOrderBean.getOrderState()==180){
                        for (int i = 1; i < emptyOrderBean.getOrder_Mark_List().size() - 1; i++) {
                            tjdAdapterList.add(emptyOrderBean.getOrder_Mark_List().get(i));
                        }
                    }else{
                         for (int i = 1; i < emptyOrderBean.getOrder_Mark_List().size(); i++) {
                            tjdAdapterList.add(emptyOrderBean.getOrder_Mark_List().get(i));
                        }
                    }

                }else{
                    for (int i = 1; i < emptyOrderBean.getOrder_Mark_List().size()-1; i++) {
                        tjdAdapterList.add(emptyOrderBean.getOrder_Mark_List().get(i));
                    }
                }
            }
            if (tjdAdapterList.size() == 0) {
                lin_tjd.setVisibility(View.GONE);
            } else {
                lin_tjd.setVisibility(View.VISIBLE);
                txt_tjd_num.setText(tjdAdapterList.size() + "????????????");
            }


            tuJingDiAdapter = new TuJingDiAdapter(this, R.layout.myorderdetails_tjd_item, tjdAdapterList, this,isXuanZe,isShow);
            tuJingDiAdapter.openLoadAnimation(new ScaleInAnimation());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    // ????????????????????????
                    return false;
                }
            };
            recycler_view_tjd.setNestedScrollingEnabled(false);//????????????
            recycler_view_tjd.setLayoutManager(layoutManager);
            recycler_view_tjd.setAdapter(tuJingDiAdapter);



            //?????????????????????????????????????????????
            mSearch = RoutePlanSearch.newInstance();
            mSearch.setOnGetRoutePlanResultListener(this);
            LatLng startpoint = null;
            LatLng endpoint = null;
            if (isXuanZe==true) {//??????????????????????????????
                startpoint = new LatLng(emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLat(), emptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLng());
                endpoint= new LatLng(emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkLat(), emptyOrderBean.getOrder_Mark_List().get(emptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkLng());
                guiHuaLuXian(startpoint, endpoint, tjdAdapterList);
                if(memptyOrderBean.getOrderState()==170||memptyOrderBean.getOrderState()==180){
                    List<EmptyOrderBean.OrderMarkListBean> shijimark=new ArrayList<>();
                    for (EmptyOrderBean.OrderMarkListBean orderMarkListBean:emptyOrderBean.getOrder_Mark_List() ) {
                        if(orderMarkListBean.isMark()==true){
                            shijimark.add(orderMarkListBean);
                        }
                    }
                    shiJiLuXian(shijimark,memptyOrderBean.getOrderState());
                }
            } else if (isXuanZe==false) {//????????????????????????
               if(emptyOrderBean.isHasMark()==false){
                   /**
                    * ?????????????????????????????? ?????????????????????????????????????????????1???????????????????????????????????????????????????????????????
                    * */
                   List<EmptyOrderBean.OrderMarkListBean> shijimark=new ArrayList<>();
                   for (EmptyOrderBean.OrderMarkListBean orderMarkListBean:emptyOrderBean.getOrder_Mark_List()) {
                       if(orderMarkListBean.isMark()==true){
                           shijimark.add(orderMarkListBean);
                       }
                   }
                   if(shijimark.size()==1){
                       startpoint = new LatLng(emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLat(), emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLng());
                       OverlayOptions startOptions = new MarkerOptions()
                               .position(startpoint).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_begin))
                               .zIndex(9).draggable(true);
                       mBaiduMap.addOverlay(startOptions);
                       MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(startpoint, 13);
                       mBaiduMap.setMapStatus(mapStatusUpdate);
                   }else{
                       if(memptyOrderBean.getOrderState()==170||memptyOrderBean.getOrderState()==180){
                           shiJiLuXian(shijimark,memptyOrderBean.getOrderState());
                       }
                   }
               }else{
                   /**
                    * ????????????????????????????????????????????????????????????  ?????????????????????????????????????????????1???????????????????????????????????????????????????????????????
                    * */
                   List<EmptyOrderBean.OrderMarkListBean> shijimark=new ArrayList<>();
                   for (EmptyOrderBean.OrderMarkListBean orderMarkListBean:emptyOrderBean.getOrder_Mark_List() ) {
                       if(orderMarkListBean.isMark()==true){
                           shijimark.add(orderMarkListBean);
                       }
                   }
                   if(shijimark.size()==1){
                       startpoint = new LatLng(emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLat(), emptyOrderBean.getOrder_Mark_List().get(0).getRealMarkLng());
                       OverlayOptions startOptions = new MarkerOptions()
                               .position(startpoint).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_begin))
                               .zIndex(9).draggable(true);
                       mBaiduMap.addOverlay(startOptions);
                       MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(startpoint, 13);
                       mBaiduMap.setMapStatus(mapStatusUpdate);
                   }else{
                       if(memptyOrderBean.getOrderState()==170||memptyOrderBean.getOrderState()==180){
                           shiJiLuXian(shijimark,memptyOrderBean.getOrderState());
                       }
                   }
               }
            }


        }else{
            lin_qi_zhong_dian.setVisibility(View.GONE);
            tv_zwyclx.setVisibility(View.VISIBLE);
        }

         if(groupState.equals(getResources().getString(R.string.type1))){
             if(memptyOrderBean.getOrderState()==170){//?????????
                 beginRefreshTimer();
             }else{
                 if(sendMsgTimer!=null){
                     sendMsgTimer.cancel();
                 }
             }
         }
        if(groupState.equals(getResources().getString(R.string.type4))){
            if(memptyOrderBean.getOrderState()>=150&&memptyOrderBean.getOrderState()<180){//??????????????????????????????????????????
                beginRefreshTimer();
            }else{
                if(sendMsgTimer!=null){
                    sendMsgTimer.cancel();
                }
                if(memptyOrderBean.getOrderState()==180){//?????????
                    fra_bottom_sure_cancel.setVisibility(View.GONE);
                }
            }
        }

        lin_top.setPadding(0,0,0,DisplayUtil.getWidgetHeight(bottom_sheet)-DisplayUtil.dip2px(100));
    }

    public void initGeoCoder(LatLng latLng,String type) {
            GeoCoder mCoder = GeoCoder.newInstance();
            mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                    if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        if(type.equals("??????")){
                            txt_start.setText("??????????????????");
                        }else{
                            txt_end.setText("??????????????????");
                        }
                    } else {
                        //????????????
                        String html = "<html><strong><body style='font-size:14px;color:#272727' family='PingFang-SC-Medium'>" + reverseGeoCodeResult.getAddressDetail().province+"-"+ reverseGeoCodeResult.getAddressDetail().city+"-"+reverseGeoCodeResult.getAddressDetail().district+ "</strong><br /><font size='12px' color= '#888888' family='PingFangSC-Regular'><size>" + reverseGeoCodeResult.getAddress() + "</size></font></body></html>";
                        if(type.equals("??????")){
                            txt_start.setText(Html.fromHtml(html, null, new SizeLabel(12, mContext)));
                        }else{
                            txt_end.setText(Html.fromHtml(html, null, new SizeLabel(12, mContext)));
                        }
                    }
                }
            });
            mCoder.reverseGeoCode(new ReverseGeoCodeOption().location(BDMapUtils.convert(latLng)));
        }

    /**
     * ?????????????????????
     */
    private void beginRefreshTimer() {
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
        sendMsgTimer = new Timer();
        sendMsgTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(memptyOrderBean.getOrder_Car_List()!=null&&memptyOrderBean.getOrder_Car_List().size()>0){
                    mPresenter.getCarFlowPositionBean(memptyOrderBean.getOrder_Car_List().get(0).getCarId());
                }
            }
        }, 0, 20000);
    }
    private Timer sendMsgTimer;

    @Override
    protected void onPause() {
        super.onPause();
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
//        isShow=false;
//        tv_top.setVisibility(View.VISIBLE);
//        img_bottom.setVisibility(View.GONE);
//        lin_gdxx.setVisibility(View.GONE);
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) lin_all_bottom.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
//        linearParams.height =  RelativeLayout.LayoutParams.WRAP_CONTENT;// ????????????????????????30
//        lin_all_bottom.setLayoutParams(linearParams); //??????????????????????????????????????????

        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        isShow=false;
        setScrollview(true);
    }

    // ???????????????????????????????????????????????????
    private RoutePlanSearch mSearch = null;
    // ????????????????????????
    private DrivingRoutePlanOption mDrivingRoutePlanOption;

    public void guiHuaLuXian(LatLng startpoint, LatLng endpoint, List<EmptyOrderBean.OrderMarkListBean> centerpoint) {
        mDrivingRoutePlanOption = new DrivingRoutePlanOption();
        // ??????????????????
        mDrivingRoutePlanOption.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_DIS_FIRST);
        // ????????????????????? ????????????passBy
        PlanNode startNode = PlanNode.withLocation(startpoint);
        // ????????????
        PlanNode endNode = PlanNode.withLocation(endpoint);
        // ????????????????????????
        List<PlanNode> listView = new ArrayList<PlanNode>();

        if (isXuanZe==true) {//??????????????????????????????
            for (int i = 0; i < centerpoint.size(); i++) {
                LatLng latLng = null;
//                if(centerpoint.get(i).getMarkType()==20){
//                    latLng=new LatLng(centerpoint.get(i).getRealMarkLat(), centerpoint.get(i).getRealMarkLng());
//                }else{
                if(centerpoint.get(i).getNeedMarkLat()!=0.0&&centerpoint.get(i).getNeedMarkLng()!=0.0){
                    latLng=new LatLng(centerpoint.get(i).getNeedMarkLat(), centerpoint.get(i).getNeedMarkLng());
                    PlanNode center = PlanNode.withLocation(latLng);
                    listView.add(center);
                 }
//                }
            }
        } else if (isXuanZe==false) {//????????????????????????
            for (int i = 0; i < centerpoint.size(); i++) {
                if(centerpoint.get(i).isMark()==true){
                    LatLng  latLng=new LatLng(centerpoint.get(i).getRealMarkLat(), centerpoint.get(i).getRealMarkLng());
                    PlanNode center = PlanNode.withLocation(latLng);
                    listView.add(center);
                }
            }
        }

        mSearch.drivingSearch(mDrivingRoutePlanOption.from(startNode).passBy(listView).to(endNode));
    }

    public void shiJiLuXian(List<EmptyOrderBean.OrderMarkListBean> listpoint,int state) {

        if(!DeviceId.equals("")){
                for (int i=0;i<listpoint.size();i++){
                    if((i+1)<listpoint.size()){
                        mPresenter.getTravelData(DeviceId,listpoint.get(i).getRealMarkTime(),listpoint.get(i+1).getRealMarkTime(),i,state,listpoint.size());
                    }
                }
        }else{
//            ToastUtil.showToast("???????????????");
        }
    }

    /**
     * ????????????????????????
     *
     * @param result ??????????????????
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result != null && result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // ?????????????????????????????????????????????????????????????????????????????????
            // result.getSuggestAddrInfo()
            ToastUtil.showToast("????????????????????????????????????,?????? result.getSuggestAddrInfo()??????????????????????????????");
            return;
        }
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            ToastUtil.showToast("????????????????????????");
            return;
        }

        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            this.result=result;
            new MyThread().start();
        }
    }

    DrivingRouteResult result;
    public class MyThread extends Thread{
        @Override
        public void run() {
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
            List<RouteNode> list = result.getRouteLines().get(0).getWayPoints();
            if (list != null && list.size() >= 1) {
                for (RouteNode planNode : list) {
                    OverlayOptions markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_tjd_unsel)).position(planNode.getLocation()).zIndex(9);
                    Marker carMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
                }
            }
        }
    }
    @Override
    public void returnOrderDriverGrab(String carPositionBeans) {
        ToastUtil.showToast("???????????????");
        EventBus.getDefault().postSticky("????????????");
        finish();
    }

    @Override
    public void returnMyOrderCancel(String carFlow_OrderId, String type) {
        EventBus.getDefault().postSticky("????????????");
        switch (type){
            case "????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("??????????????????");
                    isRef=true;
                    finish();
                }
                break;
            case "????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("??????????????????");
                    finish();
                }
                break;
            case "????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("??????????????????");
                    finish();
                }
                break;
            case "??????????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("????????????");
                }
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
            case "???????????????????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("????????????");
                }
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
            case "????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("????????????");
                }
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                 }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
            case "????????????":
                if (carFlow_OrderId == null) {
                    ToastUtil.showToast("??????????????????");
                }
                if(sendMsgTimer!=null){
                    sendMsgTimer.cancel();
                }
                fra_bottom_sure_cancel.setVisibility(View.GONE);
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                 }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
        }
    }

    EmptyOrderBean memptyOrderBean;

    @Override
    public void returnMyOrderDetails(EmptyOrderBean emptyOrderBean) {
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }

        setScrollview(true);
        lin_all_bottom.setNestedScrollingEnabled(false);


        this.memptyOrderBean = emptyOrderBean;
        lin_gdxx.setVisibility(View.GONE);
        options=new ArrayList<>();
        mBaiduMap.clear();
        if(memptyOrderBean.getOrder_Car_List()!=null&&memptyOrderBean.getOrder_Car_List().size()>0){
            DeviceId=memptyOrderBean.getOrder_Car_List().get(0).getDeviceId();
        }
        /**
         *  ???????????? = 1000  ????????? 1000   ?????????  1100  ?????????  1200  ?????????  1400  ?????????  1500
         *   ???????????? = 2000  ????????? 10    ????????? 12   ????????? 90
         *   ???????????? = 3000   ????????? 20    ????????? 22   ????????? 90
         *   ???????????? = 4000
         * */
        if(groupState.equals(getResources().getString(R.string.type1))){
            lin_tjddk.setVisibility(View.VISIBLE);
            tv_xzdk.setVisibility(View.GONE);
            tv_add_tjd.setVisibility(View.GONE);
            if(memptyOrderBean.getUseStaffId().equals(UserManager.getLoginBean().getStaffId())){
//                tv_sx_tjd.setVisibility(View.VISIBLE);
                if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type1_1))){
                    tv_cancle.setVisibility(View.GONE);
                    tv_sure.setText("????????????");
                    tv_sure.setVisibility(View.VISIBLE);
                    tjdData(emptyOrderBean,false);//?????????
                }else if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type1_2))){
                    if(memptyOrderBean.getOrderState()==165||memptyOrderBean.getOrderState()==170){//?????????

                        tv_xzdk.setVisibility(View.VISIBLE);
                        tv_add_tjd.setVisibility(View.VISIBLE);
                        tjdData(emptyOrderBean,true);//?????????

//                        tv_sure.setText("????????????");
//                        tv_sure.setVisibility(View.VISIBLE);
                        fra_bottom_sure_cancel.setVisibility(View.GONE);

                        tv_cancle.setVisibility(View.GONE);
                    }else{
                        tjdData(emptyOrderBean,false);//?????????
                        tv_cancle.setVisibility(View.VISIBLE);
                        tv_cancle.setText("????????????");
//                        tv_sure.setText("????????????");
                        tv_sure.setVisibility(View.GONE);
                    }
                }else {
                    tjdData(emptyOrderBean,false);//?????????
                    fra_bottom_sure_cancel.setVisibility(View.GONE);
                    tv_cancle.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                }
            }else{
                fra_bottom_sure_cancel.setVisibility(View.GONE);
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
                tjdData(emptyOrderBean,false);//?????????
            }


        }else if(groupState.equals(getResources().getString(R.string.type2))){
            tjdData(emptyOrderBean,false);//?????????
            if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type2_1))){
                tv_cancle.setVisibility(View.VISIBLE);
                tv_sure.setVisibility(View.VISIBLE);
                tv_cancle.setText("??????");
                tv_sure.setText("??????");
            }else{
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
            }
        }else if(groupState.equals(getResources().getString(R.string.type3))){
            tjdData(emptyOrderBean,false);//?????????
            if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type3_1))){
                tv_sure.setVisibility(View.VISIBLE);
                tv_cancle.setVisibility(View.GONE);
//                tv_cancle.setVisibility(View.VISIBLE);
//                tv_cancle.setText("??????");
                tv_sure.setText("????????????");
            }else{
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
            }
        }else if(groupState.equals(getResources().getString(R.string.type4))){
            tjdData(emptyOrderBean,false);//?????????
//            tv_sx_tjd.setVisibility(View.VISIBLE);
            lin_tjddk.setVisibility(View.VISIBLE);
            tv_xzdk.setVisibility(View.GONE);
            tv_add_tjd.setVisibility(View.GONE);
            if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_1))||String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_5))){//?????????  ?????????
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.VISIBLE);
                tv_sure.setText("????????????");
            }/*else if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_2))){// ?????????
                if(memptyOrderBean.getOrderState()==150) {//????????????????????????????????????
                    tv_sure.setVisibility(View.VISIBLE);
                    tv_center.setVisibility(View.VISIBLE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_cancle.setText("????????????");
                    tv_sure.setText("???????????????");
//                    tv_cancle.setText("????????????");
//                    tv_center.setText("????????????");
//                    tv_sure.setText("??????????????????");

                }else if(memptyOrderBean.getOrderState()==155) {//???????????????
                    tv_sure.setVisibility(View.VISIBLE);
                    tv_center.setVisibility(View.VISIBLE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_cancle.setText("????????????");
//                    tv_center.setText("????????????");
                    tv_sure.setText("??????????????????");
                }else if(memptyOrderBean.getOrderState()==160*//*||memptyOrderBean.getOrderState()==165*//*) {//????????? ?????? ?????????????????????
                    tv_center.setVisibility(View.VISIBLE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_sure.setVisibility(View.GONE);
                    tv_center.setText("????????????");
                    tv_cancle.setText("????????????");
                }else if(memptyOrderBean.getOrderState()==165) {//?????????????????????
                    tv_cancle.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    tv_center.setVisibility(View.VISIBLE);
                    tv_center.setText("????????????");
                    //tv_sure.setText("??????????????????");
                } else  if(memptyOrderBean.getOrderState()==170){//???????????????????????????????????????????????????
                    tv_cancle.setVisibility(View.GONE);
                    tv_center.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.VISIBLE);
                    tv_sure.setText("????????????");
                    startActivity(OrderMileageActivity.class);
                }

            }else {//?????????
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
            }*/
        }
        stateTV.setText(memptyOrderBean.getEscapeOrderStateFormat());
        MyOrderListAdapter.myOrder(memptyOrderBean,stateTV,groupState,this);//????????????
        moRenData();//????????????
        ddzzData();//????????????
        scryData();//????????????
    }

    @Override
    public void returnAllOrderList(List<EmptyOrderBean> emptyOrderBean) {

    }
    double carlng,catlat;
    String DeviceId="";
    @Override
    public void returnCarFlowPosition(CarLatLngBean latLng) {
        carlng=latLng.getLng();
        catlat=latLng.getLat();
        //??????????????????  ????????????????????????
        for (int i = 1; i < memptyOrderBean.getOrder_Mark_List().size() - 1; i++) {
            if(memptyOrderBean.getOrder_Mark_List().get(i).getNeedMarkLat()!=0.0&&memptyOrderBean.getOrder_Mark_List().get(i).getNeedMarkLng()!=0.0){
                LatLng tjdlatLng=new LatLng(memptyOrderBean.getOrder_Mark_List().get(i).getNeedMarkLat(),memptyOrderBean.getOrder_Mark_List().get(i).getNeedMarkLng());
                LatLng carlatLng=BDMapUtils.convert(new LatLng(latLng.getLat(),latLng.getLng()));
                boolean isCircleContains = SpatialRelationUtil.isCircleContainsPoint(tjdlatLng, memptyOrderBean.getMarkRadius(), carlatLng);
                if(isCircleContains==true){
                    memptyOrderBean.getOrder_Mark_List().get(i).setMarkCircleRadius(true);
                    tuJingDiAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }

        if(isXuanZe&&groupState.equals(getResources().getString(R.string.type4))){
            if(latLng.isValid()==false){
                 ToastUtil.showToast("????????????????????????");
                 fra_bottom_sure_cancel.setVisibility(View.GONE);
            }else{
                fra_bottom_sure_cancel.setVisibility(View.VISIBLE);
                if(memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLat()!=0.0&&memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLng()!=0.0){
                    LatLng tjdlatLng=new LatLng(memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLat(),memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLng());
                    LatLng carlatLng=BDMapUtils.convert(new LatLng(latLng.getLat(),latLng.getLng()));
                    boolean isCircleContains = SpatialRelationUtil.isCircleContainsPoint(tjdlatLng, memptyOrderBean.getMarkRadius(), carlatLng);
                    if(isCircleContains==true&&memptyOrderBean.getOrderState()==150){//????????????????????????????????????????????????????????????
                        tv_sure.setVisibility(View.GONE);
                        tv_center.setVisibility(View.VISIBLE);
                        tv_cancle.setVisibility(View.VISIBLE);
                        tv_cancle.setText("????????????");
                        tv_center.setText("??????????????????");
                    }else{
                        tv_sure.setVisibility(View.VISIBLE);
                        tv_center.setVisibility(View.GONE);
                        tv_cancle.setVisibility(View.VISIBLE);
                        tv_cancle.setText("????????????");
                        tv_sure.setText("???????????????");
                    }
                }
                if(memptyOrderBean.getOrderState()==155) {//???????????????
                    tv_sure.setVisibility(View.GONE);
                    tv_center.setVisibility(View.VISIBLE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_cancle.setText("????????????");
                    tv_center.setText("??????????????????");
                }else if(memptyOrderBean.getOrderState()==160) {//????????? ?????? ?????????????????????
                    tv_center.setVisibility(View.VISIBLE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_sure.setVisibility(View.GONE);
                    tv_center.setText("????????????");
                    tv_cancle.setText("????????????");
                }else if(memptyOrderBean.getOrderState()==165) {//?????????????????????
                    tv_cancle.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    tv_center.setVisibility(View.VISIBLE);
                    tv_center.setText("????????????");
                    //tv_sure.setText("??????????????????");
                } else  if(memptyOrderBean.getOrderState()==170){//???????????????????????????????????????????????????
                    tv_cancle.setVisibility(View.GONE);
                    tv_center.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.VISIBLE);
                    tv_sure.setText("????????????");
                }
            }
        }else{
             if(memptyOrderBean.getOrderState()==150){//????????????????????????????????????????????????????????????
                    tv_sure.setVisibility(View.VISIBLE);
                    tv_center.setVisibility(View.GONE);
                    tv_cancle.setVisibility(View.VISIBLE);
                    tv_cancle.setText("????????????");
                    tv_sure.setText("???????????????");
            }
            if(memptyOrderBean.getOrderState()==155) {//???????????????
                tv_sure.setVisibility(View.GONE);
                tv_center.setVisibility(View.VISIBLE);
                tv_cancle.setVisibility(View.VISIBLE);
                tv_cancle.setText("????????????");
                tv_center.setText("??????????????????");
            }else if(memptyOrderBean.getOrderState()==160) {//????????? ?????? ?????????????????????
                tv_center.setVisibility(View.VISIBLE);
                tv_cancle.setVisibility(View.VISIBLE);
                tv_sure.setVisibility(View.GONE);
                tv_center.setText("????????????");
                tv_cancle.setText("????????????");
            }else if(memptyOrderBean.getOrderState()==165) {//?????????????????????
                tv_cancle.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
                tv_center.setVisibility(View.VISIBLE);
                tv_center.setText("????????????");
                //tv_sure.setText("??????????????????");
            } else  if(memptyOrderBean.getOrderState()==170){//???????????????????????????????????????????????????
                tv_cancle.setVisibility(View.GONE);
                tv_center.setVisibility(View.GONE);
                tv_sure.setVisibility(View.VISIBLE);
                tv_sure.setText("????????????");
            }
        }
    }

    /**
     *
     * ????????????
     */
    @Override
    public void returnOrderStateLogList(List<CarRecordsBean> carRecordsBeans) {
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        isShow=true;
        setScrollview(false);

        setTab(txt_ycjl, view2, txt_ycxx, view1, View.GONE, View.VISIBLE);
        carRecordsBeanAdapter = new CarRecordsAdapter(this, R.layout.order_details_car_records_item, carRecordsBeans);
        carRecordsBeanAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view_ycjl.setLayoutManager(new LinearLayoutManager(this));
        // ???????????? , ??????
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true);
        recycler_view_ycjl.setAdapter(carRecordsBeanAdapter);
    }

    @Override
    public void returnOrderMarkAddBean(String str) {
                if(str.equals("????????????")){
                    if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")) {
                        mPresenter.getUserSureGoCarBeans(memptyOrderBean.getCarFlow_OrderId(), "????????????");
                    }else{
                        ToastUtil.showToast("??????id?????????");
                    }
                }else if(str.equals("??????????????????")){
                    if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")) {
                        mPresenter.getUserSureGoCarBeans(memptyOrderBean.getCarFlow_OrderId(), "????????????");
                    }else{
                        ToastUtil.showToast("??????id?????????");
                    }
                }else{
                    if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")) {
                       mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                    }else{
                        ToastUtil.showToast("??????id?????????");
                    }
                }
    }

    @Override
    public void returnOrderMarkModelBean(MarkerDetailsBean item) {
            DaKaDetailsActivity.goToPage(this, new Gson().toJson(item));
    }
    //??????OverlayOptions?????????
    List<OverlayOptions> options = new ArrayList<OverlayOptions>();
    @Override
    public void drawTravel(TravelListBean travelListBeanList, int type,int state,int alllistsize) {

        List<LatLng> list=new ArrayList<>();
        if(travelListBeanList!=null&&travelListBeanList.getPois()!=null) {
            for (TravelItemBean travelItemBean : travelListBeanList.getPois()) {
                list.add(BDMapUtils.convert(new LatLng(travelItemBean.getLat(), travelItemBean.getLng())));
            }

            if (list.size() > 0) {
                if (state == 170) {
                    // ??????????????????
                    if (type == 0) {
                        OverlayOptions option1 = new MarkerOptions()
                                .position(list.get(0))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_begin));
                        options.add(option1);
                    }
                        OverlayOptions option1 = new MarkerOptions()
                                .position(list.get(list.size() - 1))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_tjd));
                        options.add(option1);


                    OverlayOptions polylineOptions = new PolylineOptions().width(DisplayUtil.dip2px(3)).dottedLine(false).zIndex(9)
                            .color(getResources().getColor(R.color.app_main_colors)).points(list);
                    mBaiduMap.addOverlay(polylineOptions);
                    mBaiduMap.addOverlays(options);
                } else {

                    if (type == 0) {
                        OverlayOptions option1 = new MarkerOptions()
                                .position(list.get(0))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_begin));
                        options.add(option1);
                    }
                    if (alllistsize - 2 == type) {
                        OverlayOptions option1 = new MarkerOptions()
                                .position(list.get(list.size() - 1))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_end));
                        options.add(option1);
                    } else {
                        OverlayOptions option1 = new MarkerOptions()
                                .position(list.get(list.size() - 1))
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_tjd));
                        options.add(option1);
                    }
                    OverlayOptions polylineOptions = new PolylineOptions().width(DisplayUtil.dip2px(3)).dottedLine(false).zIndex(9)
                            .color(getResources().getColor(R.color.app_main_colors)).points(list);
                    mBaiduMap.addOverlay(polylineOptions);
                    mBaiduMap.addOverlays(options);
                }
            }
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

    @Override
    public void ondialogwc(String mtype, String neir1, String neir2) {
       if(mtype.equals(this.getString(R.string.dialog_ts5_1))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
               mPresenter.getMyOrderCancelBeans(memptyOrderBean.getCarFlow_OrderId(),0,"","????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
        }else if(mtype.equals(this.getString(R.string.dialog_ts5_2))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
               mPresenter.getMyOrderCancelBeans(memptyOrderBean.getCarFlow_OrderId(), Integer.valueOf(getResources().getString(R.string.type2_2)),"","????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
        }else if(mtype.equals(this.getString(R.string.dialog_ts5_5))){
           if(memptyOrderBean.isHasMark()==false){
               JsonArray MarkImgUrlList= new JsonArray();
               mPresenter.getOrderMarkAddPresenter("????????????",orderId,"","",String.valueOf(carlng),String.valueOf(catlat),"","","0","20","",MarkImgUrlList,true,"","",null,null,"","",true);
           }else{
               JsonArray MarkImgUrlList= new JsonArray();
               mPresenter.getOrderMarkAddPresenter("????????????",orderId,memptyOrderBean.getOrder_Mark_List().get(memptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkTitle(),memptyOrderBean.getOrder_Mark_List().get(memptyOrderBean.getOrder_Mark_List().size() - 1).getNeedMarkSimpleAddress(),String.valueOf(carlng),String.valueOf(catlat),"","",memptyOrderBean.getOrder_Mark_List().size() - 1+"","10",memptyOrderBean.getOrder_Mark_List().get(memptyOrderBean.getOrder_Mark_List().size() - 1).getCarFlow_Order_MarkId(),MarkImgUrlList,true,"","",null,null,"","",false);
           }
        }else if(mtype.equals(this.getString(R.string.dialog_ts5_6))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
              mPresenter.getUserSureGoCarBeans(memptyOrderBean.getCarFlow_OrderId(),"??????????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
        }else if(mtype.equals(this.getString(R.string.dialog_ts5_3))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
               mPresenter.getMyOrderCancelBeans(memptyOrderBean.getCarFlow_OrderId(), Integer.valueOf(getResources().getString(R.string.type2_3)),neir2,"????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
        }else if(mtype.equals(getResources().getString(R.string.dialog_ts5_4))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
               mPresenter.getOrderDispatchAuditBeans(memptyOrderBean.getCarFlow_OrderId(),Integer.valueOf(getResources().getString(R.string.type3_3)),neir2,null,"????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
        }else if(mtype.equals(getResources().getString(R.string.dialog_ts5_7))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
               mPresenter.getUserSureGoCarBeans(memptyOrderBean.getCarFlow_OrderId(),"???????????????????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
       }else if(mtype.equals(getResources().getString(R.string.dialog_ts5_8))){
           if(memptyOrderBean.isHasMark()==true){
               JsonArray MarkImgUrlList= new JsonArray();
               mPresenter.getOrderMarkAddPresenter("??????????????????",orderId,memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkTitle(),memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkSimpleAddress(),String.valueOf(carlng),String.valueOf(catlat),"","","0","10",memptyOrderBean.getOrder_Mark_List().get(0).getCarFlow_Order_MarkId(),MarkImgUrlList,true,"","",null,null,"","",false);
           }else{
               JsonArray MarkImgUrlList= new JsonArray();
               mPresenter.getOrderMarkAddPresenter("??????????????????",orderId,"","",String.valueOf(carlng),String.valueOf(catlat),"","","0","20","",MarkImgUrlList,true,"","",null,null,"","",false);
           }

       }else if(mtype.equals(getResources().getString(R.string.dialog_ts5_10))){
           if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                mPresenter.getUserSureGoCarBeans(memptyOrderBean.getCarFlow_OrderId(),"???????????????????????????");
           }else{
               ToastUtil.showToast("??????id?????????");
           }
       }else if(mtype.equals(getResources().getString(R.string.dialog_ts5_9))){
           OrderMileageActivity.goToPage(this,memptyOrderBean.isHasMark(),160,new Gson().toJson(memptyOrderBean.getOrder_Mark_List()),orderId,catlat,carlng);
       }
    }

    // ??????RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        private MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            return null;
        }
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }


    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    private void moRenData() {
        tv_ycr_vaule.setText(memptyOrderBean.getUseStaffName());
        tv_yx_jsr_vaule.setText(memptyOrderBean.getDriverStaffName().equals("") ? memptyOrderBean.getDriverTypeFormat() : memptyOrderBean.getDriverStaffName());
        tv_yx_cph_vaule.setText(memptyOrderBean.getWillUseCarPlate().equals("") ? "--" : memptyOrderBean.getWillUseCarPlate());
        tv_yx_cx_vaule.setText(memptyOrderBean.getCarStyleName() + memptyOrderBean.getWillUseCarSeatNum() + "???");


        if(groupState.equals(getResources().getString(R.string.type3))||groupState.equals(getResources().getString(R.string.type4))||groupState.equals(getResources().getString(R.string.type1))){
            if(memptyOrderBean.getOrder_Car_List()!=null&&memptyOrderBean.getOrder_Car_List().size()>0){
                tv_sj_jsr_vaule.setText(memptyOrderBean.getOrder_Car_List().get(0).getDriverStaffName());
                tv_sj_cph_vaule.setText(memptyOrderBean.getOrder_Car_List().get(0).getCarPlateNumber());
                tv_sj_cx_vaule.setText(memptyOrderBean.getOrder_Car_List().get(0).getCarStyleName());
            }else{
                if(groupState.equals(getResources().getString(R.string.type3))||groupState.equals(getResources().getString(R.string.type4))){
                    if(memptyOrderBean.getDispatch_Grab_List()!=null&&memptyOrderBean.getDispatch_Grab_List().size()>0){
                        tv_sj_jsr_vaule.setText(memptyOrderBean.getDispatch_Grab_List().get(0).getGrabStaffName().equals("")?"--":memptyOrderBean.getDispatch_Grab_List().get(0).getGrabStaffName());
                        tv_sj_cph_vaule.setText(memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarPlateNumber().equals("")?"--":memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarPlateNumber());
                        tv_sj_cx_vaule.setText(memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarStyleName().equals("")?"--":memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarStyleName());
                    }else{
                        tv_sj_jsr_vaule.setText("--");
                        tv_sj_cph_vaule.setText("--");
                        tv_sj_cx_vaule.setText("--");
                    }
                }else{
                    tv_sj_jsr_vaule.setText("--");
                    tv_sj_cph_vaule.setText("--");
                    tv_sj_cx_vaule.setText("--");
                }
            }
        }else{
            rel_jsr.setVisibility(View.GONE);
            rel_cph.setVisibility(View.GONE);
            rel_cx.setVisibility(View.GONE);
            view_cph.setVisibility(View.GONE);
            view_cx.setVisibility(View.GONE);
            view_jsr.setVisibility(View.GONE);
        }



        tv_kssj_vaule.setText(memptyOrderBean.getWillUseStartDateTimeFormat());
        tv_jssj_vaule.setText(memptyOrderBean.getWillUseEndDateTimeFormat());
        tv_yclx_vaule.setText(memptyOrderBean.getUseCarTypeName());
        tv_hplx_vaule.setText(memptyOrderBean.getCarPlateTypeName());
        ddbhTV.setText(memptyOrderBean.getCarFlow_OrderId());
        tv_cjsj.setText(memptyOrderBean.getCreateTimeFormat());
        tv_scry_num.setText(memptyOrderBean.getPassengerNum() + "???");

        tv_ycsc.setText(memptyOrderBean.getUseCarDuration() + "");
        tv_yclc.setText(memptyOrderBean.getUseCarMileage() + "");
        tv_fyzj.setText(memptyOrderBean.getUseCarFee() + "");

        tv_ycsy_bt_vaule.setText(memptyOrderBean.getReasonTitle().equals("")?"--":memptyOrderBean.getReasonTitle());
        tv_ycsy_nr_vaule.setText(memptyOrderBean.getReasonContent().equals("")?"--":memptyOrderBean.getReasonContent());

    }

    List<WorkBeanchBean> ddzzmlist;

    private void ddzzData() {
        ddzzmlist = new ArrayList<>();
        if (!memptyOrderBean.getLeaderStaffName().equals("") && !memptyOrderBean.getLeaderStaffPhone().equals("")) {
            ddzzmlist.add(new WorkBeanchBean(memptyOrderBean.getLeaderStaffName() + getResources().getString(R.string.stringsnull2)+ memptyOrderBean.getLeaderStaffPhone()));
        }
        tv_ddzz_num.setText(ddzzmlist.size() + "???");
        if (ddzzmlist.size() == 0) {
            recycler_view_ddzz.setVisibility(View.GONE);
        } else {
            recycler_view_ddzz.setVisibility(View.VISIBLE);
        }
        ddzzworkBeanchAdapter = new WorkBeanchAdapter(this, R.layout.fragment_my_need_ryxx_item, ddzzmlist, "??????????????????");
        ddzzworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view_ddzz.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_view_ddzz.setAdapter(ddzzworkBeanchAdapter);
    }

    List<WorkBeanchBean> scrymlist;

    private void scryData() {
        scrymlist = new ArrayList<>();
        for (EmptyOrderBean.OrderPassengerListBean e : memptyOrderBean.getOrder_Passenger_List()) {
            scrymlist.add(new WorkBeanchBean(e.getPassengerName() + getResources().getString(R.string.stringsnull2)+ e.getPassengerPhone()));
        }
        if (scrymlist.size() == 0) {
            recycler_view_scry.setVisibility(View.GONE);
        } else {
            recycler_view_scry.setVisibility(View.VISIBLE);
        }
        scryworkBeanchAdapter = new WorkBeanchAdapter(this, R.layout.fragment_my_need_ryxx_item, scrymlist, "??????????????????");
        scryworkBeanchAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view_scry.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_view_scry.setAdapter(scryworkBeanchAdapter);
    }

    public void setScrollview(boolean isShows){
        if(isShows==false){
            isShow=true;
            tv_top.setVisibility(View.GONE);
            img_bottom.setVisibility(View.VISIBLE);
            lin_gdxx.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) lin_all_bottom.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
            if(fra_bottom_sure_cancel.getVisibility()==View.VISIBLE){
                linearParams.height = DisplayUtil.getScreenHeight(this) - DisplayUtil.dip2px(title_bar_layout.getHeight())- DisplayUtil.dip2px(title_bar_layout.getHeight())/2;// ????????????????????????30
            }else{
                linearParams.height = DisplayUtil.getScreenHeight(this) - DisplayUtil.dip2px(title_bar_layout.getHeight());// ????????????????????????30
            }
//            linearParams.height = DisplayUtil.getScreenHeight(this) - DisplayUtil.dip2px(title_bar_layout.getHeight());// ????????????????????????30
            lin_all_bottom.setLayoutParams(linearParams); //??????????????????????????????????????????
        }else{
            isShow=false;
            tv_top.setVisibility(View.VISIBLE);
            img_bottom.setVisibility(View.GONE);
            lin_gdxx.setVisibility(View.GONE);
            recycler_view_tjd.setVisibility(View.GONE);
            txt_zk.setText("??????");
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) lin_all_bottom.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
            linearParams.height =  RelativeLayout.LayoutParams.WRAP_CONTENT;// ????????????????????????30
            lin_all_bottom.setLayoutParams(linearParams); //??????????????????????????????????????????
        }
    }
    boolean isShow=false;
    @OnClick({R.id.lin_top,R.id.tv_center,R.id.tv_sx_tjd,R.id.tv_cancle, R.id.title_bar_back, R.id.tv_gdxx, R.id.lin_ycxx, R.id.lin_ycjl, R.id.txt_zk, R.id.tv_sure, R.id.tv_add_tjd, R.id.tv_xzdk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sx_tjd:
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
            case R.id.title_bar_back:
                isRef=true;
                finish();
                break;
            case R.id.lin_top:
//                setScrollview(isShow);
                break;
            case R.id.tv_gdxx:
//                if (lin_gdxx.getVisibility() == View.VISIBLE) {
//                    lin_gdxx.setVisibility(View.GONE);
//
//                    Drawable drawable = getResources().getDrawable(R.mipmap.two_icon_bottom);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    tv_gdxx.setCompoundDrawables(null, null, drawable, null);
//                    tv_gdxx.setText("????????????");
//                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) lin_all_bottom.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
//                    linearParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;// ????????????????????????30
//                    lin_all_bottom.setLayoutParams(linearParams); //??????????????????????????????????????????
//                } else {
//                    lin_gdxx.setVisibility(View.VISIBLE);
//                    Drawable drawable = getResources().getDrawable(R.mipmap.two_icon_top);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    tv_gdxx.setCompoundDrawables(null, null, drawable, null);
//                    tv_gdxx.setText("??????");
//                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) lin_all_bottom.getLayoutParams(); //?????????textView????????????????????? linearParams.height = 20;// ????????????????????????20
//                    linearParams.height = DisplayUtil.getScreenHeight(this) -DisplayUtil.dip2px(5);// ????????????????????????30
//                    lin_all_bottom.setLayoutParams(linearParams); //??????????????????????????????????????????
//                }
                break;
            case R.id.lin_ycxx:
                setTab(txt_ycxx, view1, txt_ycjl, view2, View.VISIBLE, View.GONE);
                mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                isShow=true;
                setScrollview(false);
                break;
            case R.id.lin_ycjl:
                if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                    mPresenter.getOrderStateLogListBean(memptyOrderBean.getCarFlow_OrderId(),groupState);
                }else{
                    ToastUtil.showToast("??????id?????????");
                }
                break;
            case R.id.txt_zk:
                if (txt_zk.getText().toString().equals("??????")) {
                    recycler_view_tjd.setVisibility(View.GONE);
                    txt_zk.setText("??????");
                } else {
                    recycler_view_tjd.setVisibility(View.VISIBLE);
                    txt_zk.setText("??????");
                }
                break;
            case R.id.tv_cancle:
                /**
                 *  ???????????? = 1000  ????????? 1000   ?????????  1100  ?????????  1200  ?????????  1400  ?????????  1500
                 *   ???????????? = 2000  ????????? 10    ????????? 12   ????????? 90
                 *   ???????????? = 3000   ????????? 20    ????????? 22   ????????? 90
                 *   ???????????? = 4000
                 * */
                if(groupState.equals(getResources().getString(R.string.type1))){
                    AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_1), 2, this);
                }else if(groupState.equals(getResources().getString(R.string.type2))){
                    AlertDialogUtils.showAlertDialog(this,  getResources().getString(R.string.dialog_ts5_3), 4, this);
                }else if(groupState.equals(getResources().getString(R.string.type3))){
                    AlertDialogUtils.showAlertDialog(this,  getResources().getString(R.string.dialog_ts5_4), 4, this);
                }else if(groupState.equals(getResources().getString(R.string.type4))){
                    AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_1), 2, this);
                }


                break;
            case R.id.tv_sure:
                /**
                 *  ???????????? = 1000  ????????? 1000   ?????????  1100  ?????????  1200  ?????????  1400  ?????????  1500
                 *   ???????????? = 2000  ????????? 10    ????????? 12   ????????? 90
                 *   ???????????? = 3000   ????????? 20    ????????? 22   ????????? 90
                 *   ???????????? = 4000
                 * */
                if(groupState.equals(getResources().getString(R.string.type1))){
                    if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type1_1))){
                        AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_1), 2, this);
                    }else if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type1_2))){
                        if(memptyOrderBean.getOrderState()==170){//?????????
                            AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_5), 2, this);
                        }else{
                            AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_6), 2, this);
                        }
                    }
                }else if(groupState.equals(getResources().getString(R.string.type2))){
                    //??????????????????
                    AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_2), 2, this);
                }else if(groupState.equals(getResources().getString(R.string.type3))){
                       DispatchCarActivity.goToPage(this,new Gson().toJson(memptyOrderBean));
                }else if(groupState.equals(getResources().getString(R.string.type4))){
                    if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_1))||String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_5))){//?????????  ?????????
                         if(memptyOrderBean.getEscapeOrderState()==Integer.valueOf(this.getString(R.string.type4_1))){//??????
                             if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                                 mPresenter.getgetOrderDriverGrabPresenter(memptyOrderBean.getCarFlow_OrderId(),memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarCompanyId(),memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarDepartmentId(),memptyOrderBean.getDispatch_Grab_List().get(0).getGrabCarId());
                             }else{
                                 ToastUtil.showToast("??????id?????????");
                             }
                         }else{
                             if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                                  CheckCarActivity.goToPage(this,"????????????",memptyOrderBean.getCarFlow_OrderId());
                             }else{
                                 ToastUtil.showToast("??????id?????????");
                             }
                         }
                    }else if(String.valueOf(memptyOrderBean.getEscapeOrderState()).equals(mContext.getResources().getString(R.string.type4_2))){// ?????????

//                        if(memptyOrderBean.getOrderState()>=150&&memptyOrderBean.getOrderState()<170){//????????????????????????????????????????????????
//                            AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_7), 2, this);
//                        }else  if(memptyOrderBean.getOrderState()==170){//???????????????????????????????????????????????????
//                            AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_5), 2, this);
//                        }
                            if(memptyOrderBean.getOrderState()==150){//????????????????????????????????????
                                   OrderMileageActivity.goToPage(this,memptyOrderBean.isHasMark(),150,new Gson().toJson(memptyOrderBean.getOrder_Mark_List()),orderId,catlat,carlng);
                            }else if(memptyOrderBean.getOrderState()==170){//(????????????)
                                OrderMileageActivity.goToPage(this,memptyOrderBean.isHasMark(),170,new Gson().toJson(memptyOrderBean.getOrder_Mark_List()),orderId,catlat,carlng);
                            }
                    }
                }
                break;
            case R.id.tv_center:


                if(groupState.equals(getResources().getString(R.string.type4))){
                    if(isXuanZe){
                        LatLng tjdlatLng=new LatLng(memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLat(),memptyOrderBean.getOrder_Mark_List().get(0).getNeedMarkLng());
                        LatLng carlatLng=BDMapUtils.convert(new LatLng(catlat,carlng));
                        boolean isCircleContains = SpatialRelationUtil.isCircleContainsPoint(tjdlatLng, memptyOrderBean.getMarkRadius(), carlatLng);
                        if(memptyOrderBean.getOrderState()==150||memptyOrderBean.getOrderState()==155){//???????????????????????????????????????
                            if(isCircleContains==true) {//????????????????????????????????????????????????????????????
                                AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_7), 2, this);
                            }else{
                                AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_10), 2, this);
                            }

                        }else if(memptyOrderBean.getOrderState()==160){//(????????????)
                            if(isCircleContains==true) {//????????????????????????????????????????????????????????????
                                OrderMileageActivity.goToPage(this,memptyOrderBean.isHasMark(),160,new Gson().toJson(memptyOrderBean.getOrder_Mark_List()),orderId,catlat,carlng);
                            }else{
                                AlertDialogUtils.showAlertDialog(this,getResources().getString(R.string.dialog_ts5_9), 2, this);
                            }
                        }
                    }else{
                        if(memptyOrderBean.getOrderState()==150||memptyOrderBean.getOrderState()==155){//???????????????????????????????????????
                            AlertDialogUtils.showAlertDialog(this, getResources().getString(R.string.dialog_ts5_7), 2, this);
                        }else if(memptyOrderBean.getOrderState()==160){//(????????????)
                            OrderMileageActivity.goToPage(this,memptyOrderBean.isHasMark(),160,new Gson().toJson(memptyOrderBean.getOrder_Mark_List()),orderId,catlat,carlng);
                        }
                    }
                }
                break;
            case R.id.tv_add_tjd:
                DiTuCheckAddressActivity.goToPage(this, "?????????", "","???????????????????????????");
                break;
            case R.id.tv_xzdk:
                /**
                 *  ???????????? = 1000  ????????? 1000   ?????????  1100  ?????????  1200  ?????????  1400  ?????????  1500
                 *   ???????????? = 2000  ????????? 10    ????????? 12   ????????? 90
                 *   ???????????? = 3000   ????????? 20    ????????? 22   ????????? 90
                 *   ???????????? = 4000
                 * */
                    AddDaKaActivity.goToPage(this, orderId,"","20","",memptyOrderBean.getOrder_Car_List().get(0).getCarId());
                break;
        }
    }

    List<EmptyOrderBean.OrderMarkListBean> tjdAdapterList;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(EmptyOrderBean.OrderMarkListBean tuJingDiBean) {
        if(tuJingDiBean!=null){
            if(tuJingDiBean.getMarkTypeFormat()!=null&&!tuJingDiBean.getMarkTypeFormat().equals("")){
                if (tuJingDiBean.getMarkTypeFormat().equals("10")) {//????????????
                    if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                        mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                     }else{
                        ToastUtil.showToast("??????id?????????");
                    }
                } else if (tuJingDiBean.getMarkTypeFormat().equals("20")) {  //????????????
                    if(memptyOrderBean!=null&&!memptyOrderBean.getCarFlow_OrderId().equals("")){
                       mPresenter.getMyOrderDetailsBeans(memptyOrderBean.getCarFlow_OrderId(),groupState);
                    }else{
                        ToastUtil.showToast("??????id?????????");
                    }
                } else if (tuJingDiBean.getMarkTypeFormat().equals("???????????????????????????")) {
                    JsonArray MarkImgUrlList= new JsonArray();
                    mPresenter.getOrderMarkAddPresenter("???????????????",orderId,tuJingDiBean.getNeedMarkTitle(),"",null,null,"","","","30","",MarkImgUrlList,false,tuJingDiBean.getNeedMarkSimpleAddress(),tuJingDiBean.getNeedMarkFullAddress(),String.valueOf(tuJingDiBean.getNeedMarkLng()),String.valueOf(tuJingDiBean.getNeedMarkLat()),tuJingDiBean.getLinkName(),tuJingDiBean.getLinkPhone(),false);
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sendMsgTimer!=null){
            sendMsgTimer.cancel();
        }
        // ??????????????????
        if (mSearch != null) {
            mSearch.destroy();
        }
        // ??????????????????
        mBaiduMap.clear();
        // ???activity??????onDestroy???????????????mMapView. onDestroy ()
        EventBus.getDefault().unregister(this);
        //???????????????????????????
        EventBus.getDefault().removeAllStickyEvents();
        if (null != map) {
            map.onDestroy();
        }

    }

    public void setTab(TextView seltxt, View selview, TextView unselTxt, View unselview, int ycxxistrue, int ycjlistrue) {
        seltxt.setTextColor(getResources().getColor(R.color.app_main_colors));
        selview.setVisibility(View.VISIBLE);
        unselTxt.setTextColor(getResources().getColor(R.color.needcar_txt_color4));
        unselview.setVisibility(View.GONE);
        lin_ycxx_details.setVisibility(ycxxistrue);
        recycler_view_ycjl.setVisibility(ycjlistrue);

    }

    public static void goToPage(Activity activity, String orderId,String type) {
        Intent intent = new Intent(activity, MyOrderDetailsActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("groupState", type);
        activity.startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyNeedCarFragment.isRef=true;
            if(sendMsgTimer!=null){
                sendMsgTimer.cancel();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void del(int pos) {

    }

    //??????
    @Override
    public void item(EmptyOrderBean.OrderMarkListBean item, int size, int pos) {
        if (item.isMark() == false) {
            AddDaKaActivity.goToPage(this, orderId,String.valueOf(pos),"10",new Gson().toJson(item),memptyOrderBean.getOrder_Car_List().get(0).getCarId());
        } else {
            mPresenter.getOrderMarkmodelPresenter(item.getCarFlow_Order_MarkId());
        }
    }
}