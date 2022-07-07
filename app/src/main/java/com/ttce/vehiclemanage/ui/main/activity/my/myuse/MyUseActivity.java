package com.ttce.vehiclemanage.ui.main.activity.my.myuse;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.map.activity.EditLogActivity;
import com.ttce.vehiclemanage.ui.mine.activity.MessageNoticeActivity;
import com.ttce.vehiclemanage.ui.mine.activity.RailManageActivity;
import com.ttce.vehiclemanage.ui.mine.activity.SosActivity;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hk on 2019/6/19.
 */

public class MyUseActivity extends BaseActivity{

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.rl_zlrz)
    RelativeLayout rl_zlrz;
    @Bind(R.id.rl_wlgl)
    RelativeLayout rl_wlgl;
    @Bind(R.id.rl_sos)
    RelativeLayout rl_sos;
    @Bind(R.id.rl_xxtz)
    RelativeLayout rl_xxtz;
    @Override
    public int getLayoutId() {
        return R.layout.activity_myuse;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        titleBarTitle.setText("我的应用");
    }
    @OnClick({R.id.rl_zlrz,R.id.rl_wlgl,R.id.rl_sos, R.id.rl_xxtz, R.id.title_bar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_zlrz://指令日志
                startActivity(EditLogActivity.class);
                break;
            case R.id.rl_wlgl:  //围栏管理
                RailManageActivity.goToPage(this);
                break;
            case R.id.rl_sos:  //SOS报警
                SosActivity.goToPage(this);
                break;
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.rl_xxtz:  //消息通知
                startActivity(MessageNoticeActivity.class);
                break;
        }
    }
}
