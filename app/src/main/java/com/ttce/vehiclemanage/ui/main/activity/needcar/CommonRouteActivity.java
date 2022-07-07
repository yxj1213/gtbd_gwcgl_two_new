package com.ttce.vehiclemanage.ui.main.activity.needcar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.bean.TuJingDiBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.AddressBookAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.RailManageContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改
 * */
public class CommonRouteActivity extends BaseActivity implements RailManageContract.View,OnRefreshListener, OnLoadMoreListener,AddressBookAdapter.AddressBookListenter {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancle)
    TextView tv_cancle;
    @Bind(R.id.tv_gl)
    TextView tv_gl;
    private List<TuJingDiBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private AddressBookAdapter addressBookAdapter;
    private String DeviceId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_book;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        tv_cancle.setVisibility(View.GONE);
        tv_sure.setText(getResources().getString(R.string.ditu9));
        titleBarTitle.setText(getResources().getString(R.string.ditu8));
        irc.setLayoutManager(new LinearLayoutManager(this));


        datas.clear();
        datas.add(new TuJingDiBean("兰亭御湖城","山西省太原市晋源区义井街道",null,"","",""));
        datas.add(new TuJingDiBean("兰亭御湖城","山西省太原市晋源区义井街道",null,"","",""));
        addressBookAdapter = new AddressBookAdapter(CommonRouteActivity.this, R.layout.common_route_item,datas, this,"常用路线");
        addressBookAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(addressBookAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if (addressBookAdapter.getSize() <= 0) {
            mStartPage = 1;
            //mPresenter.getNewFenceList(mStartPage);
        }
    }
    @Override
    public void showLoading(String title) {
        if (addressBookAdapter.getPageBean().isRefresh()) {
            if (addressBookAdapter.getSize() <= 0) {
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
        if (addressBookAdapter.getPageBean().isRefresh()) {
            if (addressBookAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void getNewFenceList(List<FenceListBean> dataList) {
        if (dataList != null&&dataList.size()>0||addressBookAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (addressBookAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                //addressBookAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                   // addressBookAdapter.addAll(dataList);
                } else {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        } else {
            irc.setRefreshing(false);
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
        }
    }

    @Override
    public void delRail(String id, Boolean isDel) {

    }

    @Override
    public void addDevice(String id, Boolean isAdd) {

    }

    @Override
    public void onRefresh() {
        addressBookAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        //mPresenter.getNewFenceList(mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        addressBookAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        //mPresenter.getNewFenceList(mStartPage);
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
    }

    @OnClick({R.id.title_bar_back,R.id.tv_gl,R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_sure:
               AddRouteActivity.goToPage(this);
                break;
            case R.id.tv_gl:
                if(isEdit==false){
                    isEdit=true;
                    tv_gl.setText("完成");
                }else{
                    isEdit=false;
                    tv_gl.setText("管理");
                }
                addressBookAdapter.notifyDataSetChanged();
                break;
        }
    }

    public static boolean isEdit=false;

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, CommonRouteActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void editAddressBook(TuJingDiBean item) {
        DiTuCheckAddressActivity.goToPage(this,mtag,new Gson().toJson(item),"常用路线");
        finish();
    }

    @Override
    public void delAddressBook(TuJingDiBean item) {

    }
}