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
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.OrderSelectBean;
import com.ttce.vehiclemanage.ui.main.activity.my.order.OrderSelectActivity;
import com.ttce.vehiclemanage.ui.main.adapter.workbean.jcxx.ygxx.InformationAuditAdapter;
import com.ttce.vehiclemanage.ui.main.contract.workbean.InformationAuditContract;
import com.ttce.vehiclemanage.ui.main.model.workbean.InformationAuditModel;
import com.ttce.vehiclemanage.ui.main.presenter.workbean.InformationAuditPresenter;
import com.ttce.vehiclemanage.ui.mine.activity.NewPersonDetailActivity;
import com.ttce.vehiclemanage.utils.SerializeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改 我的订单
 */
public class ShenHeToDoListActivity extends BaseActivity<InformationAuditPresenter, InformationAuditModel> implements InformationAuditContract.View, OnRefreshListener, OnLoadMoreListener {

    private InformationAuditAdapter itemAdapter;
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
                OrderSelectActivity.goToPage(this,"信息审核");
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


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(String str) {
        if(str.equals("刷新列表")){
            loadData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时必须调用mMapView. onDestroy ()
        EventBus.getDefault().unregister(this);
        //清空粘性事件的缓存
        EventBus.getDefault().removeAllStickyEvents();
    }
    public static void goToPage(Activity activity,String type) {
        Intent intent = new Intent(activity, ShenHeToDoListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(OrderSelectBean orderSelectBean) {
        if(orderSelectBean.getType().equals("信息审核")){
            StartCreateTime=orderSelectBean.getStartTime();
            EndCreateTime=orderSelectBean.getEndTime();
            KeyWord=orderSelectBean.getNameOrPhone();
            IDNumber=orderSelectBean.getOrderId();
            loadData();
        }
    }


    String StartCreateTime="",EndCreateTime="",  KeyWord="", IDNumber="";
    protected void loadData() {
        type =this.getIntent().getExtras().getString("type");

        String userJson = SPDefaultHelper.getString(SPDefaultHelper.USER);
        LoginBean user = SerializeUtil.deSerialize(userJson, LoginBean.class);
        if (user != null) {
            mPresenter.getBusinessStaffCheckList(type,mStartPage,StartCreateTime,EndCreateTime,KeyWord,IDNumber);
        }
        title_bar_title.setText(getResources().getString(R.string.need_car_1_1));

        itemAdapter = new InformationAuditAdapter(this, R.layout.adapter_information_audit_item, new ArrayList<>(),type);
        mStartPage = 1;
        itemAdapter.openLoadAnimation(new ScaleInAnimation());
        ircl.setLayoutManager(new LinearLayoutManager(this));
        ircl.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                InformationAuditBean informationAuditBean= (InformationAuditBean) o;
                NewPersonDetailActivity.goToPage(ShenHeToDoListActivity.this,-1,informationAuditBean.getStaffId(),informationAuditBean.getUserId(),informationAuditBean.getCompanyId());
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
        //发起请求
        ircl.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);

        mPresenter.getBusinessStaffCheckList(type,mStartPage,StartCreateTime,EndCreateTime,KeyWord,IDNumber);
    }

    @Override
    public void onRefresh() {
        itemAdapter.getPageBean().setRefresh(true);
        ircl.setRefreshing(true);
        mStartPage = 1;

        //发起请求
        mPresenter.getBusinessStaffCheckList(type,mStartPage,StartCreateTime,EndCreateTime,KeyWord,IDNumber);
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
    public void returnBusinessStaffCheckList(List<InformationAuditBean> informationAuditBeans) {
        if(informationAuditBeans!=null&&informationAuditBeans.size()>0||itemAdapter.getAll().size()>0){
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (itemAdapter.getPageBean().isRefresh()) {
                ircl.setRefreshing(false);
                itemAdapter.replaceAll(informationAuditBeans);
            } else {
                if (informationAuditBeans.size() > 0) {
                    ircl.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    itemAdapter.addAll(informationAuditBeans);
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
}