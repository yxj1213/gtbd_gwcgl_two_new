package com.ttce.vehiclemanage.ui.main.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.CarLatLngBean;
import com.ttce.vehiclemanage.bean.CarRecordsBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.MarkerDetailsBean;
import com.ttce.vehiclemanage.bean.OrderSelectBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.main.activity.my.order.MyOrderDetailsActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.OrderSelectActivity;
import com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu.CheckCarActivity;
import com.ttce.vehiclemanage.ui.main.adapter.me.order.MyOrderListAdapter;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyOrderConstract;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyOrderModel;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyOrderPresenter;
import com.ttce.vehiclemanage.utils.SerializeUtil;
import com.ttce.vehiclemanage.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ????????? ????????????
 */
public class OrderToDoListActivity extends BaseActivity<MyOrderPresenter, MyOrderModel> implements MyOrderConstract.View, OnRefreshListener, OnLoadMoreListener, MyOrderListAdapter.DriverJieDanListenter{

    private MyOrderListAdapter itemAdapter;
    private String type="";
    private ArrayList<Integer> escapeOrderState=new ArrayList<>();
    private int mStartPage = 1;

    @Bind(R.id.irc1)
    IRecyclerView ircl;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.iv_right)
    ImageView iv_right;
    @Bind(R.id.title_bar_title)
    TextView title_bar_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_todolist;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    @OnClick({R.id.title_bar_back,R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_right:
                OrderSelectActivity.goToPage(this,"????????????");
                break;
        }
    }
    @Override
    public void initView() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
        EventBus.getDefault().register(this);

        ircl.setHasFixedSize(true);
        ircl.setNestedScrollingEnabled(false);
        ircl.setOnRefreshListener(this);
        ircl.setOnLoadMoreListener(this);
        loadData();

        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.icon_order_select);
    }

    @Override
    public void jiedan(EmptyOrderBean tuJingDiBean) {

        if(tuJingDiBean.getEscapeOrderState()==Integer.valueOf(this.getString(R.string.type4_1))){//??????
            if(tuJingDiBean.getDispatch_Grab_List()!=null&&tuJingDiBean.getDispatch_Grab_List().size()>0){
                mPresenter.getgetOrderDriverGrabPresenter(tuJingDiBean.getCarFlow_OrderId(),tuJingDiBean.getDispatch_Grab_List().get(0).getGrabCarCompanyId(),tuJingDiBean.getDispatch_Grab_List().get(0).getGrabCarDepartmentId(),tuJingDiBean.getDispatch_Grab_List().get(0).getGrabCarId());
            }else{
                ToastUtil.showToast("?????????????????????????????????");
            }
        }else{
            CheckCarActivity.goToPage(this,"????????????",tuJingDiBean.getCarFlow_OrderId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(String str) {
        if(str.equals("????????????")){
            loadData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // ???activity??????onDestroy???????????????mMapView. onDestroy ()
        EventBus.getDefault().unregister(this);
        //???????????????????????????
        EventBus.getDefault().removeAllStickyEvents();
    }
    public static void goToPage(Activity activity,String type,List<Integer> escapeOrderStatelist) {
        Intent intent = new Intent(activity, OrderToDoListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putIntegerArrayList("escapeOrderState", (ArrayList<Integer>) escapeOrderStatelist);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(OrderSelectBean orderSelectBean) {
        if(orderSelectBean.getType().equals("????????????")){
            StartCreateTime=orderSelectBean.getStartTime();
            EndCreateTime=orderSelectBean.getEndTime();
            PlateNumber=orderSelectBean.getCarNumber();
            KeyWord=orderSelectBean.getNameOrPhone();
            CarFlow_OrderId=orderSelectBean.getOrderId();
            loadData();
        }
    }


    String StartCreateTime="",EndCreateTime="", PlateNumber="", KeyWord="", CarFlow_OrderId="";
    JsonArray mescapeOrderStatelist;
    protected void loadData() {
        type =this.getIntent().getExtras().getString("type");
        escapeOrderState =this.getIntent().getExtras().getIntegerArrayList("escapeOrderState");
        mescapeOrderStatelist= new JsonArray();
        for (Integer ints:escapeOrderState) {
            mescapeOrderStatelist.add(ints);
        }

        String userJson = SPDefaultHelper.getString(SPDefaultHelper.USER);
        LoginBean user = SerializeUtil.deSerialize(userJson, LoginBean.class);
        if (user != null) {
            /**
             *  ???????????? = 1000
             *   ???????????? = 2000
             *   ???????????? = 3000
             *   ???????????? = 4000
             * ????????????????????????????????????????????????
             *
             * */
            mPresenter.getAllOrderListBeans(mStartPage,mescapeOrderStatelist,type, StartCreateTime,EndCreateTime, PlateNumber, KeyWord, CarFlow_OrderId);
        }
        if(type.equals(getResources().getString(R.string.type2))){//????????????
          title_bar_title.setText(getResources().getString(R.string.work_item));
        }else  if(type.equals(getResources().getString(R.string.type3))){//????????????
            title_bar_title.setText(getResources().getString(R.string.me_txt8));
        }else  if(type.equals(getResources().getString(R.string.type4))){//????????????
            title_bar_title.setText(getResources().getString(R.string.me_txt13));
        }else  if(type.equals("5000")){//????????????
            title_bar_title.setText(getResources().getString(R.string.need_car_0_1));
        }
        switch (type.substring(0,1)){
            case "4":
                itemAdapter = new MyOrderListAdapter(this, R.layout.adapter_driver_order_item, new ArrayList<>(),type,this);
                break;
            default:
                itemAdapter = new MyOrderListAdapter(this, R.layout.adapter_my_order_item, new ArrayList<>(),type);
                break;

        }
        mStartPage = 1;
        itemAdapter.openLoadAnimation(new ScaleInAnimation());
        ircl.setLayoutManager(new LinearLayoutManager(this));
        ircl.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MyOrderDetailsActivity.goToPage(OrderToDoListActivity.this,((EmptyOrderBean) o).getCarFlow_OrderId(),type);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        itemAdapter.getPageBean().setRefresh(false);
        //????????????
        ircl.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);

        mPresenter.getAllOrderListBeans(mStartPage,mescapeOrderStatelist,type ,StartCreateTime,EndCreateTime, PlateNumber, KeyWord, CarFlow_OrderId);
    }

    @Override
    public void onRefresh() {
        itemAdapter.getPageBean().setRefresh(true);
        ircl.setRefreshing(true);
        mStartPage = 1;

        //????????????
        mPresenter.getAllOrderListBeans(mStartPage,mescapeOrderStatelist,type ,StartCreateTime,EndCreateTime, PlateNumber, KeyWord, CarFlow_OrderId);
    }

    @Override
    public void showLoading(String title) {
        if (itemAdapter.getPageBean().isRefresh()) {
            if (itemAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
            }
        }
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        if (itemAdapter.getPageBean().isRefresh()) {
            if (itemAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            ircl.setRefreshing(false);
        } else {
            ircl.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void returnOrderDriverGrab(String carPositionBeans) {
        ToastUtil.showToast("???????????????");
        loadData();
    }

    @Override
    public void returnMyOrderCancel(String carFlow_OrderId, String type) {

    }

    @Override
    public void returnMyOrderDetails(EmptyOrderBean carFlow_OrderId) {

    }

    @Override
    public void returnAllOrderList(List<EmptyOrderBean> listOrderBean) {
        if(listOrderBean!=null&&listOrderBean.size()>0||itemAdapter.getAll().size()>0){
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (itemAdapter.getPageBean().isRefresh()) {
                ircl.setRefreshing(false);
                itemAdapter.replaceAll(listOrderBean);
            } else {
                if (listOrderBean.size() > 0) {
                    ircl.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    itemAdapter.addAll(listOrderBean);
                } else {
                    ircl.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }else{
            ircl.setRefreshing(false);
            if(mStartPage>1){
                ircl.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
            }else{
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
            }
        }
    }

    @Override
    public void returnCarFlowPosition(CarLatLngBean latLng) {

    }

    @Override
    public void returnOrderStateLogList(List<CarRecordsBean> carRecordsBeans) {

    }

    @Override
    public void returnOrderMarkAddBean(String str) {

    }

    @Override
    public void returnOrderMarkModelBean(MarkerDetailsBean str) {

    }

    @Override
    public void drawTravel(TravelListBean travelListBeanList, int type, int state, int alllistsize) {

    }
}