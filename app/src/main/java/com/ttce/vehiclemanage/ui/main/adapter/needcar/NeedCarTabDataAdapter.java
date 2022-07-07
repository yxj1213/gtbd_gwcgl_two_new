package com.ttce.vehiclemanage.ui.main.adapter.needcar;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ttce.vehiclemanage.bean.EmptyOrderBean;

import java.util.ArrayList;
import java.util.List;

public class NeedCarTabDataAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private List<EmptyOrderBean.CarStyleListBean> mTitles;

    public NeedCarTabDataAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, List<EmptyOrderBean.CarStyleListBean> title) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = title;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}