package com.ttce.vehiclemanage.ui.mine.activity;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.mine.fragment.AlarmMessageFragment;
import com.ttce.vehiclemanage.ui.mine.fragment.SystemMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 消息通知
 * Created by hk on 2019/6/24.
 */

public class MessageNoticeActivity extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_notice;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBarTitle.setText("消息通知");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AlarmMessageFragment.newInstance());
        fragmentList.add(SystemMessageFragment.newInstance());

        List<String> titleList = new ArrayList();
        titleList.add("告警消息");
        titleList.add("系统消息");

        for (int i = 0; i < titleList.size(); i++) {
            tabs.addTab(tabs.newTab().setText(titleList.get(i)));
        }
        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(baseFragmentAdapter);
        viewPager.setOffscreenPageLimit(1);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabIndicatorFullWidth(false);
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }
}
