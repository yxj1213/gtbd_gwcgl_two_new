package com.ttce.vehiclemanage.ui.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.SosBean;
import com.ttce.vehiclemanage.ui.mine.adapter.SosAdapter;
import com.ttce.vehiclemanage.ui.mine.constract.SosListConstract;
import com.ttce.vehiclemanage.ui.mine.model.SosModel;
import com.ttce.vehiclemanage.ui.mine.presenter.SosPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/21.
 */

public class SosActivity extends BaseActivity<SosPresenter, SosModel> implements SosListConstract.View, OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    private List<SosBean> datas = new ArrayList<>();
    private int mStartPage = 1;
    private SosAdapter sosAdapter;

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, SosActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sos;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.sos_add);
        titleBarTitle.setText("SOS报警");
        irc.setLayoutManager(new LinearLayoutManager(this));
        datas.clear();
        sosAdapter = new SosAdapter(SosActivity.this, datas);
        sosAdapter.openLoadAnimation(new ScaleInAnimation());
        irc.setAdapter(sosAdapter);
        irc.setOnRefreshListener(this);
        irc.setOnLoadMoreListener(this);
        //数据为空才重新发起请求
        if (sosAdapter.getSize() <= 0) {
            mStartPage = 1;
            mPresenter.getSosList(mStartPage);
        }
    }

    @OnClick({R.id.title_bar_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.iv_right:
                Intent intent=new Intent(this, SubmitSosActivity.class);
                startActivityForResult(intent,AppConstant.REQUEST_EDIT_WEILAN);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case AppConstant.REQUEST_EDIT_WEILAN:
                if(resultCode==RESULT_OK){
                    onRefresh();
                }
                break;
        }
    }
    @Override
    public void onRefresh() {
        sosAdapter.getPageBean().setRefresh(true);
        mStartPage = 1;
        //发起请求
        irc.setRefreshing(true);
        mPresenter.getSosList(mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mStartPage++;
        sosAdapter.getPageBean().setRefresh(false);
        //发起请求
        irc.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getSosList(mStartPage);
    }

    @Override
    public void showLoading(String title) {
        if (sosAdapter.getPageBean().isRefresh()) {
            if (sosAdapter.getSize() <= 0) {
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
        if (sosAdapter.getPageBean().isRefresh()) {
            if (sosAdapter.getSize() <= 0) {
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
                loadedTip.setTips(msg);
            }
            irc.setRefreshing(false);
        } else {
            irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
        }
    }

    @Override
    public void returnList(List<SosBean> dataList) {
        if (dataList!=null&&dataList.size()>0||sosAdapter.getAll().size()>0) {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.haveDate);
            if (sosAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                sosAdapter.replaceAll(dataList);
            } else {
                if (dataList.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    sosAdapter.addAll(dataList);
                } else {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        } else {
            irc.setRefreshing(false);
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
        }
    }
}
