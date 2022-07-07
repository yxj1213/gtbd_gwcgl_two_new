package com.ttce.vehiclemanage.ui.mine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 问题详情
 * Created by hk on 2019/6/24.
 */

public class ProblemDetailActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBarTitle.setText("常见问题");
        Bundle bundle = getIntent().getExtras();
        String mString = bundle.getString("id");
        if (mString.equals("0")) {
            tvContent.setText("1、终端指示灯不亮-检查电源是否完全断开大闸（总电源），检查终端连接线保险丝及所搭载保险是否熔断，检查接地或搭铁线是否脱落。\n" +
                    "2、指示灯亮-检查SIM卡是否预存资费正常。\n" +
                    "3、指示灯亮-检查GPRS/GPS天线连接是否正常、卫星天线是否被干扰或屏蔽信号。\n" +
                    "检查终端设备是否因车辆清洗造成线路板进水短路");
        } else if (mString.equals("1")) {
            tvContent.setText("1、系统平台登录失败，请核对当前使用的用户名密码是否正确；登录数量超限，请联系平台客服核实当前账号登录数量。\n" +
                    "2、位置或速度刷新不及时-请关闭系统重新打开或检查所在环境网络连接情况。\n" +
                    "3、车辆显示黄色-GPS信号接收弱，请将车辆移至空旷处或及时更换卫星天线。\n" +
                    "4、车辆出现位置偏移误差较大时，请核查车辆周边是否有较大建筑物或其他遮挡物，或检查车辆安装终端的固定位置是否松动脱落现象，如有出现请将终端固定牢靠（并接近于水平角度）。");
        } else if (mString.equals("2")) {
            tvContent.setText("1、如果以上方法仍不能解决您所面临的问题，请致电我方售后部客服或维修专员。\n" +
                    "2、平台电脑端和APP端功能操作方面有疑问，请及时联系平台客服人员为您解答。");
        }
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }
}
