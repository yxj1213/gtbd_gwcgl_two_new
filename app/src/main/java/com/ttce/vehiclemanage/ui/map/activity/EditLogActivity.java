package com.ttce.vehiclemanage.ui.map.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.SetBean;
import com.ttce.vehiclemanage.ui.map.fragment.EditLogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 、
 * 操作日志
 * Created by hk on 2019/6/25.
 */

public class EditLogActivity extends BaseActivity {

    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.dismiss_layout)
    FrameLayout dissmissFrameLayout;
    @Bind(R.id.tv_czrz)
    TextView tvCzrz;
    @Bind(R.id.tv_lxzl)
    TextView tvLxzl;
    @Bind(R.id.ll_all)
    LinearLayout llAll;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    private String DeviceId;
    private String PlateNumber;
    EditLogFragment dissmissFragment;
    private EditLogFragment qbFragment, cgFragment, sbFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_log;
    }

    @Override
    public void initPresenter() {

    }
    @Override
    public void initView() {
        titleBarTitle.setText("指令日志");
        etSearch.setHint("请输入车牌号");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EditLogFragment.newInstance("", DeviceId, PlateNumber));
        fragmentList.add(EditLogFragment.newInstance("1", DeviceId, PlateNumber));
        fragmentList.add(EditLogFragment.newInstance("0", DeviceId, PlateNumber));

//        dissmissFragment = EditLogFragment.newInstance("3", DeviceId, PlateNumber);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.dismiss_layout, dissmissFragment);
//        transaction.commit();
        ///""：全部
        /// 0：失败
        /// 1：成功
        List<String> titleList = new ArrayList();
        titleList.add("全部");
        titleList.add("成功");
        titleList.add("失败");

        for (int i = 0; i < titleList.size(); i++) {
            tabs.addTab(tabs.newTab().setText(titleList.get(i)));
        }

        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(baseFragmentAdapter);
        viewPager.setOffscreenPageLimit(1);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabIndicatorFullWidth(false);
        setCenter();
    }

    @OnClick(R.id.iv_left)
    public void onViewClicked() {
        finish();
    }

    private void setCenter() {
        tvCzrz.setSelected(true);
        tvLxzl.setSelected(false);
        llAll.setVisibility(View.VISIBLE);
        dissmissFrameLayout.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_czrz, R.id.tv_lxzl, R.id.tv_search,R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_czrz:
                setCenter();
                break;
            case R.id.tv_lxzl:
                tvCzrz.setSelected(false);
                tvLxzl.setSelected(true);
                llAll.setVisibility(View.GONE);
                dissmissFrameLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_search:
                PlateNumber = etSearch.getText().toString();
                String type=null;
                switch (tabs.getSelectedTabPosition()){
                    case 0:
                        type="";
                        break;
                    case 1:
                        type="1";
                        break;
                    case 2:
                        type="0";
                        break;
                }
                EventBus.getDefault().post(new SetBean(PlateNumber,type));
                break;
        }
    }
}
