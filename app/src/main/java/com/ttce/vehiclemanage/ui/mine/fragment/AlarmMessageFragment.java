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
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.ui.mine.adapter.AlarmMessageAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.AlarmMessageConstract;
import com.ttce.vehiclemanage.ui.mine.model.AlarmMessageModel;
import com.ttce.vehiclemanage.ui.mine.presenter.AlarmMessagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by hk on 2019/6/25.
 */

public class AlarmMessageFragment extends BaseFragment<AlarmMessagePresenter, AlarmMessageModel> implements AlarmMessageConstract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.irc1)
    IRecyclerView irc;
    @Bind(R.id.loadedTip1)
    LoadingTip loadedTip;

    private List<AlarmListBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private AlarmMessageAdapter alarmMessageAdapter;
    private int type;
    private String DeviceId;

    public static AlarmMessageFragment newInstance() {
        AlarmMessageFragment alarmMessageFragment = new AlarmMessageFragment();
        return alarmMessageFragment;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_alarm_message;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        alarmMessageAdapter = new AlarmMessageAdapter(getContext(), R.layout.adapter_alarm_message, datas);
        alarmMessageAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(alarmMessageAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if (alarmMessageAdapter.getSize() <= 0) {
            mStartPage = 1;
            mPresenter.getAlarmList(type, DeviceId, mStartPage);
        }
    }

    @Override
    public void showLoading(String title) {
        if (alarmMessageAdapter.getPageBean().isRefresh()) {
            if (alarmMessageAdapter.getSize() <= 0) {
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
        if (alarmMessageAdapter.getPageBean().isRefresh()) {
            if (alarmMessageAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void returnData(List<AlarmListBean> dataList) {
        if (dataList != null&&dataList.size()>0||alarmMessageAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (alarmMessageAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                alarmMessageAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    alarmMessageAdapter.addAll(dataList);
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
        alarmMessageAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getAlarmList(type, DeviceId, mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        alarmMessageAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getAlarmList(type, DeviceId, mStartPage);
    }
}
