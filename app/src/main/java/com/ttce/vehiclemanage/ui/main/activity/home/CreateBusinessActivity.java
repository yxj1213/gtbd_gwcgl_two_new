package com.ttce.vehiclemanage.ui.main.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;
import com.ttce.vehiclemanage.ui.main.adapter.home.CompanyTypeListAdapter;
import com.ttce.vehiclemanage.ui.main.contract.home.HomeLeftContract;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.ui.main.model.home.HomeLeftModel;
import com.ttce.vehiclemanage.ui.main.presenter.home.HomeLeftPresenter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment.isRef;

/**
 * 已修改
 */
public class CreateBusinessActivity extends BaseActivity<HomeLeftPresenter, HomeLeftModel> implements HomeLeftContract.View {

    @Bind(R.id.irc1)
    IRecyclerView ircl;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.title_bar_layout)
    RelativeLayout title_bar_layout;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.title_bar_title)
    TextView title_bar_title;

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, CreateBusinessActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyNeedCarFragment.isRef=true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                isRef=true;
                finish();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.mainleft_create_business;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    CompanyTypeListAdapter itemAdapter;

    @Override
    public void initView() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.app_main_colors));
        title_bar_layout.setBackground(getResources().getDrawable(R.drawable.titlebar));
        img_back.setImageResource(R.mipmap.icon_back_white);
        title_bar_title.setText("选择企业类型");
        title_bar_title.setTextColor(getResources().getColor(R.color.white));

        mPresenter.getcompanyTypeListPresenter();

        itemAdapter = new CompanyTypeListAdapter(this, R.layout.company_type_list_item, new ArrayList<>());
        itemAdapter.openLoadAnimation(new ScaleInAnimation());
        ircl.setLayoutManager(new LinearLayoutManager(this));
        ircl.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                CreateBusiness2Activity.goToPage(CreateBusinessActivity.this, ((CompanyTypeListBean) o).getBusiness_CompanyTypeId(),"", "新增");
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }

    @Override
    public void returnCompanyTypeList(List<CompanyTypeListBean> companyTypeListBeans) {
        if (companyTypeListBeans != null && companyTypeListBeans.size() > 0) {
            itemAdapter.replaceAll(companyTypeListBeans);
        } else {
            loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
        }
    }

    @Override
    public void returnbusinessComPanyAdd(String str) {

    }

    @Override
    public void getSaveChangeCompanys(ChangeCompanySaveReturnBean str) {

    }

    @Override
    public void returnbusinessComPanyAdd(ChangeCompanyBean str) {

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
}