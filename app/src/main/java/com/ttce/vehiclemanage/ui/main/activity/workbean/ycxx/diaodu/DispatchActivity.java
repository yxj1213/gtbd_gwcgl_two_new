package com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.TabEntity;
import com.ttce.vehiclemanage.ui.main.fragment.my.MyOrderListFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 调度派车
 * */
public class DispatchActivity extends BaseActivity {
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"全部", "待派车", "已派车", "已驳回"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MyOrderListFragment qbFragment, dpcFragment, ypcFragment, ybhFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initPresenter() {

    }



    @Override
    public void initView() {
        titleBarTitle.setText(getResources().getString(R.string.me_txt8));
        initTab();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            qbFragment = (MyOrderListFragment) getSupportFragmentManager().findFragmentByTag("qb");
            dpcFragment = (MyOrderListFragment) getSupportFragmentManager().findFragmentByTag("dpc");
            ypcFragment = (MyOrderListFragment) getSupportFragmentManager().findFragmentByTag("ypc");
            ybhFragment = (MyOrderListFragment) getSupportFragmentManager().findFragmentByTag("ybh");
        } else {
            Bundle bundleQb = new Bundle();
            bundleQb.putString("type",mContext.getString(R.string.type3));
            bundleQb.putString("escapeOrderState","");
            qbFragment = new MyOrderListFragment();
            qbFragment.setArguments(bundleQb);

            Bundle bundledpc = new Bundle();
            bundledpc.putString("type",mContext.getString(R.string.type3));
            bundledpc.putString("escapeOrderState",mContext.getString(R.string.type3_1));
            dpcFragment = new MyOrderListFragment();
            dpcFragment.setArguments(bundledpc);

            Bundle bundleypc = new Bundle();
            bundleypc.putString("type",mContext.getString(R.string.type3));
            bundleypc.putString("escapeOrderState",mContext.getString(R.string.type3_2));
            ypcFragment = new MyOrderListFragment();
            ypcFragment.setArguments(bundleypc);

            Bundle bundleybh = new Bundle();
            bundleybh.putString("type",mContext.getString(R.string.type3));
            bundleybh.putString("escapeOrderState",mContext.getString(R.string.type3_3));
            ybhFragment = new MyOrderListFragment();
            ybhFragment.setArguments(bundleybh);

            transaction.add(R.id.fl_body, qbFragment, "qb");
            transaction.add(R.id.fl_body, dpcFragment, "dpc");
            transaction.add(R.id.fl_body, ypcFragment, "ypc");
            transaction.add(R.id.fl_body, ybhFragment, "ybh");
        }
        transaction.commit();
        switchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }



    private void switchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(qbFragment);
                transaction.hide(dpcFragment);
                transaction.hide(ypcFragment);
                transaction.hide(ybhFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 1:
                transaction.hide(qbFragment);
                transaction.show(dpcFragment);
                transaction.hide(ypcFragment);
                transaction.hide(ybhFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(qbFragment);
                transaction.hide(dpcFragment);
                transaction.show(ypcFragment);
                transaction.hide(ybhFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(qbFragment);
                transaction.hide(dpcFragment);
                transaction.hide(ypcFragment);
                transaction.show(ybhFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case AppConstant.REQUEST_ADD_APPLY_ORDER:
                if (resultCode == RESULT_OK) {
                    //qbFragment.loadData();
                }
                break;
        }
    }

    public static void goToPage(Activity activity) {
        Intent intent = new Intent(activity, DispatchActivity.class);
        activity.startActivity(intent);
    }
}
