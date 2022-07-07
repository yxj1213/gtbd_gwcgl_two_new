package com.ttce.vehiclemanage.ui.mine.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.SystemMessageBean;
import com.ttce.vehiclemanage.ui.mine.adapter.SystemMessageAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.SystemMessageConstract;
import com.ttce.vehiclemanage.ui.mine.model.SystemMessageModel;
import com.ttce.vehiclemanage.ui.mine.presenter.SystemMessagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 系统消息
 * Created by hk on 2019/6/24.
 */

public class SystemMessageFragment extends BaseFragment<SystemMessagePresenter, SystemMessageModel> implements SystemMessageConstract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<SystemMessageBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private SystemMessageAdapter systemMessageAdapter;


    public static SystemMessageFragment newInstance() {
        SystemMessageFragment systemMessageFragment = new SystemMessageFragment();
        return systemMessageFragment;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_system_message;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        systemMessageAdapter = new SystemMessageAdapter(getContext(), R.layout.adapter_system_message, datas);
        systemMessageAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(systemMessageAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if (systemMessageAdapter.getSize() <= 0) {
            mStartPage = 1;
            mPresenter.getMessageList(mStartPage);
        }
    }

    @Override
    public void showLoading(String title) {
        if (systemMessageAdapter.getPageBean().isRefresh()) {
            if (systemMessageAdapter.getSize() <= 0) {
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
        if (systemMessageAdapter.getPageBean().isRefresh()) {
            if (systemMessageAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void returnData(List<SystemMessageBean> dataList) {
        if (dataList != null&&dataList.size()>0||systemMessageAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (systemMessageAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                systemMessageAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    systemMessageAdapter.addAll(dataList);
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
    public void onRefresh() {
        systemMessageAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getMessageList(mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        systemMessageAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getMessageList(mStartPage);
    }
}
