package com.ttce.vehiclemanage.ui.main.activity.workbean.ycxx.diaodu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.IsOrderingBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.OrderSuccessBean;
import com.ttce.vehiclemanage.bean.OtherUserId;
import com.ttce.vehiclemanage.ui.main.contract.needcar.MyNeedCarConstract;
import com.ttce.vehiclemanage.ui.main.fragment.workbean.ycxx.diaodu.CheckCarFragment;
import com.ttce.vehiclemanage.ui.main.model.needcar.MyNeedCarModel;
import com.ttce.vehiclemanage.ui.main.presenter.needcar.MyNeedCarPresenter;
import com.ttce.vehiclemanage.widget.TabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CheckCarActivity extends BaseActivity<MyNeedCarPresenter, MyNeedCarModel> implements MyNeedCarConstract.View {
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
 @Bind(R.id.pager)
 ViewPager pager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    List<Fragment> fragmentList;
    List<String> mlist;
    @Override
    public void initView() {
        titleBarTitle.setText("车辆列表");


        mPresenter.getEmptyOrderBeans();
    }
    String type="",order_id="";
    @OnClick({R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
        }
    }



    public static void goToPage(Activity activity,String type,String order_id) {
        Intent intent = new Intent(activity, CheckCarActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("order_id",order_id);
        activity.startActivity(intent);
    }

    @Override
    public void returnEmptyOrderBean(EmptyOrderBean memptyOrderBean) {
        type=this.getIntent().getStringExtra("type");
        order_id=this.getIntent().getStringExtra("order_id");

        fragmentList = new ArrayList<>();
        fragmentList.add(CheckCarFragment.newInstance( "0",new Gson().toJson(memptyOrderBean.getCarStyle_List()),type,order_id));
        fragmentList.add(CheckCarFragment.newInstance("10",new Gson().toJson(memptyOrderBean.getCarStyle_List()),type,order_id));
        fragmentList.add(CheckCarFragment.newInstance("20",new Gson().toJson(memptyOrderBean.getCarStyle_List()),type,order_id));
        fragmentList.add(CheckCarFragment.newInstance("30",new Gson().toJson(memptyOrderBean.getCarStyle_List()),type,order_id));

        mlist = new ArrayList<>();
        mlist.add("全部");
        mlist.add("空闲中");
        mlist.add("出行中");
        mlist.add("维保中");

        for (int i = 0; i < mlist.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(mlist.get(i)));
        }
        BaseFragmentAdapter baseFragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, mlist);
        pager.setAdapter(baseFragmentAdapter);
        pager.setOffscreenPageLimit(1);
        tablayout.setupWithViewPager(pager);
    }

    @Override
    public void returnAddOrderBean(OrderSuccessBean emptyOrderBean) {

    }

    @Override
    public void returnOtherUserIdBean(OtherUserId otherUserId) {

    }

    @Override
    public void returnFailBean(String message, String type) {

    }

    @Override
    public void returnOrderIsOrdering(IsOrderingBean message) {

    }

    @Override
    public void returnStaffMagState(Message2Bean MessageBean) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
    }
}
