package com.ttce.vehiclemanage.ui.main.fragment.workbean.jcxx.ygxx;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.google.gson.JsonArray;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.LoginBean;
import com.ttce.vehiclemanage.bean.OrderSelectBean;
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

/**
 * 已修改
 * */
public class InformationAuditFragment extends BaseFragment<InformationAuditPresenter, InformationAuditModel> implements InformationAuditContract.View, OnRefreshListener, OnLoadMoreListener {
    private InformationAuditAdapter itemAdapter;
    private String type="";
    private ArrayList<Integer> escapeOrderState=new ArrayList<>();
    private int mStartPage = 1;

    @Bind(R.id.irc1)
    IRecyclerView ircl;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_yc_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        ircl.setHasFixedSize(true);
        ircl.setNestedScrollingEnabled(false);
        ircl.setOnRefreshListener(this);
        ircl.setOnLoadMoreListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(String str) {
        if(str.equals("刷新列表")){
            loadData();
        }
    }
    String StartCreateTime="",EndCreateTime="",  KeyWord="", IDNumber="";
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //清空粘性事件的缓存
        EventBus.getDefault().removeAllStickyEvents();
    }

    public static InformationAuditFragment newInstance(String type, List<Integer> escapeOrderStatelist) {
        InformationAuditFragment myOrderListFragment = new InformationAuditFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putIntegerArrayList("escapeOrderState", (ArrayList<Integer>) escapeOrderStatelist);
        myOrderListFragment.setArguments(bundle);
        return myOrderListFragment;
    }
    JsonArray mescapeOrderStatelist;
    @Override
    protected void loadData() {
        if (getArguments() != null) {
            type = getArguments().getString("type");
            escapeOrderState = getArguments().getIntegerArrayList("escapeOrderState");
        }
        mescapeOrderStatelist= new JsonArray();
        for (Integer ints:escapeOrderState) {
            mescapeOrderStatelist.add(ints);
        }

        // 1:待审 2：已审
        String userJson = SPDefaultHelper.getString(SPDefaultHelper.USER);
        LoginBean user = SerializeUtil.deSerialize(userJson, LoginBean.class);
        if (user != null) {
            mPresenter.getBusinessStaffCheckList(type,mStartPage,StartCreateTime,EndCreateTime,KeyWord,IDNumber);
        }
        itemAdapter = new InformationAuditAdapter(getActivity(), R.layout.adapter_information_audit_item, new ArrayList<>(),type);
        mStartPage = 1;
        itemAdapter.openLoadAnimation(new ScaleInAnimation());
        ircl.setLayoutManager(new LinearLayoutManager(getContext()));
        ircl.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                InformationAuditBean informationAuditBean= (InformationAuditBean) o;
                if(type.equals("1")){
                    NewPersonDetailActivity.goToPage(getActivity(),-1,informationAuditBean.getStaffId(),informationAuditBean.getUserId(),informationAuditBean.getCompanyId());
                }else{
                    NewPersonDetailActivity.goToPage(getActivity(),-2,informationAuditBean.getStaffId(),informationAuditBean.getUserId(),informationAuditBean.getCompanyId());
                }

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