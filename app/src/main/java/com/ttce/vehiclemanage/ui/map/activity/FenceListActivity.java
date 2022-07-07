package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.CarDetailBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.map.adapter.FenceListAdapter;
import com.ttce.vehiclemanage.ui.map.contract.FenceListContract;
import com.ttce.vehiclemanage.ui.map.model.FenceListModel;
import com.ttce.vehiclemanage.ui.map.presenter.FenceListPresenter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 围栏列表
 * Created by hk on 2019/7/9.
 */

public class FenceListActivity extends BaseActivity<FenceListPresenter, FenceListModel> implements FenceListContract.View, OnRefreshListener, OnLoadMoreListener, FenceListAdapter.FenceListenter {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView addFenceIV;
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<FenceListBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private FenceListAdapter fenceListAdapter;
    public static void goToPage(Activity activity,String currentData) {
        Intent intent = new Intent(activity, FenceListActivity.class);
        intent.putExtra("currentData", currentData);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fence_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    MonitorResponseBean currentData;
    @Override
    public void initView() {
        titleBarTitle.setText("围栏列表");
        addFenceIV.setImageResource(R.mipmap.ico_weilan_add);
        addFenceIV.setVisibility(View.VISIBLE);
        String JsonData = getIntent().getStringExtra("currentData");
        currentData = new Gson().fromJson(JsonData,MonitorResponseBean.class);

        irc.setLayoutManager(new LinearLayoutManager(this));
        datas.clear();
        fenceListAdapter = new FenceListAdapter(FenceListActivity.this, datas, this);
        fenceListAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(fenceListAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        mStartPage = 1;
        mPresenter.getFenceList(currentData.getDeviceId(), mStartPage);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void updateAlarmType(FenceListBean fenceListBean, boolean checked, int type) {
        List<Integer> alarmTypeList = new ArrayList<>(Arrays.asList(fenceListBean.getAlarmTypes()));
        if (alarmTypeList != null) {
            if (checked) {
                if (!alarmTypeList.contains(new Integer(type))) {
                    alarmTypeList.add(new Integer(type));
                }
            } else {
                if (alarmTypeList.contains(new Integer(type))) {
                    alarmTypeList.remove(new Integer(type));
                }
            }
        } else {
            alarmTypeList = new ArrayList<>();
        }
        mPresenter.updateFenceAlarmType(fenceListBean, currentData.getDeviceId(), new Gson().toJson(alarmTypeList));
    }

    @Override
    public void onCarListInfo(FenceListBean fenceListBean) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mContext).title("车辆列表")
                .widgetColorRes(R.color.blue_color1)// 输入框光标的颜色
                .customView(R.layout.dialog_car_list_layout, false).positiveText("确定").positiveColorRes(R.color.blue_color1).build();
        LinearLayout linearLayout = (LinearLayout) materialDialog.findViewById(R.id.ll_content);
        if (fenceListBean.getCarDetails() != null) {
            for (CarDetailBean carDetail : fenceListBean.getCarDetails()) {
                View view = View.inflate(this, R.layout.layout_car_item, null);
                TextView textView = view.findViewById(R.id.tv_carname);
                textView.setText(carDetail.getCarNub());
                view.findViewById(R.id.iv_close).setVisibility(View.GONE);
                linearLayout.addView(view);
            }
        }
        materialDialog.show();
    }

    @Override
    public void onFenceAlarmTypeUpdate(FenceListBean fenceListBean, String alarmTypes, boolean result) {
        if (result) {
            FenceListBean oldItem = new FenceListBean();
            oldItem.setId(fenceListBean.getId());
            Type type = new TypeToken<List<Integer>>() {
            }.getType();
            List<Integer> alarmTypeList = new Gson().fromJson(alarmTypes, type);
            fenceListBean.setAlarmTypes(alarmTypeList.toArray(new Integer[alarmTypeList.size()]));
            fenceListAdapter.replace(oldItem, fenceListBean);
        }
    }

    @OnClick({R.id.title_bar_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_right:
                AddRailActivity.goToPage(this,new Gson().toJson(currentData), AppConstant.REQUEST_ADD_FENCE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case AppConstant.REQUEST_ADD_FENCE:
                if (resultCode == RESULT_OK) {
                    mStartPage = 1;
                    mPresenter.getFenceList(currentData.getDeviceId(), mStartPage);
                }
                break;
        }
    }

    @Override
    public void showErrorTip(String msg) {
        if (fenceListAdapter.getPageBean().isRefresh()) {
            if (fenceListAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }

    @Override
    public void getFenceList(List<FenceListBean> dataList) {
        if (dataList != null&&dataList.size()>0||fenceListAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (fenceListAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                fenceListAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    fenceListAdapter.addAll(dataList);
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
        fenceListAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getFenceList(currentData.getDeviceId(), mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        fenceListAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getFenceList(currentData.getDeviceId(), mStartPage);
    }
}
