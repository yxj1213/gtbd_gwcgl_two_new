package com.ttce.vehiclemanage.ui.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.ui.usermanage.activity.ForgetPwdActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用协议
 * Created by hk on 2019/6/24.
 */

public class ProtocolActivity extends BaseActivity {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.webview)
    WebView webview;

    public static void goToPage(Activity activity, int type) {
        Intent intent = new Intent(activity, ProtocolActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    public void initPresenter() {
    }
    String url;
    @Override
    public void initView() {
        int type=this.getIntent().getIntExtra("type",0);
        if(type==0){
            titleBarTitle.setText("使用协议");
            url = "https://www.gtbds.com/upload/agreement.html";
        }else if(type==1){
            titleBarTitle.setText("隐私政策");
            url = "https://www.gtbds.com/upload/privacy.html";
        }
        WebSettings settings = webview.getSettings();
        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true); // 开启 DB storage API 功能
        settings.setAppCacheEnabled(true); // 开启 AppCacheEnable

        // 设置缓存模式，非常重要，决定了webview缓存资源的方式
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.loadUrl(url);
    }

    @OnClick(R.id.title_bar_back)
    public void onViewClicked() {
        finish();
    }

}
