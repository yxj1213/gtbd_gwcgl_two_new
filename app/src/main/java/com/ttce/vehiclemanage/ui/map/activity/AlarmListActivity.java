package com.ttce.vehiclemanage.ui.map.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.map.fragment.AlarmListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 告警列表
 * Created by hk on 2019/7/5.
 */

public class AlarmListActivity extends BaseActivity{

    //全部：-1  待处理：0 已处理：1
    public static final int QB = -1;
    public static final int DCL = 0;
    public static final int YCL = 1;

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;

    private String DeviceId;

    @Override
    public void initPresenter() {

    }

    public static void goToPage(Activity activity, String DeviceId) {
        Intent intent = new Intent(activity, AlarmListActivity.class);
        intent.putExtra("DeviceId", DeviceId);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_list;
    }

    @Override
    public void initView() {
        DeviceId = getIntent().getStringExtra("DeviceId");
        titleBarTitle.setText("告警通知");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AlarmListFragment.newInstance(QB, DeviceId));
        fragmentList.add(AlarmListFragment.newInstance(DCL, DeviceId));
        fragmentList.add(AlarmListFragment.newInstance(YCL, DeviceId));

        List<String> titleList = new ArrayList();
        titleList.add("全部");
        titleList.add("待处理");
        titleList.add("已处理");

        for (int i = 0; i < titleList.size(); i++) {
            TabLayout.Tab tab=tabs.newTab();
            tab.setTag(i-1);
            tabs.addTab(tab.setText(titleList.get(i)));
        }

        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(baseFragmentAdapter);
        viewPager.setOffscreenPageLimit(1);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch(view.getId()){
            case R.id.title_bar_back:
                finish();
                break;
        }
    }

}
