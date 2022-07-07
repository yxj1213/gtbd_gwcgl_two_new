package com.ttce.vehiclemanage.ui.main.activity.needcar;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.main.activity.MainActivity;
import com.ttce.vehiclemanage.ui.main.activity.my.order.MyOrderDetailsActivity;
import com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment;
import com.ttce.vehiclemanage.utils.ToastUtil;


import butterknife.Bind;
import butterknife.OnClick;

import static com.ttce.vehiclemanage.ui.main.fragment.MyNeedCarFragment.isRef;

/**
 * Created by hk on 2019/6/18.
 */

public class OrderSuccessActivity extends BaseActivity {
    @Bind(R.id.rb)
    CheckBox rb;
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    /**
     * 入口
     *
     * @param activity
     */
    public static void goToPage(Activity activity,String CarFlow_OrderId) {
        Intent intent = new Intent(activity, OrderSuccessActivity.class);
        intent.putExtra("CarFlow_OrderId",CarFlow_OrderId);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_order_success;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyNeedCarFragment.isRef=true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @OnClick({R.id.title_bar_back,R.id.tv_back,R.id.tv_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                isRef=true;
                finish();
                break;
            case R.id.tv_back:
                isRef=true;
                finish();
                break;
            case R.id.tv_details:
                MyOrderDetailsActivity.goToPage(this,CarFlow_OrderId,getResources().getString(R.string.type1));
                finish();
                break;
        }
    }

    @Override
    public void initPresenter() {
    }

    String CarFlow_OrderId;
    @Override
    public void initView() {
        titleBarTitle.setText("提交成功");
        CarFlow_OrderId= this.getIntent().getStringExtra("CarFlow_OrderId");
    }
}