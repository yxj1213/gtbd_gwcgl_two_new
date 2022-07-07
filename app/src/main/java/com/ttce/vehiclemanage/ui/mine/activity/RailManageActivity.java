package com.ttce.vehiclemanage.ui.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.CarDetailBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.locus.activity.DeptActivity;
import com.ttce.vehiclemanage.ui.map.activity.AddRailActivity;
import com.ttce.vehiclemanage.ui.mine.adapter.RailManageAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.RailManageContract;
import com.ttce.vehiclemanage.ui.mine.model.RailManageModel;
import com.ttce.vehiclemanage.ui.mine.presenter.RailManagePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 围栏管理
 * Created by hk on 2019/6/20.
 */

public class RailManageActivity extends BaseActivity<RailManagePresenter, RailManageModel> implements RailManageContract.View, OnRefreshListener, OnLoadMoreListener, RailManageAdapter.RailListenter {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<FenceListBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private RailManageAdapter railManageAdapter;
    private String DeviceId;

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, RailManageActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rail_manage;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("围栏管理");
        irc.setLayoutManager(new LinearLayoutManager(this));
        datas.clear();
        railManageAdapter = new RailManageAdapter(RailManageActivity.this, datas, this);
        railManageAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(railManageAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if (railManageAdapter.getSize() <= 0) {
            mStartPage = 1;
            mPresenter.getNewFenceList(mStartPage);
        }
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showLoading(String title) {
        if (railManageAdapter.getPageBean().isRefresh()) {
            if (railManageAdapter.getSize() <= 0) {
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
        if (railManageAdapter.getPageBean().isRefresh()) {
            if (railManageAdapter.getSize() <= 0) {
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
        if (dataList != null&&dataList.size()>0||railManageAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (railManageAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                railManageAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    railManageAdapter.addAll(dataList);
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
        railManageAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getNewFenceList(mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        railManageAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getNewFenceList(mStartPage);
    }

    /***
     * 编辑围栏
     * @param fenceListBean
     */
    @Override
    public void updateRail(FenceListBean fenceListBean) {
        Intent intent=new Intent(this,AddRailActivity.class);
        intent.putExtra("updateDevice",(Serializable) fenceListBean);
        startActivityForResult(intent,AppConstant.REQUEST_EDIT_WEILAN);
    }

    /***
     * 添加设备
     * @param fenceListBean
     */
    @Override
    public void addRail(FenceListBean fenceListBean) {
        Intent intent=new Intent(this,DeptActivity.class);
        intent.putExtra("Device",(Serializable) fenceListBean);
        startActivityForResult(intent,AppConstant.REQUEST_SELECT_DEVICE);
    }

    @Override
    public void delRail(FenceListBean fenceListBean) {
        MaterialDialog dialog = new MaterialDialog.Builder(this).theme(Theme.LIGHT).
                title("提示").content("确定删除吗？").negativeColorRes(R.color.blue_color1)
                .negativeText("取消").positiveText("确认").onPositive(new MaterialDialog
                        .SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull
                            DialogAction which) {
                            mPresenter.delRail(fenceListBean.getId());
                    }
                })
                .build();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void showCars(FenceListBean fenceListBean) {
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
    public void delRail(String id,Boolean isDel) {
        if(isDel){
            FenceListBean delbean=new FenceListBean();
            delbean.setId(id);
            railManageAdapter.removeAt(datas.indexOf(delbean));
            showTipDialog("删除成功",LoadingTip.LoadStatus.finish,R.color.blue_color1);
            mStartPage = 1;
            mPresenter.getNewFenceList(mStartPage);
        }else{
            showTipDialog("删除失败",LoadingTip.LoadStatus.error,R.color.blue_color1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case AppConstant.REQUEST_SELECT_DEVICE:
                if(resultCode==RESULT_OK && data!=null){
                    String selectedDeviceId=data.getStringExtra("DeviceId");
                    FenceListBean fenceListBean=(FenceListBean)data.getSerializableExtra("Device");
                    if(fenceListBean!=null&& !TextUtils.isEmpty(selectedDeviceId)){
                            if(!fenceListBean.getDevices().contains(selectedDeviceId)){
                                List<String> addIdList=new ArrayList<>();
                                addIdList.add(selectedDeviceId);
                                mPresenter.addDevice(fenceListBean.getId(),new Gson().toJson(addIdList));
                            } else{
                                showTipDialog("设备已存在",LoadingTip.LoadStatus.error,R.color.blue_color1);
                            }
                    }
                }
                break;
            case AppConstant.REQUEST_EDIT_WEILAN:
                if(resultCode==RESULT_OK){
                    onRefresh();
                }
                break;
        }
    }

    @Override
    public void addDevice(String id, Boolean isAdd) {
        if(isAdd){
            onRefresh();
            showTipDialog("添加成功",LoadingTip.LoadStatus.finish,R.color.blue_color1);
        }else{
            showTipDialog("添加失败",LoadingTip.LoadStatus.error,R.color.blue_color1);
        }
    }
}
