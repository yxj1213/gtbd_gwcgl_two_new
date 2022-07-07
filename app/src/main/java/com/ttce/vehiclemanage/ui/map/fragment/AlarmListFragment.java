package com.ttce.vehiclemanage.ui.map.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.ui.map.activity.AlarmDetailActivity;
import com.ttce.vehiclemanage.ui.map.adapter.AlarmListAdapter;
import com.ttce.vehiclemanage.ui.map.contract.AlarmListContract;
import com.ttce.vehiclemanage.ui.map.model.AlarmListModel;
import com.ttce.vehiclemanage.ui.map.presenter.AlarmListPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 告警列表
 * Created by hk on 2019/7/5.
 */

public class AlarmListFragment extends BaseFragment<AlarmListPresenter, AlarmListModel> implements AlarmListContract.View, OnRefreshListener, OnLoadMoreListener, OnItemClickListener, AlarmListAdapter.AlarmListenter {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<AlarmListBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private AlarmListAdapter alarmListAdapter;
    private int type;
    private String DeviceId;

    public static AlarmListFragment newInstance(int type, String DeviceId) {
        AlarmListFragment alarmListFragment = new AlarmListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("DeviceId", DeviceId);
        alarmListFragment.setArguments(bundle);
        return alarmListFragment;
    }

    @Override
    protected void loadData() {
        type = getArguments().getInt("type");
        DeviceId = getArguments().getString("DeviceId");
        mStartPage = 1;
        mPresenter.getAlarmList(type, DeviceId, mStartPage);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_alarm_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }



    @Override
    protected void initView() {
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        alarmListAdapter = new AlarmListAdapter(getContext(), datas, this);
        alarmListAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(alarmListAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        alarmListAdapter.setOnItemClickListener(this);
//        type = getArguments().getInt("type");
//        DeviceId = getArguments().getString("DeviceId");
//        //数据为空才重新发起请求
//        if (alarmListAdapter.getSize() <= 0) {
//            mStartPage = 1;
//            mPresenter.getAlarmList(type, DeviceId, mStartPage);
//        }

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        if (alarmListAdapter.getPageBean().isRefresh()) {
            if (alarmListAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void getAlarmList(List<AlarmListBean> dataList) {
        if (dataList != null&&dataList.size()>0||alarmListAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (alarmListAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                alarmListAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    alarmListAdapter.addAll(dataList);
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
    public void updateState(String id) {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        alarmListAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getAlarmList(type, DeviceId, mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        alarmListAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getAlarmList(type, DeviceId, mStartPage);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        Intent intent = new Intent(getActivity(), AlarmDetailActivity.class);
        intent.putExtra("data", (Serializable) datas.get(position));
        getActivity().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }



    @Override
    public void updateAlarmType(AlarmListBean alarmListBean) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity()).title("处理内容")
                .widgetColorRes(R.color.blue_color1)// 输入框光标的颜色
                .customView(R.layout.view_audio_dialog, false).positiveText("确定").positiveColorRes(R.color.blue_color1)
                .negativeText("取消").negativeColorRes(R.color.text_grey_color)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            View view = dialog.getCustomView();
                            EditText etReason = view.findViewById(R.id.et_reason);

                            String reason = etReason.getText().toString();
                            if (TextUtils.isEmpty(reason)) {
                                ToastUtil.showToast("请输入内容");
                                return;
                            }
                            mPresenter.updateState(alarmListBean.getDeviceId(), reason);
                        }
                    }
                }).build();
        materialDialog.show();
    }
}
