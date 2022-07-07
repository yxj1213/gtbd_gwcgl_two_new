package com.ttce.vehiclemanage.ui.map.fragment;

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
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.bean.SetBean;
import com.ttce.vehiclemanage.ui.map.activity.InstructDetailActivity;
import com.ttce.vehiclemanage.ui.map.adapter.EditLogAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.InstructListConstract;
import com.ttce.vehiclemanage.ui.mine.model.InstructListModel;
import com.ttce.vehiclemanage.ui.mine.presenter.InstructListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 操作日志
 * Created by hk on 2019/6/25.
 */

public class EditLogFragment extends BaseFragment<InstructListPresenter, InstructListModel> implements InstructListConstract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<InstructDetailBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private EditLogAdapter editLogAdapter;
    private String type;
    private String DeviceId;
    private String PlateNumber;

    public static EditLogFragment newInstance(String type, String DeviceId, String PlateNumber) {
        EditLogFragment editLogFragment = new EditLogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("DeviceId", DeviceId);
        bundle.putString("PlateNumber", PlateNumber);
        editLogFragment.setArguments(bundle);
        return editLogFragment;
    }

    @Override
    protected void loadData() {
        mStartPage = 1;
        type = getArguments().getString("type");
        PlateNumber = getArguments().getString("PlateNumber");
        DeviceId = getArguments().getString("DeviceId");
        mPresenter.getInstructList(type, DeviceId, PlateNumber, mStartPage);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_edit_log;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        irc.setLayoutManager(new LinearLayoutManager(getContext()));
        datas.clear();
        editLogAdapter = new EditLogAdapter(getContext(), R.layout.adapter_edit_log, datas);
        editLogAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(editLogAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
//        type = getArguments().getString("type");
//        PlateNumber = getArguments().getString("PlateNumber");
//        DeviceId = getArguments().getString("DeviceId");
//        //数据为空才重新发起请求
//        if (editLogAdapter.getSize() <= 0) {
//            mStartPage = 1;
//            mPresenter.getInstructList(type, DeviceId, PlateNumber, mStartPage);
//        }
        editLogAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                InstructDetailActivity.goToPage(getActivity(), ((InstructDetailBean) o).getID());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }
    @Override
    public void showLoading(String title) {
        if (editLogAdapter.getPageBean().isRefresh()) {
            if (editLogAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.loading);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SetBean event) {
        if(event.getPages().equals(type)){
            PlateNumber = event.getNumber();
            datas.clear();
            editLogAdapter.notifyDataSetChanged();
            onRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void stopLoading() {
        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
    }

    @Override
    public void showErrorTip(String msg) {
        if (editLogAdapter.getPageBean().isRefresh()) {
            if (editLogAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void returnData(List<InstructDetailBean> dataList) {
        if (dataList!=null&&dataList.size()>0||editLogAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (editLogAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                editLogAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    editLogAdapter.addAll(dataList);
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

        editLogAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        irc.setRefreshing(true);
        //发起请求
        mPresenter.getInstructList(type, DeviceId, PlateNumber, mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        editLogAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getInstructList(type, DeviceId, PlateNumber, mStartPage);
    }
}
