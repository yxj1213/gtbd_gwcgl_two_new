package com.ttce.vehiclemanage.ui.mine.activity;

import android.text.Html;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 平台服务协议
 * Created by hk on 2019/6/24.
 */

public class PlatformServiceActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.contentTV)
    TextView contentTV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_platform_service;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleBarTitle.setText("平台服务商信息");
        StringBuilder contentSB=new StringBuilder();
        contentSB.append("<p>国泰北斗位置服务平台</p>");
        contentSB.append("<p>邮箱：sxgtbd@163.com</p>");
        contentSB.append("<p>电话：0351-2486666</p>");
        contentTV.setText(Html.fromHtml(contentSB.toString()));
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

}
